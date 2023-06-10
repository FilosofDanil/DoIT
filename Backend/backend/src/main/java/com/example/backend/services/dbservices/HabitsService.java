package com.example.backend.services.dbservices;

import com.example.backend.DTOs.HabitsDTO;
import com.example.backend.DTOs.TrackedDaysDTO;
import com.example.backend.components.habitscomponents.HabitsComponentCRUD;
import com.example.backend.components.habitscomponents.HabitsMarkingComponent;
import com.example.backend.components.habitscomponents.HabitsTrackingComponent;
import com.example.backend.components.usercomponents.UserAuthComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitsService implements DbaServiceInterface<HabitsDTO> {
    private final HabitsComponentCRUD habitsComponentCRUD;
    private final HabitsMarkingComponent habitsMarkingComponent;
    private final UserAuthComponent userAuthComponent;
    private final HabitsTrackingComponent habitsTrackingComponent;

    @Override
    public List<HabitsDTO> get(Authentication auth) {
        return habitsComponentCRUD.get(userAuthComponent.getUserByAuthorities(auth));
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

    public void mark(Long id) {
        habitsMarkingComponent.markIt(id);
    }

    public void unmark(Long id) {
        habitsMarkingComponent.unmarkIt(id);
    }

    public List<TrackedDaysDTO> getAllTracks(Long id) {
        return habitsTrackingComponent.getAllTracksByHabit(habitsComponentCRUD.getEntityById(id));
    }
}