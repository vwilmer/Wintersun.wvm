package com.example.demo.service;

import com.example.demo.repository.IEmail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmail {

//    private JavaMailSender mailSender;
//
//    @Autowired
//    public EmailService(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }

    @Async
    @Override
    public void sendEmail(SimpleMailMessage email) {

    }
}
