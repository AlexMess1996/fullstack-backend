package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.model.User;
import com.example.fullstack_backend.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Adjust the port if necessary
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Optional<User> userOpt = userService.authenticate(username, password);
        Map<String, String> response = new HashMap<>();
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            response.put("status", "success");
            response.put("fullName", user.getFullName());
        } else {
            response.put("status", "error");
            response.put("message", "Invalid credentials");
        }
        return response;
    }
}