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

        message.setSubject("RESTABLECE TÚ CONTRASEÑA");






        message.setSentDate(new Date());

//        message.setContent(builder.toString(), "text/html; charset=UTF-8");
        message.setContent(createContentHTMLToSend(mensaje.getTo_address(), url, token), "text/html; charset=UTF-8");


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

    public String createContentHTMLToSend(String toAddress,
                                          String url,
                                          String token) {

        String comun1 = "<div bgcolor='#FFFFFF' style='margin:0;padding: 5px;'><table border='0' cellpadding='0' cellspacing='0' height='100%' style='min-width:348px' width='100%'><tbody><tr height='32px'></tr><tr align='center'><td>\n" +
                "<table border='0' cellpadding='0' cellspacing='0' style='padding-bottom:20px;max-width:600px;min-width:220px'><tbody><tr><td><table cellpadding='0' cellspacing='0'>\n" +
                "<tbody><tr><td></td><td><table border='0' cellpadding='0' cellspacing='0' style='direction:ltr;padding-bottom:7px' width='100%'>\n" +
                "<tbody><tr><td align='left'><img src='https://www.bg.com.bo/wp-content/uploads/logo-aj.png'></td><td align='right' width='35'><img height='28' src='https://ci5.googleusercontent.com/proxy/6NSREpM_-WhRjzfatLPCRhciZraXl3AMbeFU3GLfke5Fd1h9L4wL7VSpz498N2riQzzfhqPOCadJSXvL07dfBvHo1B4zOoPMq3uSfzYJ62QHcbChiXGBB3D-uIne=s0-d-e1-ft#https://www.gstatic.com/accountalerts/email/anonymous_profile_photo.png' style='width:28px;height:28px;border-radius:50%' width='28'></td></tr></tbody></table></td><td></td></tr><tr><td height='5' style='background:url('https://ci5.googleusercontent.com/proxy/Ky9uO0L4rCGl_EWbkifDjpWLd1NFKfi-7b0JFxdVNqqFxNiBhPWyAqs1qKnPjpW5SaR1r77z_cI3vg96-jP6lpbwEfA67jtQsf_zpAF_QNGPg11GKPcs=s0-d-e1-ft#https://www.gstatic.com/accountalerts/email/hodor/4-corner-nw.png') top left no-repeat' width='6'><div></div></td><td height='5' style='background:url('https://ci4.googleusercontent.com/proxy/gXzgRO1K9pWfZAogBcrVQnvwQSkX2I8jcnx4g-SvUNfv82pak_4MS_c1aUDeM40soy4koxNBI_ked6U7zrdBUTjETr518K7PLsfDQqhgTKSF2StiCg=s0-d-e1-ft#https://www.gstatic.com/accountalerts/email/hodor/4-pixel-n.png') top center repeat-x'><div></div></td><td height='5' style='background:url('https://ci5.googleusercontent.com/proxy/FCthMIcyUgO8YTLL_W5YLLOf8y-W7iePJhbY6RQcus60exGI_nmzO0_hpSJ3NY-IfXIgy7OGWAcb9gi34GE4UjaXoVwU99DLo_R6kdVlVf90Yw0S5N6J=s0-d-e1-ft#https://www.gstatic.com/accountalerts/email/hodor/4-corner-ne.png') top right no-repeat' width='6'><div></div></td></tr><tr><td style='background:url('https://ci4.googleusercontent.com/proxy/nt_AIB8tvZvtjQ12K1IxqaM2XPLvZjk-KfB0zxDCUh74WW4hggtOwVMhqJjCPlfdv-7695plB1wt2DOjd6bfj9g6YSYsIWkLks-Sp2OOLZrCHVSNqA=s0-d-e1-ft#https://www.gstatic.com/accountalerts/email/hodor/4-pixel-w.png') center left repeat-y' width='6'><div></div></td><td><div style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;padding-left:20px;padding-right:20px;border-bottom:thin solid #f0f0f0;color:rgba(0,0,0,0.87);font-size:24px;padding-bottom:38px;padding-top:40px;text-align:center;word-break:break-word'>\n" +
                "\n" + "<div>Accediste a tu cuenta para restablecer la contraseña<br>\n" + "<a style='font-family:Roboto-Regular,Arial,sans-serif;color:rgba(0,0,0,0.87);font-size:16px;line-height:1.8'>";
        String comun2 = "</a></div></div><div style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:14px;color:rgba(0,0,0,0.87);line-height:1.6;padding-left:20px;padding-right:20px;padding-bottom:32px;padding-top:24px'><div>Te enviamos este correo electrónico para asegurarnos de que hayas sido tú el que solicitó el cambio de contraseña. Para restablecer tu contraseña, haz clic en la opción que aparece abajo. Haciendo clic en el botón podras acceder a la página para cambiar tu contraseña.<div style='padding-top:24px;text-align:center'><a href='#' style='display:inline-block;text-decoration:none' target='_blank' data-saferedirecturl=''><table border='0' cellpadding='0' cellspacing='0' style='background-color:#4184f3;border-radius:2px;min-width:90px'><tbody><tr style='height:6px'></tr><tr><td style='padding-left:8px;padding-right:8px;text-align:center'>";
        String beginTagLink = "<a href='";
        String urlToken = url + token;
        String newAnchorTag = beginTagLink + urlToken + "'";
        String endTagLink = " style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;color:#ffffff;font-weight:400;line-height:20px;text-decoration:none;font-size:13px;text-transform:uppercase' target='_blank'>Cambiar contraseña</a>";
        String finalAnchor = newAnchorTag + endTagLink;
        String comun3 = "</td></tr><tr style='height:6px'></tr></tbody></table></a></div></div></div></td></tr><tr></tr><tr><td></td><td><div style='text-align:left'><div style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;color:rgba(0,0,0,0.54);font-size:12px;line-height:20px;padding-top:10px'><div>Te enviamos este correo electrónico para notificarte acerca de los cambios importantes que hicimos en tu cuenta y servicios de la Autoridad de Fiscalización del Juego.</div><div style='direction:ltr'>©";
        String param2 = Calendar.getInstance().get(Calendar.YEAR) + "";
        String comun4 = " SAFJ,<a style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;color:rgba(0,0,0,0.54);font-size:12px;line-height:20px;padding-top:10px'> Calle 16 de Obrajes N° 220, Edif. Centro de Negocios Obrajes, Piso 2, La Paz - Bolivia.</a></div></div></div></td><td></td></tr></tbody></table></td></tr></tbody></table></td></tr><tr height='32px'></tr></tbody></table></div>";
        String finalHTLM = comun1 + toAddress + comun2 + finalAnchor + comun3 + param2 + comun4;
        return finalHTLM;

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

