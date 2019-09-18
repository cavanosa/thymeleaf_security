package com.inezpre5.thymeleaf_security.repository;

import com.inezpre5.thymeleaf_security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByNombre(String nombre);
    Optional<Usuario> findByNombre(String nombre);
}
