package com.example.backend.components.usercomponents;

import com.example.backend.entities.User;
import com.example.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthComponent {
    private final UserRepository userRepository;

    public User getUserByAuthorities(Authentication auth) {
        String username = (String) auth.getPrincipal();
        if (userRepository.getUserByEmail(username).isEmpty()) {
            throw new RuntimeException();
        }
        return userRepository.getUserByEmail(username).get();
    }
}
