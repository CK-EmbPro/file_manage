package com.manage_file.file_handling.service.impl;

import com.manage_file.file_handling.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Value("${basepath}")
    private String basepath;

    @Override
    public List<String> getFileNames() {
        File baseDir = new File(basepath);
        List<String> fileNames = new ArrayList<>();
        try {
            if (baseDir.exists()) {
                fileNames = Arrays.stream(baseDir.listFiles()).map(File::getName).toList();
            } else {
                throw new FileNotFoundException("files directory not found");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        }

        return fileNames;
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String uploadStatus = "Not yet";
        if (!file.isEmpty()) {

            File baseDir = new File(basepath);
            if (!baseDir.exists()) {
                if (!baseDir.mkdirs()) {
                    throw new IOException("Failed to create directory " + basepath);
                }

            }

            boolean targetFile = new File(basepath + File.separator + file.getOriginalFilename()).exists();
            if (targetFile) {
                return "file already exists";
            }

            try {
                Path fileToSave = Path.of(basepath + File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(), fileToSave, StandardCopyOption.REPLACE_EXISTING);
                uploadStatus = "uploaded successfully";


            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }else {
            return "file is empty";
        }

        return uploadStatus;
    }

    @Override
    public Resource downloadFile(String fileName) {
        Resource fileUrl = null;
        try {
            File fileToDownload = new File(basepath +File.separator+ fileName);
            if (fileToDownload.exists()) {
                fileUrl = new UrlResource(fileToDownload.toURI());
            } else {
                throw new FileNotFoundException("file not found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fileUrl;
    }
}
