package com.example.demo.service;

import com.example.demo.repository.IUserV2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private IUserV2Repository userV2Repository;

    @Autowired
    public UsuarioService(IUserV2Repository userV2Repository) {
        this.userV2Repository = userV2Repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userV2Repository.findByUsername(username);
    }
}
