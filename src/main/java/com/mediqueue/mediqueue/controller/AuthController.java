package com.mediqueue.mediqueue.controller;

import com.mediqueue.mediqueue.model.User;
import com.mediqueue.mediqueue.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    // Register API
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody Map<String, String> request) {
        try {
            User user = authService.register(
                    request.get("fullName"),
                    request.get("email"),
                    request.get("password"),
                    request.get("phone"),
                    User.Role.valueOf(
                            request.get("role").toUpperCase())
            );
            return ResponseEntity.ok(
                    Map.of(
                            "message", "Registered successfully!",
                            "userId", user.getId(),
                            "email", user.getEmail()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Login API
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> request) {
        try {
            User user = authService.findByEmail(
                    request.get("email"));
            return ResponseEntity.ok(
                    Map.of(
                            "message", "Login successful!",
                            "userId", user.getId(),
                            "role", user.getRole(),
                            "fullName", user.getFullName()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
