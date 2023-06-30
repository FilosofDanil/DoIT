package com.example.backend.services.dbservices;

import com.example.backend.DTOs.HabitsDTO;
import com.example.backend.DTOs.TrackedDaysDTO;
import com.example.backend.components.habitscomponents.HabitsComponentCRUD;
import com.example.backend.components.habitscomponents.HabitsMarkingComponent;
import com.example.backend.components.habitscomponents.HabitsTrackingComponent;
import com.example.backend.components.interfaces.ComponentCrud;
import com.example.backend.components.interfaces.ComponentEntityCRUD;
import com.example.backend.components.interfaces.MarkingInterface;
import com.example.backend.components.usercomponents.UserAuthComponent;
import com.example.backend.entities.Habits;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitsService implements DbaServiceInterface<HabitsDTO> {
    private final ComponentCrud<HabitsDTO> habitsComponentCRUD;
    private final ComponentEntityCRUD<Habits> habitsComponentEntityCRUD;
    private final MarkingInterface habitsMarkingComponent;
    private final UserAuthComponent userAuthComponent;
    private final HabitsTrackingComponent habitsTrackingComponent;

    @Override
    public List<HabitsDTO> get(Authentication auth) {
        List<HabitsDTO> list = habitsComponentCRUD.get(userAuthComponent.getUserByAuthorities(auth));
        list.forEach(habitsDTO -> habitsDTO.setTracks(getAllTracks(habitsDTO.getId())));
        return list;
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

    private List<TrackedDaysDTO> getAllTracks(Long id) {

        return habitsTrackingComponent.getAllTracksByHabit(habitsComponentEntityCRUD.getEntityById(id));
    }
}
