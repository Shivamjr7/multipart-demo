package com.shivam.multipartDemo.controller;

import com.shivam.multipartDemo.model.FileEntity;
import com.shivam.multipartDemo.repository.FileRepository;
import com.shivam.multipartDemo.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController()
@RequestMapping("/v1")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping(value = "/upload/local")
    public void uploadToFile(@RequestParam("file") MultipartFile file) {

        fileUploadService.uploadToLocal(file);
    }

    @PostMapping(value = "/upload/db")
    public void uploadToDb(@RequestParam("file") MultipartFile file) {
        fileUploadService.uploadToDb(file);
    }

    @GetMapping(value = "/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id)
    {

        FileEntity downloadedFile =   fileUploadService.downloadFile(id);

       return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(downloadedFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+downloadedFile.getFileName())
                .body(new ByteArrayResource(downloadedFile.getData()));


    }

}
