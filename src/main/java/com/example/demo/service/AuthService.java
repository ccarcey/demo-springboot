package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String signin(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        System.out.println("Password recibido: " + password);
        System.out.println("Password almacenado: " + user.getPassword());
        System.out.println("Rol del usuario: " + user.getRole());

        if (!passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("No coincide el password");
            throw new RuntimeException("Credenciales inválidas");
        }

        Role role = Role.valueOf(user.getRole().toString());
        return jwtService.generateToken(user.getUsername(), role);
    }
}