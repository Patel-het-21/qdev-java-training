package com.employeedatamanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for authentication operations.
 * Handles login, logout, and session management.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login() {
        // Authentication logic would go here
        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout() {
        // Logout logic would go here
        return "redirect:/auth/login";
    }
}