package com.example.demo.repository;

import com.example.demo.entity.ArchivoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IArchivoCustom {
    int uploadFile(ArchivoEntity file, MultipartFile[] uploadfiles);

    List<ArchivoEntity> getAllFiles();

    void delete(Long id);

    byte[] openFile(String filename) throws IOException;
}
