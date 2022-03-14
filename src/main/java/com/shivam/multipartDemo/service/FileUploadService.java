package com.shivam.multipartDemo.service;

import com.shivam.multipartDemo.model.FileEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    void uploadToLocal(MultipartFile file);
    void uploadToDb(MultipartFile file);
    FileEntity downloadFile(String id);

}
