package com.businessgroup.pos_saas.controller;

import com.businessgroup.pos_saas.config.JwtUtils;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if ("admin".equals(request.getUsername()) && "admin123".equals(request.getPassword())) {
            String token = jwtUtils.generateToken(request.getUsername());
            return ResponseEntity.ok(new LoginResponse("Login exitoso", token));
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    static class LoginRequest {
        @NotBlank
        private String username;
        @NotBlank
        private String password;

        // Getters y setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    static class LoginResponse {
        private String message;
        private String token;

        public LoginResponse(String message, String token) {
            this.message = message;
            this.token = token;
        }

        public String getMessage() {
            return message;
        }

        public String getToken() {
            return token;
        }
    }
}