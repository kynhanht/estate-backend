package com.estate.service.impl;


import com.estate.config.FileStorageProperties;
import com.estate.exception.FileNotFoundException;
import com.estate.exception.FileStorageException;
import com.estate.service.IFileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService implements IFileStorageService {

    private final Path fileImageStorageLocation;

    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileImageStorageLocation = Paths.get(fileStorageProperties.getUploadImageDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileImageStorageLocation);
        } catch (IOException ex) {
            throw new FileStorageException("Could not create the directory where the uploaded file will be stored", ex);
        }
    }

    public String storeImageFile(MultipartFile file) {
        return storeFile(fileImageStorageLocation, file);
    }

    public String storeFile(Path location, MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String filename = uuid + "." + ext;
        System.out.println(filename);
        try {
            if (filename.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence: " + filename);
            }
            Path targetLocation = location.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file: " + filename + ". Please try again!", ex);
        }

    }

    public Resource loadImageFileAsResource(String filename) {
        return loadFileAsResource(fileImageStorageLocation, filename);
    }

    public Resource loadFileAsResource(Path location, String filename) {
        try {
            Path filePath = location.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found: " + filename);
            }
        } catch (IOException ex) {
            throw new FileStorageException("File not found: " + filename, ex);
        }
    }

    public void deleteImageFile(String filename) {
        deleteFile(fileImageStorageLocation, filename);
    }

    public void deleteFile(Path location, String filename) {
        try {
            Path filePath = location.resolve(filename).normalize();
            if (!Files.exists(filePath)) {
                throw new FileNotFoundException("File Not found: " + filename);
            }
            Files.delete(filePath);
        } catch (IOException ex) {
            throw new FileNotFoundException("File not found: " + filename, ex);
        }
    }
}
