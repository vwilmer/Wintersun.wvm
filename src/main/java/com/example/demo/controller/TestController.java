package com.example.demo.controller;

import com.example.demo.entity.enumerador.UsuarioEntity;
import com.example.demo.mail.Mensaje;
import com.example.demo.repository.IUserV2Repository;
import com.example.demo.service.ArchivoServicio;
import com.example.demo.util.pdf.UsuarioEntityPDFView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.*;

@RestController
public class TestController {

    @Value("${gmail.username}")
    private String username;
    @Value("${gmail.password}")
    private String password;

    @Autowired
    private ArchivoServicio archivoServicio;

    @Autowired
    private IUserV2Repository userV2Repository;

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

        String token = "61b67f71-4640-4dde-b1b1-56914cec6cec";
        String url = "http://172.16.30.17:2030/winter/reset?token=";
        String changePassword = "Cambiar la contraseña";

        StringBuilder builder = new StringBuilder();

        builder.append("<h1 style='background:#0277BD; color: white; padding: 10px; border-radius: 5px; text-align: center; font-family: Arial;'>Bienvenido al Sistema de la Autoridad de Fiscalización del Juego | SAFJ</h1>");
        builder.append("<p><h2 style='font-size: 24px; color: #202124;'>Restablece tu contraseña</h2></p>");
        builder.append("<p style='margin-top: -10px;'>Para restablecer su contraseña, haga clic en la opción que aparece abajo. Haciendo clic en el botón podrá acceder a la página para cambiar su contraseña.<p/>");
        builder.append("<p style='margin-top: 25px;'><a style='background: #3F51B5; color: white; text-decoration: none; padding: 10px; border-radius: 3px; border-style: dotted;' target=\"_blank\" " +
                "href=" + url + token + ">" + changePassword + "</a></p>");
        builder.append("<br/>");
        builder.append("<p>Atentamente:</p>");
        builder.append("<p style='background:#0277BD; border-radius: 10px; padding-left: 20px; padding-right: 28px; padding-bottom: 6px; padding-top: 12px; color: white;'>AUTORIDAD DE FISCALIZACIÓN DEL JUEGO</p>");

        message.setSubject("RESTABLECER LA CONTRASEÑA DE USUARIO");
        message.setContent(builder.toString(), "text/html; charset=UTF-8");
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


    @GetMapping("/reporte")
    public ModelAndView report() {

        Map<String, Object> model = new HashMap<>();

        List<UsuarioEntity> usuarioEntities = this.userV2Repository.findAll();

        model.put("usuarios", usuarioEntities);

        return new ModelAndView(new UsuarioEntityPDFView(), model);
    }
}

