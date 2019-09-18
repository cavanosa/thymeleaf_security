package com.inezpre5.thymeleaf_security.controller;

import com.inezpre5.thymeleaf_security.model.Producto;
import com.inezpre5.thymeleaf_security.service.ProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping
    public ModelAndView lista(){
        ModelAndView mv = new ModelAndView("producto/lista");
        List<Producto> lista = productoService.getList();
        mv.addObject("productos", lista);
        return mv;
    }

    @GetMapping("/detalle/{id}")
    public ModelAndView detalle(@PathVariable("id") long id){
        if(!productoService.existsProductoId(id))
            return new ModelAndView("redirect:/producto");
        ModelAndView mv = new ModelAndView("/producto/detalle");
        Producto producto = productoService.getProductoId(id).get();
        mv.addObject("producto", producto);
        return mv;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/nuevo")
    public String nuevo(){
        return "producto/nuevo";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ModelAndView crear(String nombre, double precio){
        if(StringUtils.isBlank(nombre)){
            ModelAndView mv = new ModelAndView();
            mv.addObject("error", "El nombre no puede estar vacío");
            mv.setViewName("producto/nuevo");
            return mv;
        }
        if(precio < 1){
            ModelAndView mv = new ModelAndView();
            mv.addObject("error", "El precio debe ser mayor que cero");
            mv.setViewName("producto/nuevo");
            return mv;
        }
        if(productoService.existsProductoNombre(nombre)){
            ModelAndView mv = new ModelAndView();
            mv.addObject("error", "Ese nombre ya existe");
            mv.setViewName("producto/nuevo");
            return mv;
        }
        Producto producto = new Producto(nombre, precio);
        productoService.save(producto);
        return new ModelAndView("redirect:/producto");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{id}")
    public ModelAndView editar (@PathVariable("id") long id){
        if(!productoService.existsProductoId(id)){
            return new ModelAndView("redirect:/producto");
        }
        ModelAndView mv = new ModelAndView();
        Producto producto = productoService.getProductoId(id).get();
        mv.addObject("producto", producto);
        mv.setViewName("producto/editar");
        return mv;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public ModelAndView actualizar(@RequestParam("nombre") String nombre, @RequestParam("precio") double precio, @RequestParam("id")long id){
        if(!productoService.existsProductoId(id)){
            ModelAndView mv = new ModelAndView();
            mv.setViewName("producto/lista");
            return mv;
        }
        Producto producto = productoService.getProductoId(id).get();
        if(productoService.existsProductoNombre(nombre) && productoService.getProductoNombre(nombre).get().getId()!=id){
            ModelAndView mv = new ModelAndView();
            mv.addObject("error", "Ese nombre ya existe");
            mv.addObject("producto", producto);
            mv.setViewName("producto/editar");
            return mv;
        }
        if(StringUtils.isBlank(nombre)){
            ModelAndView mv = new ModelAndView();
            mv.addObject("error", "El nombre no puede estar vacío");
            mv.addObject("producto", producto);
            mv.setViewName("producto/editar");
            return mv;
        }
        if(precio < 1){
            ModelAndView mv = new ModelAndView();
            mv.addObject("error", "El precio debe ser mayor que 0");
            mv.addObject("producto", producto);
            mv.setViewName("producto/editar");
            return mv;
        }
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        productoService.save(producto);
        return new ModelAndView("redirect:/producto");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/eliminar/{id}")
    public ModelAndView eliminar(@PathVariable("id") long id){
        if(productoService.existsProductoId(id)) {
            productoService.delete(id);
            return new ModelAndView("redirect:/producto");
        }
        return null;
    }
}
