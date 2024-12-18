package com.pickle.pickledemo.service.impl;

import com.pickle.pickledemo.entity.File;
import com.pickle.pickledemo.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final Path rootLocation = Paths.get("uploads");

    public File storeFile(MultipartFile file) throws IOException {

        File fileEntity = new File();
        long timestamp = System.currentTimeMillis();
        fileEntity.setFileName(timestamp+file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setFilePath(this.rootLocation.resolve(timestamp+file.getOriginalFilename()).toString());

        Files.copy(file.getInputStream(), this.rootLocation.resolve(timestamp+file.getOriginalFilename()));

        return fileRepository.save(fileEntity);
    }

    public File getFile(Integer id) {
        return fileRepository.findById(id).orElse(null);
    }

}
