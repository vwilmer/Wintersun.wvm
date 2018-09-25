package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DemoWinterApplication {

    public static String UPLOADED_FOLDER;

    @Value("${fileuploader.filepath}")
    public void setFolder(String folderValue) {
        UPLOADED_FOLDER = folderValue;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoWinterApplication.class, args);
    }
}
