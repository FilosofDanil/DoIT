package com.example.backend.services.dbservices;

import com.example.backend.DTOs.UserDTO;
import com.example.backend.components.usercomponents.UserAuthComponent;
import com.example.backend.components.usercomponents.UserCRUDComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserAuthComponent userAuthComponent;
    private final UserCRUDComponent userCRUDComponent;

    public UserDTO getProfile(Authentication authentication) {
        return userCRUDComponent.getById(userAuthComponent.getUserByAuthorities(authentication).getId());
    }
}
