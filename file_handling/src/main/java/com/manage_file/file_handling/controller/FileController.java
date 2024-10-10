package com.manage_file.file_handling.controller;

import com.manage_file.file_handling.response.ApiResponse;
import com.manage_file.file_handling.service.impl.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("file")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FileController {

    public final FileServiceImpl fileService;

    @GetMapping("hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("welcome to find uploading&downloading");

    }
    @PostMapping("upload")
    public ResponseEntity<ApiResponse<String>> uploadFile(@RequestParam(name = "file") MultipartFile file) throws IOException {
        String uploadStatus = fileService.uploadFile(file);
        HttpStatus status = "uploaded successfully".equals(uploadStatus) ? HttpStatus.CREATED : "file already exists".equals(uploadStatus) ? HttpStatus.BAD_REQUEST : HttpStatus.METHOD_NOT_ALLOWED;
        return ResponseEntity
                .status(status)
                .body(new ApiResponse<>(uploadStatus, status));
    }

    @GetMapping("all")
    public ResponseEntity<ApiResponse<List<String>>> listFiles(){
        List<String> files = fileService.getFileNames();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(files,  HttpStatus.OK));
    }

    @GetMapping(value = "download/{file_name}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadFile(@PathVariable(value = "file_name") String fileName){
        Resource fileResource = fileService.downloadFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+fileName+"\"")
                .body(fileResource);
    }
}