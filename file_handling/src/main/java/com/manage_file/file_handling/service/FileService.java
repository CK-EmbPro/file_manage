package com.manage_file.file_handling.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<String> getFileNames();
    String uploadFile(MultipartFile file) throws IOException;
    Resource downloadFile(String fileName);
}
