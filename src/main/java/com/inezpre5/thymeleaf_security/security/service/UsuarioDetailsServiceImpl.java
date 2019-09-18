package com.inezpre5.thymeleaf_security.security.service;

import com.inezpre5.thymeleaf_security.model.Usuario;
import com.inezpre5.thymeleaf_security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByNombre(nombre).orElseThrow(() -> new UsernameNotFoundException(nombre));
        return UsuarioPrincipal.build(usuario);
    }
}
