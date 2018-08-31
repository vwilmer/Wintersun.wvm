package com.example.demo.controller;

import com.example.demo.entity.ArchivoEntity;
import com.example.demo.service.ArchivoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class ArchivoController {

    private ArchivoServicio archivoServicio;

    @Autowired
    public ArchivoController(ArchivoServicio archivoServicio) {
        this.archivoServicio = archivoServicio;
    }

    @GetMapping("/get/files")
    public List<ArchivoEntity> getAllFilesUploaded() {
        return archivoServicio.getAllFiles();
    }

    @RequestMapping(value = "/open/file", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getFile(@RequestParam("filename") String filename) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        if (filename.endsWith(".pdf")) {
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
        } else if (filename.toLowerCase().endsWith(".txt")) {
            headers.setContentType(MediaType.parseMediaType("text/plain"));
        } else if (filename.toLowerCase().endsWith(".docx")) {
            headers.setContentType(MediaType.parseMediaType("application/docx"));
        } else if (filename.toLowerCase().endsWith(".doc")) {
            headers.setContentType(MediaType.parseMediaType("application/doc"));
        } else if (filename.toLowerCase().endsWith(".xlsx")) {
            headers.setContentType(MediaType.parseMediaType("application/xlsx"));
        } else if (filename.toLowerCase().endsWith(".xls")) {
            headers.setContentType(MediaType.parseMediaType("application/xls"));
        } else if (filename.toLowerCase().endsWith(".ppt")) {
            headers.setContentType(MediaType.parseMediaType("application/ppt"));
        } else if (filename.toLowerCase().endsWith(".pptx")) {
            headers.setContentType(MediaType.parseMediaType("application/pptx"));
        } else if (filename.toLowerCase().endsWith(".gif")) {
            headers.setContentType(MediaType.parseMediaType("image/gif"));
        } else if (filename.toLowerCase().endsWith(".png")) {
            headers.setContentType(MediaType.parseMediaType("image/png"));
        } else if (filename.toLowerCase().endsWith(".jpg")) {
            headers.setContentType(MediaType.parseMediaType("image/jpg"));
        } else if (filename.toLowerCase().endsWith(".jpeg")) {
            headers.setContentType(MediaType.parseMediaType("image/jpeg"));
        } else if (filename.toLowerCase().endsWith(".bmp")) {
            headers.setContentType(MediaType.parseMediaType("image/bmp"));
        } else if (filename.toLowerCase().endsWith(".mp4")) {
            headers.setContentType(MediaType.parseMediaType("video/mp4"));
        } else if (filename.toLowerCase().endsWith(".mp3")) {
            headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
        }

        headers.add("content-disposition", "inline;filename=" + filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        byte[] contents = archivoServicio.openFile(filename);
        return new ResponseEntity(contents, headers, HttpStatus.OK);
    }

    // delete element from database and hard disk
    @DeleteMapping("/delete/file/{id}")
    public void deleteFile(@PathVariable(name = "id") Long id) {
        archivoServicio.delete(id);
    }

    @PostMapping("/api/upload")
    public String uploadFileMulti(ArchivoEntity file,
                                  @RequestParam("files") MultipartFile[] uploadfiles) {
        int response = archivoServicio.uploadFile(file, uploadfiles);
        return "se subio";
    }
}
