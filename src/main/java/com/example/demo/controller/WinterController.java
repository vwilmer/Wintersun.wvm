package com.example.demo.controller;

import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.enumerador.CommentType;
import com.example.demo.entity.enumerador.RolType;
import com.example.demo.entity.enumerador.UsuarioEntity;
import com.example.demo.repository.IUserV2Repository;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/winter")
public class WinterController {

    private CommentService commentService;
    private IUserV2Repository userV2Repository;

    @Autowired
    public WinterController(CommentService commentService,
                            IUserV2Repository userV2Repository) {
        this.commentService = commentService;
        this.userV2Repository = userV2Repository;
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
}
