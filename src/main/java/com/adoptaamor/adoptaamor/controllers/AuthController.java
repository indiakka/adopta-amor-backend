package com.adoptaamor.adoptaamor.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adoptaamor.adoptaamor.payloads.LoginRequest;
import com.adoptaamor.adoptaamor.payloads.RegisterRequest;
import com.adoptaamor.adoptaamor.services.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173") // Ajusta el puerto según tu frontend
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService; // Cambié la variable para usar convenciones comunes en minúsculas

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return authService.login(request); // Devuelve directamente el resultado del servicio
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return authService.register(request); // Devuelve directamente el resultado del servicio
    }

}
