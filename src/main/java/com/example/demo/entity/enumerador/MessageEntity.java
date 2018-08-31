package com.example.demo.entity.enumerador;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wv_mensajes")
@Data
public class MessageEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String text;
    private String tag;

    private UsuarioEntity author;
}
