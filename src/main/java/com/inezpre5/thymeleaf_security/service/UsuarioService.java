package com.inezpre5.thymeleaf_security.service;

import com.inezpre5.thymeleaf_security.model.Usuario;
import com.inezpre5.thymeleaf_security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> getList(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getById(long id){
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> getByNombre(String nombre){
        return usuarioRepository.findByNombre(nombre);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public boolean existsUsuarioId(long id){
        return usuarioRepository.existsById(id);
    }

    public boolean existsUsuarioNombre(String nombre){
        return usuarioRepository.existsByNombre(nombre);
    }
}
