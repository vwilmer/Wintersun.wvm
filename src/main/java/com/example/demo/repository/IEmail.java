package com.example.demo.repository;

import org.springframework.mail.SimpleMailMessage;

public interface IEmail {
    void sendEmail(SimpleMailMessage email);
}
