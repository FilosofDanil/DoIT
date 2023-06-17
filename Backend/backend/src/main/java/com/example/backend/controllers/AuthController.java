package com.example.backend.controllers;

import com.example.backend.DTOs.UserDTO;
import com.example.backend.auth.JwtRequest;
import com.example.backend.auth.JwtResponse;
import com.example.backend.auth.RefreshJwtRequest;
import com.example.backend.services.auth.AuthService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.*;

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

    }

    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
}
