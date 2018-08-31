package com.example.demo.entity;

import lombok.Data;

@Data
public class Resultado {
    Integer personaId;
    String nombres;
    String apellidoPaterno;
    String apellidoMaterno;
    Integer oficinaId;
    Integer usuarioId;
    String usuarioLogin;
    Integer tipoJerarquiaId;
    String cargo;
    String correoCorporativo;
}
