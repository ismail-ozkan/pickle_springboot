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

@Service
@AllArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final Path rootLocation = Paths.get("uploads");

    public File storeFile(MultipartFile file) throws IOException {
        Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        File fileEntity = new File();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setFilePath(this.rootLocation.resolve(file.getOriginalFilename()).toString());
        return fileRepository.save(fileEntity);
    }

    public File getFile(Integer id) {
        return fileRepository.findById(id).orElse(null);
    }

}
