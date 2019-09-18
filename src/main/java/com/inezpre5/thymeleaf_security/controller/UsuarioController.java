package com.inezpre5.thymeleaf_security.controller;

import com.inezpre5.thymeleaf_security.enums.RolNombre;
import com.inezpre5.thymeleaf_security.model.Rol;
import com.inezpre5.thymeleaf_security.model.Usuario;
import com.inezpre5.thymeleaf_security.service.RolService;
import com.inezpre5.thymeleaf_security.service.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String nuevo(){
        return "registro";
    }

    @PostMapping("/registrar")
    public ModelAndView registrar(String nombre, String password){
        ModelAndView mv = new ModelAndView();
        if(StringUtils.isBlank(nombre)){
            mv.addObject("error", "ese nombre no es válido");
            mv.setViewName("registro");
            return mv;
        }
        if(StringUtils.isBlank(password)){
            mv.addObject("error", "esa contraseña no es válida");
            mv.setViewName("registro");
            return mv;
        }
        if(usuarioService.existsUsuarioNombre(nombre)){
            mv.addObject("error", "ese nombre ya existe");
            mv.setViewName("registro");
            return mv;
        }
        String passwordEnc = passwordEncoder.encode(password);
        Usuario usuario = new Usuario(nombre, passwordEnc);
        Rol rol = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
        Set<Rol> roles = new HashSet<>();
        roles.add(rol);
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        mv.addObject("registroOK", "Cuenta creada, ingresa tus credenciales para iniciar sesión");
        mv.setViewName("login");
        return mv;
    }
}
