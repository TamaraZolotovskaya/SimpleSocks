package me.tamarazolotovskaya.simplesocks3.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.tamarazolotovskaya.simplesocks3.services.FileService;
import me.tamarazolotovskaya.simplesocks3.services.SocksServise;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/files")
@Tag(name = "Передача файлов по HTTP", description = "API для работы с файлами")
public class FilesController {
    @Value("${path.to.data.file}")
    private String dataFilepath;
    @Value("${name.of.socks.data.file}")
    private String socksDataFileName;


    private final FileService fileService;
    private final SocksServise socksServise;

    public FilesController(FileService fileService, SocksServise socksServise) {
        this.fileService = fileService;
        this.socksServise = socksServise;
    }

    @Operation(
            summary = "Скачать данные о текущем состоянии склада в виде json-файла"
    )
    @GetMapping(value = "/downloadjson")
    public ResponseEntity<InputStreamResource> downloadFileJson() throws IOException {
        File file = new File(dataFilepath + "/" + socksDataFileName);
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + socksDataFileName)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Принимает json-файл с данными и заменяет ими данные в памяти"
    )
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFileJson(@RequestParam MultipartFile file) {
        try {
            fileService.uploadFile(file, socksDataFileName);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}
