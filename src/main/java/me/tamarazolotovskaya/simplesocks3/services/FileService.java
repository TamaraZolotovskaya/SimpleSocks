package me.tamarazolotovskaya.simplesocks3.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileService {
    @Value("${path.to.data.file}")
    private String dataFilepath;

    public void saveToJsonFile(Object object, String fileName) {
        Path path = Path.of(dataFilepath, fileName);
        try {
            String json = new ObjectMapper().writeValueAsString(object);
            Files.deleteIfExists(path);
            Files.createFile(path);
            Files.writeString(path, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadFile(MultipartFile file, String filename) throws IOException {
        Path filePath = Path.of(dataFilepath, filename);
        Files.deleteIfExists(filePath);
        File newFile = new File(dataFilepath + "/" + filename);

        try (FileOutputStream fos = new FileOutputStream(newFile);
             InputStream is = file.getInputStream()) {
            IOUtils.copy(is, fos);
        }
    }
}
