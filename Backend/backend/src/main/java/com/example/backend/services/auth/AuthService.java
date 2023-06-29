package com.example.backend.services.auth;

import com.example.backend.DTOs.UserDTO;
import com.example.backend.auth.*;
import com.example.backend.cookies.CookiesUtil;
import com.example.backend.entities.User;
import com.example.backend.repositories.UserRepository;
import com.example.backend.services.MailSender;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.Cookie;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;
    private final CookiesUtil cookiesUtil;
    @Autowired(required = false)
    private MailSender mailSender;

    public void signup(UserDTO userDTO) {
        User user = UserMapper.toUser(userDTO);
        user.setRoles(new HashSet<>(Set.of(Role.USER)));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setVerified(false);
        userRepository.save(user);
        sendActivationCodeAssistant(user);
    }

    public Cookie login(@NonNull JwtRequest authRequest) throws AuthException {
        final User user = userRepository.getUserByEmail(authRequest.getLogin())
                .orElseThrow(() -> new AuthException("User Not Found"));
        if (user.getPassword().equals(authRequest.getPassword()) && user.getVerified()) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            return cookiesUtil.createCookie(accessToken);
        } else {
            throw new AuthException("Invalid password or your account is not verified");
        }

    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.getUserByEmail(login)
                        .orElseThrow(() -> new AuthException("User Not Found"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtAuthentication getAuthInfo() {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User username = (User) auth.getPrincipal();
            String name = auth.getName();
            java.util.List<Role> roles = (java.util.List<Role>) auth.getAuthorities();
            Set<Role> setRoles = new HashSet<>();
            setRoles.addAll(roles);
            return new JwtAuthentication(username.getUsername(), name, setRoles);
        }
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    private void sendActivationCodeAssistant(User user) {
        mailSender.send(user.getEmail(), "Profile Verification", "Dear " + user.getProfile_name() + " " + user.getSurname() + "\n" +
                "We're glad to see, that you have chosen our service. \n" +
                "For a further partnership with you, it's required to verify your email \n" +
                "To activate your account, you just need to click the link below \n"
                + "http://localhost:5173/verify?verification=" + user.getActivationCode());
    }

    public Boolean activate(String code) {
            User user = userRepository.findByActivationCode(code);
            if (user == null) {
                throw new IllegalArgumentException();
            }
        user.setVerified(true);
        userRepository.save(user);
        return true;
    }

    static class UserMapper {
        private static User toUser(UserDTO user) {
            return User.builder()
                    .profile_name(user.getProfile_name())
                    .email(user.getEmail())
                    .surname(user.getSurname())
                    .phone(user.getPhone())
                    .password(user.getPassword())
                    .birthDate(user.getBirthDate())
                    .build();
        }
    }
}
