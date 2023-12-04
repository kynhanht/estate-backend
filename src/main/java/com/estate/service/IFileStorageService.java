package com.estate.service;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface IFileStorageService {

    String storeImageFile(MultipartFile file);

    String storeFile(Path location, MultipartFile file);

    Resource loadImageFileAsResource(String filename);

    Resource loadFileAsResource(Path location, String filename);

    void deleteImageFile(String filename);

    void deleteFile(Path location, String filename);
}
