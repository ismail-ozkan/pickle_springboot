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
import java.util.Arrays;

@Service
@AllArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final Path rootLocation = Paths.get("uploads");

    public File storeFile(MultipartFile file) throws IOException {

        File fileEntity = new File();
        long timestamp = System.currentTimeMillis();
        // add timestamp end of the file name but before the file extension

        // Extract the file's original name and extension
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new RuntimeException("File name cannot be null");
        }
        
        String fileExtension = "";
        String fileNameWithoutExtension = originalFilename;

        int lastDotIndex = originalFilename.lastIndexOf(".");
        if (lastDotIndex != -1) {
            fileNameWithoutExtension = originalFilename.substring(0, lastDotIndex).replace(" ", "_");
            fileExtension = originalFilename.substring(lastDotIndex);

            // Store all valid extensions in an array to validate the file name format
            String[] validExtensions = {"jpg", "png", "jpeg", "pdf"};
            if (!Arrays.asList(validExtensions).contains(fileExtension.toLowerCase().substring(1))) {
                throw new RuntimeException("Invalid file extension. Supported extensions are JPG, PNG, JPEG.");
            }

            // Validate the file name format
            // if (!Pattern.matches("[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*", originalFilename)) {
            //     throw new RuntimeException("Invalid file name format. File name should only contain alphanumeric characters and underscores.");
        }

        // Append timestamp before the file extension
        String newFileName = fileNameWithoutExtension + "_" + timestamp + fileExtension;

        // Set properties in file entity
        fileEntity.setFileName(newFileName);
        fileEntity.setFileType(file.getContentType());
        fileEntity.setFilePath(this.rootLocation.resolve(newFileName).toString());

        // Ensure the root directory exists
        if (!Files.exists(rootLocation)) {
            Files.createDirectories(rootLocation);
        }

        // Save the file to the specified directory
        Files.copy(file.getInputStream(), this.rootLocation.resolve(newFileName));

        System.out.println("File uploaded successfully: " + newFileName);

        return fileRepository.save(fileEntity);
    }

    public File getFile(Integer id) {
        return fileRepository.findById(id).orElse(null);
    }

}
