package com.example.demo.controller;

import com.example.demo.mail.Mensaje;
import com.example.demo.service.ArchivoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@RestController
public class TestController {

    @Value("${gmail.username}")
    private String username;
    @Value("${gmail.password}")
    private String password;

    @Autowired
    private ArchivoServicio archivoServicio;

    @PostMapping("/send")
    public String sendEmail(@RequestBody Mensaje mensaje) throws IOException, MessagingException {
        System.out.println(mensaje);
        sendmail(mensaje);
        return "Enviado";
    }

    private void sendmail(Mensaje mensaje) throws MessagingException, IOException {

        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp"); // comentado

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.host", "mail2.aj.gob.bo");

        properties.put("mail.smtp.port", "587");
//        properties.put("mail.smtp.connectiontimeout", 10000);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username, false));

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mensaje.getTo_address()));
        message.setSubject(mensaje.getAsunto());
        message.setContent(mensaje.getCuerpo(), "text/html");
        message.setSentDate(new Date());

//        // messageBodyPart
//        MimeBodyPart mimeBodyPart = new MimeBodyPart();
//        mimeBodyPart.setContent("email body content", "text/html");
//
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(mimeBodyPart);
//
//        // attachFilePart
//        MimeBodyPart mimeBodyPart1 = new MimeBodyPart();
//        mimeBodyPart1.attachFile("D:\\pepe.png");
//
//        multipart.addBodyPart(mimeBodyPart1);
//
//        message.setContent(multipart);

        // send
        Transport.send(message);

    }

    @GetMapping("/listado/{id}")
    public ResponseEntity<?> getListado(@PathVariable Integer id) {

//        System.out.println(this.archivoServicio.getFun(1091).size());
//        System.out.println(this.archivoServicio.getFun(1093).size());

        return new ResponseEntity<>(this.archivoServicio.getFun(id), HttpStatus.OK);
    }
}
