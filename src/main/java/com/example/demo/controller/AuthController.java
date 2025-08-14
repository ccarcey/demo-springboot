package com.example.demo.controller;

import com.example.demo.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String username, @RequestParam String password) {
        return authService.signin(username, password);
    }
}
