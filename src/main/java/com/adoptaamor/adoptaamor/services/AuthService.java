package com.adoptaamor.adoptaamor.services;

import com.adoptaamor.adoptaamor.models.User;
import com.adoptaamor.adoptaamor.models.role.Role;
import com.adoptaamor.adoptaamor.payloads.AuthResponse;
import com.adoptaamor.adoptaamor.payloads.LoginRequest;
import com.adoptaamor.adoptaamor.payloads.RegisterRequest;
import com.adoptaamor.adoptaamor.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // Método de registro
    public ResponseEntity<String> register(RegisterRequest registerRequest) {
        // Verificar si el usuario ya existe
        Optional<User> existingUser = userRepository.findByEmail(registerRequest.getEmail());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>("Email already in use", HttpStatus.BAD_REQUEST);
        }

        // Cifrar la contraseña antes de guardar
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        // Crear el nuevo usuario
        User newUser = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(encodedPassword)
                .role(Role.USER) // Asigna el rol USER por defecto
                .build();

        userRepository.save(newUser);

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    // Método de login
    // Método de login en AuthService
    public ResponseEntity<AuthResponse> login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = jwtService.generateToken(user); // Ahora se genera el token correctamente

        AuthResponse authResponse = new AuthResponse(token, user.getId(), user.getName(), user.getRole().name());
        return ResponseEntity.ok(authResponse);
    }

}