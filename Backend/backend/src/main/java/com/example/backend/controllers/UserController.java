package com.example.backend.controllers;

import com.example.backend.DTOs.UserDTO;
import com.example.backend.services.dbservices.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/profile")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public UserDTO getProfile() {
        return userService.getProfile(SecurityContextHolder.getContext().getAuthentication());
    }
}
