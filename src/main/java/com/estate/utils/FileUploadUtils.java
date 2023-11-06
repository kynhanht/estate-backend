package com.estate.utils;

import com.estate.constant.ErrorMessageConstants;
import com.estate.constant.SystemConstants;
import com.estate.exception.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class FileUploadUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);

    public static void uploadFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return;
        }
        // Get file name, Example: images.jpg
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Get absolute path where stores this file
            Path directoryLocation = Paths.get(SystemConstants.UPLOAD_BUILDING_FILE_DIR).toAbsolutePath().normalize();
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileUploadException(ErrorMessageConstants.FILE_UPLOAD_PATH_INVALID + fileName);
            }
            // Create location where stores this file
            Files.createDirectories(directoryLocation);
            // Copy file to the target location (Replacing existing file with the same name)
            Path fileLocation = directoryLocation.resolve(fileName);
            Files.copy(file.getInputStream(), fileLocation, StandardCopyOption.REPLACE_EXISTING);
            // Optional(In linux): Set permission for file when it is uploaded
            Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");
            Files.setPosixFilePermissions(fileLocation, perms);
            //Optional(In linux): Set owner for file when it is uploaded
            /* UserPrincipal owner = fileLocation.getFileSystem().getUserPrincipalLookupService()
                    .lookupPrincipalByName("kynhanht");
            Files.setOwner(fileLocation, owner); */
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new FileUploadException(ErrorMessageConstants.COULD_NOT_UPLOAD_FILE + fileName, e);
        }
    }

    public static String loadPathFile(String fileName) {

        if (org.apache.commons.lang.StringUtils.isBlank(fileName)) {
            return null;
        }
        Path filePath = Paths.get(SystemConstants.LOAD_FILE_DIR).resolve(fileName).normalize();
        return filePath.toString();
    }

    public static String getFileName(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        return StringUtils.cleanPath(file.getOriginalFilename());
    }
}
