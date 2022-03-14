package com.shivam.multipartDemo.repository;

import com.shivam.multipartDemo.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity,String> {


}
