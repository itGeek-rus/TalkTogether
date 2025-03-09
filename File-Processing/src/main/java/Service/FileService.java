package Service;

import Entity.FileDocument;
import Repo.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileDocument uploadFile(MultipartFile file) throws IOException {

        if(file == null || file.isEmpty()){
            throw new IllegalArgumentException("Файл не должен быть пустым");
        }

        FileDocument fileDocument = new FileDocument();
        String originalFileName = file.getOriginalFilename();

        if(originalFileName != null){
            fileDocument.setFileName(originalFileName);
        } else {
            fileDocument.setFileName("filename.txt");
        }

        fileDocument.setContentType(file.getContentType());
        fileDocument.setSize(file.getSize());
        fileDocument.setData(file.getBytes());

        return fileRepository.save(fileDocument);

    }

    public FileDocument getFile(String id){
        return fileRepository.findById(id).orElse(null);
    }

    public void deleteFile(String id){
        fileRepository.deleteById(id);
    }

}
