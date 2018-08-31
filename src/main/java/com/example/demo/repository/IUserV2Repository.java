package com.example.demo.repository;

import com.example.demo.entity.enumerador.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserV2Repository extends JpaRepository<UsuarioEntity, Long> {
    UsuarioEntity findByUsername(String username);
    UsuarioEntity findByUsernameAndPassword(String username, String password);
    UsuarioEntity findByResetToken(String resetToken);
}
