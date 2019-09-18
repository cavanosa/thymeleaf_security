package com.inezpre5.thymeleaf_security.repository;

import com.inezpre5.thymeleaf_security.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByNombre(String nombre);
    Optional<Producto> findByNombre(String nombre);
}