package Controller;

import Entity.FileDocument;
import Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/files/api/fil/v1")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String UPLOAD_DIR = "/upload";
    private static final String COMPRESSED_DIR = "/compressed";

    @PostMapping("/upload")
    public ResponseEntity<FileDocument> uploadFile(@RequestParam("file") MultipartFile file){

        try{
            File originalFile = saveFile(file); //сохранение оригнального файла

            //Сжимается видео, если это видеофайл
            if(isVideoFile(originalFile)){
                File compressedFile = compressVideo(originalFile);
                FileDocument uploadFile = fileService.uploadFile((MultipartFile) compressedFile); //Замена оригинального фала, на сжатый в БД
                return ResponseEntity.status(HttpStatus.CREATED).body(uploadFile);
            } else {
                //Если это не видео, просто загружаем оригинальный файл
                FileDocument uploadFile = fileService.uploadFile((MultipartFile) originalFile);
                return ResponseEntity.status(HttpStatus.CREATED).body(uploadFile);
            }
        } catch(IOException | InterruptedException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<FileDocument> getFile(@PathVariable String id){
        FileDocument fileDocument = fileService.getFile(id);

        if(fileDocument != null){
            return ResponseEntity.ok(fileDocument);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable String id){
        fileService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }

    private File saveFile(MultipartFile file) throws IOException{
        //Создается директория для загрузки, если ее нет
        Files.createDirectories(Paths.get(UPLOAD_DIR));
        Path filePath = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        file.transferTo(filePath);
        return filePath.toFile();
    }

    private boolean isVideoFile(File file){
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".mp4") || fileName.endsWith(".avi");
    }

    private File compressVideo(File originalFile) throws IOException, InterruptedException {

        //Создание директории ждя сжатых файлов, если ее не существует
        Files.createDirectories(Paths.get(COMPRESSED_DIR));
        String compressedFileName = COMPRESSED_DIR + "compressed_" + originalFile.getName();
        ProcessBuilder processBuilder = new ProcessBuilder("ffmpeg", "-i", originalFile.getAbsolutePath(),
                "-vcodec", "libx265", "-crf", "28", compressedFileName);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        if(exitCode != 0){
            throw new IOException("Ошибка сжатия видео: " + exitCode);
        }
        return new File(compressedFileName);

    }

}
