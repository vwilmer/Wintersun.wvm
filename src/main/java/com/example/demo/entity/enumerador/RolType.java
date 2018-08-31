package com.example.demo.entity.enumerador;

import org.springframework.security.core.GrantedAuthority;

public enum RolType implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
