package com.example.syrax_tournament_backend.controller;

import com.example.syrax_tournament_backend.dto.AdminMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public AdminMessage adminHome() {
        return new AdminMessage("Welcome, Admin!");
    }
}
