package com.inezpre5.thymeleaf_security.config;

import com.inezpre5.thymeleaf_security.enums.RolNombre;
import com.inezpre5.thymeleaf_security.model.Rol;
import com.inezpre5.thymeleaf_security.model.Usuario;
import com.inezpre5.thymeleaf_security.service.RolService;
import com.inezpre5.thymeleaf_security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdminCreate implements CommandLineRunner {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        /*Rol rolAdmin = new Rol();
        rolAdmin.setRolNombre(RolNombre.ROLE_ADMIN);
        Rol rolUser = new Rol();
        rolUser.setRolNombre(RolNombre.ROLE_USER);
        Usuario adminUser = new Usuario("admin", passwordEncoder.encode("admin"));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolAdmin);
        roles.add(rolUser);
        adminUser.setRoles(roles);

        //insert
        rolService.save(rolAdmin);
        rolService.save(rolUser);
        usuarioService.save(adminUser);*/
    }
}
