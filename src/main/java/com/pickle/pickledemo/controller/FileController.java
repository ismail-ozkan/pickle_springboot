package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.entity.File;
import com.pickle.pickledemo.service.impl.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/files/upload")
    public ResponseEntity<File> uploadFile(@RequestParam("file") MultipartFile uploadedFile) {
        try {
            File file = fileService.storeFile(uploadedFile);
            return ResponseEntity.ok(file);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer id) {
        File file = fileService.getFile(id);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            Path filePath = Paths.get(file.getFilePath());
            byte[] fileBytes = Files.readAllBytes(filePath);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                    .body(fileBytes);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
