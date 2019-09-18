package com.inezpre5.thymeleaf_security.service;

import com.inezpre5.thymeleaf_security.model.Producto;
import com.inezpre5.thymeleaf_security.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public List<Producto> getList(){
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductoId(long id){
        return productoRepository.findById(id);
    }

    public Optional<Producto> getProductoNombre(String nombre){
        return productoRepository.findByNombre(nombre);
    }

    public void save(Producto producto){
        productoRepository.save(producto);
    }

    public void delete(long id){
        productoRepository.deleteById(id);
    }

    public boolean existsProductoId(long id){
        return productoRepository.existsById(id);
    }

    public boolean existsProductoNombre(String nombre){
        return productoRepository.existsByNombre(nombre);
    }
}
