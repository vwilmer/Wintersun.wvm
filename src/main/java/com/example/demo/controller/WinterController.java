package com.example.demo.controller;

import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.enumerador.CommentType;
import com.example.demo.entity.enumerador.RolType;
import com.example.demo.entity.enumerador.UsuarioEntity;
import com.example.demo.repository.IUserV2Repository;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/winter")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WinterController {

    private CommentService commentService;
    private IUserV2Repository userV2Repository;

    @Autowired
    public WinterController(CommentService commentService,
                            IUserV2Repository userV2Repository) {
        this.commentService = commentService;
        this.userV2Repository = userV2Repository;
    }

    // display form to forgot password
    @GetMapping("/forgot")
    public ModelAndView displayForgotPasswordPage() {
        return new ModelAndView("forgotPassword");
    }

    @Value("${gmail.username}")
    private String username;
    @Value("${gmail.password}")
    private String password;

    // make action
    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestBody UsuarioEntity usuarioEntity) {

        UsuarioEntity userFromDB = this.userV2Repository.findByUsername(usuarioEntity.getUsername());

        Map<String, Object> objectMap = new LinkedHashMap<>();

        if (userFromDB == null) {
            objectMap.put("message", "El usuario no existe");
            objectMap.put("status", 404);
            return new ResponseEntity<>(objectMap, HttpStatus.NOT_FOUND);
        } else {
            // Generate random 36-character string token for reset password
            userFromDB.setResetToken(UUID.randomUUID().toString());
            // Save token to database
            this.userV2Repository.save(userFromDB);

            Properties properties = new Properties();
            properties.put("mail.transport.protocol", "smtp");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "mail2.aj.gob.bo");
            properties.put("mail.smtp.port", "587");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username, false));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userFromDB.getEmail()));
                message.setSubject("REF. RESTABLECE TÚ CONTRASEÑA");
                message.setSentDate(new Date());
                String url = "http://172.16.30.17:2030/winter/reset?token=";
                message.setContent(createContentHTMLToSend(userFromDB.getEmail(), url, userFromDB.getResetToken()), "text/html; charset=UTF-8");
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }


            objectMap.put("status", 200);
            objectMap.put("mensaje", "Se ha enviado un enlace de restablecimiento de contraseña a: " + userFromDB.getEmail() + " por favor, revise el mensaje enviado en su correo electrónico.");
            return new ResponseEntity<>(objectMap, HttpStatus.OK);
        }
    }

    // display form to reset password
    @GetMapping("/reset")
    public ModelAndView displayResetPassword(@RequestParam("token") String token) {

        UsuarioEntity userFromDB = this.userV2Repository.findByResetToken(token);

        ModelAndView resetPass = new ModelAndView("resetPassword");

        if (userFromDB != null) {
            // Token found in DB
            resetPass.addObject("status", 200);
            resetPass.addObject("messageOK", "Para cambiar tu contraseña tienes que escribir la nueva contraseña y volver a escribir la misma contraseña en el formulario que aparece abajo. Si las contraseñas coinciden, el sistema habilitará en botón CAMBIAR CONTRASEÑA, en el cual debes de hacer clic en el mismo y tu contraseña será restablecida y podras iniciar sesión con las nuevas credenciales, por lo que debes de guardar y no compartir tu contraseña con nadie.");
            resetPass.addObject("resetToken", token);
        } else {
            // Token not found in DB
            resetPass.addObject("message", "Oops!  Este token contiene un enlace de restablecimiento de contraseña inválido. Para mayor información diríjase a oficinas del Departamento de Desarrollo y Control de Calidad - Dirección Nacional de Informática y Telecomunicaciones.");
            resetPass.addObject("status", 404);
        }
        return resetPass;
    }

    // make action
    @PostMapping("/reset")
    public ResponseEntity<?> setNewPassword (@RequestParam Map<String, String> requestParams) {
        System.out.println("BOLIVIA");
        System.out.println(requestParams.get("token"));
        System.out.println(requestParams.get("password"));
        UsuarioEntity userFromDB = this.userV2Repository.findByResetToken(requestParams.get("token"));
        Map<String, Object> objectMap = new LinkedHashMap<>();

        if (userFromDB != null) {
            // Token found in DB
            // Set new password
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            userFromDB.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
            // Set the reset token to null so it cannot be used again
            userFromDB.setResetToken(null);
            this.userV2Repository.save(userFromDB);
            objectMap.put("status", 200);
            objectMap.put("message", "Se ha restablecido su contraseña exitosamente. Ahora puedes iniciar sesión con tu nombre de usuario y tu nueva contraseña.");
            return new ResponseEntity<>(objectMap, HttpStatus.OK);
        } else {
            // Token not found in DB
            objectMap.put("message", "Oops!  Parece que tu enlace de restablecimiento de contraseña inválido.");
            objectMap.put("status", 404);
            return new ResponseEntity<>(objectMap, HttpStatus.NOT_FOUND);
        }

    }

    // display form to forgot login
    @GetMapping("/login")
    public ModelAndView displayLoginPage() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody UsuarioEntity usuarioEntity, HttpServletRequest request) {
        // Getting servlet request URL
        String url = request.getRequestURL().toString();
        // Getting servlet request query string.
        String queryString = request.getQueryString();

//        UsuarioEntity userFromDB = this.userV2Repository.findByUsernameAndPassword(usuarioEntity.getUsername(), usuarioEntity.getPassword());
        UsuarioEntity userFromDB = this.userV2Repository.findByUsername(usuarioEntity.getUsername());

        Map<String, Object> objectMap = new HashMap<>();

        if (userFromDB == null) {
            objectMap.put("message", "El usuario no existe");
            objectMap.put("status", 404);
            return new ResponseEntity<>(objectMap, HttpStatus.NOT_FOUND);
        } else {
            String password = userFromDB.getPassword();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (encoder.matches(usuarioEntity.getPassword(), password)) {
                objectMap.put("status", 200);
                objectMap.put("resultado", userFromDB);
                return new ResponseEntity<>(objectMap, HttpStatus.OK);
            } else {
                objectMap.put("message", "Verifique la contraseña y vuelva a intentar");
                objectMap.put("status", 401);
                return new ResponseEntity<>(objectMap, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PutMapping("/changepassword/{userId}")
    public ResponseEntity<?> changePasswordFromUser(@PathVariable Long userId,
                                                    @RequestBody UsuarioEntity usuarioEntity) {
        UsuarioEntity userFromDB = this.userV2Repository.findById(userId).get();

        Map<String, Object> objectMap = new HashMap<>();

        if (userFromDB == null) {
            objectMap.put("message", "El usuario no existe");
            objectMap.put("status", 404);
            return new ResponseEntity<>(objectMap, HttpStatus.NOT_FOUND);
        } else {
            String newPassword = usuarioEntity.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(newPassword);

            userFromDB.setPassword(hashedPassword);

            this.userV2Repository.save(userFromDB);

            objectMap.put("status", 200);
            objectMap.put("resultado", userFromDB);
            return new ResponseEntity<>(objectMap, HttpStatus.OK);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> registrarUsr() {
        return new ResponseEntity<>(this.userV2Repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsr(@RequestBody UsuarioEntity usuarioEntity) {
        Map<String, Object> objectMap = new HashMap<>();
        UsuarioEntity userFromDB = this.userV2Repository.findByUsername(usuarioEntity.getUsername());

        if (userFromDB != null) {
            objectMap.put("message", "El usuario ya existe");
            objectMap.put("status", 409);
        } else {
            objectMap.put("message", "Se agrego un usuario");
            objectMap.put("status", 200);

            String password = usuarioEntity.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);
            usuarioEntity.setPassword(hashedPassword);
            usuarioEntity.setActive(true);
            usuarioEntity.setRoles(Collections.singleton(RolType.USER));

            this.userV2Repository.save(usuarioEntity);
        }

        return new ResponseEntity<>(objectMap, HttpStatus.OK);
    }

    // http://172.16.30.17:2030/winter/pepe?nombre=google%20esta%20aqui
    @GetMapping("/pepe")
    public ResponseEntity<?> main(@RequestParam(required = false, defaultValue = "oveja", name = "nombre") String pParam) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("message", "El parametro de solicitud es: " + pParam);
        objectMap.put("status", 200);

        return new ResponseEntity<>(objectMap, HttpStatus.OK);
    }

    @GetMapping("/tipos")
    public ResponseEntity<?> getByType(@RequestParam(required = false, defaultValue = "PLUS", name = "tipo") CommentType type) throws ServletException {
        if (!contains(type.toString())) {
            throw new ServletException("El valor de tipo no forma parte del enumerador");
        }
        List<CommentEntity> entities = this.commentService.getByType(type);
        Map<String, Object> objectMap = new HashMap<>();

        objectMap.put("status", 200);
        objectMap.put("message", "El parametro de solicitud es: " + type);
        objectMap.put("result", entities);

        return new ResponseEntity<>(objectMap, HttpStatus.OK);
    }

    private boolean contains(String type) {

        for (CommentType c : CommentType.values()) {
            if (c.name().equals(type)) {
                return true;
            }
        }

        return false;
    }


    private String createContentHTMLToSend(String toAddress,
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
}
