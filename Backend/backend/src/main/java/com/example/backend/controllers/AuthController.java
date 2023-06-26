package com.example.backend.controllers;

import com.example.backend.DTOs.UserDTO;
import com.example.backend.auth.JwtRequest;
import com.example.backend.auth.JwtResponse;
import com.example.backend.auth.RefreshJwtRequest;
import com.example.backend.entities.User;
import com.example.backend.services.auth.AuthService;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("signup")
    public void signup(@RequestBody UserDTO userDTO) {
        authService.signup(userDTO);
    }

    @PostMapping("login")
    public void login(@RequestBody JwtRequest authRequest, HttpServletRequest request, HttpServletResponse response) throws AuthException {
        response.addCookie(authService.login(authRequest));
//        response.addHeader("Access-Control-Allow-Origin", "http://localhost:5173");
//        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
//        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    @GetMapping("activate/{code}")
    public ResponseEntity<Boolean> activate(@PathVariable String code) {
        Boolean response = authService.activate(code);
        return ResponseEntity.ok(response);
    }

    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
}
