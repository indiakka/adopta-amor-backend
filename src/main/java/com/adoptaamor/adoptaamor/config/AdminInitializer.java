package com.adoptaamor.adoptaamor.config;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.adoptaamor.adoptaamor.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

import com.adoptaamor.adoptaamor.models.User;
import com.adoptaamor.adoptaamor.models.role.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class AdminInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        // Verificar si el administrador ya existe
        if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
            // Si no existe, crear el usuario administrador
            User admin = User.builder()
                    .name("Admin")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("1111")) // Cifra la contraseña 1111
                    .role(Role.ADMIN) // Asignar rol ADMIN
                    .build();

            userRepository.save(admin);
            System.out.println("Administrador creado: admin@gmail.com / 1111");
        }
    }
}
