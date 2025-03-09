package ru.FilesMessanger.File.Processing;

import Entity.FileDocument;
import Service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FileService fileService;

    @Test
    public void testUploadFile() throws Exception{
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Hello World".getBytes());

        FileDocument fileDocument = new FileDocument();
        fileDocument.setId(UUID.randomUUID().toString());
        fileDocument.setFileName("test.txt");
        fileDocument.setContentType("text/plain");
        fileDocument.setSize(file.getSize());
        fileDocument.setData(file.getBytes());

        //Ставим заглушку для fileService
        when(fileService.uploadFile(any(MultipartFile.class))).thenReturn(fileDocument);

        //Выполняем запрос и проверяем статус содержимого ответа
        mockMvc.perform(multipart("/files/api/fil/v1/upload")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fileName").value("test.txt"))
                .andExpect(jsonPath("$.contentType").value("text/palin"));

    }

    @Test
    public void testGetFile() throws Exception{

        //Создается объект FileDocument, который будет возвращен сервисом
        FileDocument fileDocument = new FileDocument();
        fileDocument.setId("123");
        fileDocument.setFileName("test.txt");
        fileDocument.setContentType("text/plain");
        fileDocument.setSize(1024);
        fileDocument.setData("Hello World".getBytes());

        when(fileService.getFile("123")).thenReturn(fileDocument);

        mockMvc.perform(get("/files/api/fil/v1/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fileName").value("test.txt"));

    }

    @Test
    public void testDeleteFile() throws Exception {

        doNothing().when(fileService).deleteFile("123");

        // Выполняем запрос на удаление файла и проверяем статус
        mockMvc.perform(delete("/files/api/fil/v1/123"))
                .andExpect(status().isNoContent());
    }

}
