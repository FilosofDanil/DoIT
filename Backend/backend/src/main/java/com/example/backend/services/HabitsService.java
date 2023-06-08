package com.example.backend.services;

import com.example.backend.DTOs.HabitsDTO;
import com.example.backend.components.habitscomponents.HabitsComponentCRUD;
import com.example.backend.components.habitscomponents.HabitsDividorComponent;
import com.example.backend.components.usercomponents.UserAuthComponent;
import com.example.backend.entities.User;
import com.example.backend.services.dbservices.DbaServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitsService implements DbaServiceInterface<HabitsDTO> {
    private final HabitsComponentCRUD habitsComponentCRUD;
    private final HabitsDividorComponent habitsDividorComponent;
    private final UserAuthComponent userAuthComponent;

    @Override
    public List<HabitsDTO> get() {
        return habitsComponentCRUD.get();
    }

    @Override
    public HabitsDTO getById(Long id) {
        return habitsComponentCRUD.getById(id);
    }

    @Override
    public HabitsDTO create(HabitsDTO habitsDTO, Authentication auth) {
        return habitsComponentCRUD.create(habitsDTO, userAuthComponent.getUserByAuthorities(auth));
    }

    @Override
    public HabitsDTO update(HabitsDTO habitsDTO, Long id, Authentication auth) {
        return habitsComponentCRUD.update(habitsDTO, id, userAuthComponent.getUserByAuthorities(auth));
    }

    @Override
    public void delete(Long id) {
        habitsComponentCRUD.delete(id);
    }
}
