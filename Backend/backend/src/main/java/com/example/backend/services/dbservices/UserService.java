package com.example.backend.services.dbservices;

import com.example.backend.DTOs.UserDTO;
import com.example.backend.components.interfaces.ComponentCrud;
import com.example.backend.components.usercomponents.UserAuthComponent;
import com.example.backend.components.usercomponents.UserCalcComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ComponentCrud<UserDTO> userCRUDComponent;
    private final UserAuthComponent userAuthComponent;
    private final UserCalcComponent userCalcComponent;

    public UserDTO getProfile(Authentication authentication) {
        return userCRUDComponent.getById(userAuthComponent.getUserByAuthorities(authentication).getId());
    }

    public Integer calculateCompletedTasks(Authentication authentication) {
        return userCalcComponent.calculateCompletedTasks(userAuthComponent.getUserByAuthorities(authentication));
    }

    public Integer calculateUncompletedTasks(Authentication authentication) {
        return userCalcComponent.calculateUncompletedTasks(userAuthComponent.getUserByAuthorities(authentication));
    }

    public Integer calculateCompletedHabits(Authentication authentication) {
        return userCalcComponent.calculateCompletedHabits(userAuthComponent.getUserByAuthorities(authentication));
    }

    public Integer calculateUncompletedHabits(Authentication authentication) {
        return userCalcComponent.calculateUncompletedHabits(userAuthComponent.getUserByAuthorities(authentication));
    }

    public Integer calculateAge(Authentication authentication) {
        return userCalcComponent.calculateAge(userAuthComponent.getUserByAuthorities(authentication));
    }
}
