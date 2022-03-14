package com.shivam.multipartDemo.service;

import com.shivam.multipartDemo.model.FileEntity;
import com.shivam.multipartDemo.repository.FileRepository;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private String localPath = "/Users/sjari/desktop/upload_";

    @Autowired
    private FileRepository repository;

    @Override
    public void uploadToDb(MultipartFile file) {

        FileEntity fileEntity = new FileEntity();

        try {
            fileEntity.setData(file.getBytes());
            fileEntity.setFileType(file.getContentType());
            fileEntity.setFileName(file.getOriginalFilename());
            repository.save(fileEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadToLocal(MultipartFile file) {

        Path path = Paths.get(localPath + file.getOriginalFilename());
        try {
            byte[] data = file.getBytes();
            Files.write(path, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FileEntity downloadFile(String id) {

        FileEntity fileEntity = repository.getOne(id);
        return fileEntity;
    }
}
