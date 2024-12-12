package com.nfc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nfc.entities.Users;
import com.nfc.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody Users user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        return userService.login(user.getEmail() , user.getPassword())
            .map(u -> ResponseEntity.ok("Login successful"))
            .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }
}
