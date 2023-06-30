package com.example.backend.components.habitscomponents;

import com.example.backend.DTOs.HabitsDTO;
import com.example.backend.components.interfaces.ComponentCrud;
import com.example.backend.components.interfaces.ComponentEntityCRUD;
import com.example.backend.components.taskcomponents.TaskComponentCRUD;
import com.example.backend.entities.Habits;
import com.example.backend.entities.TrackedDays;
import com.example.backend.entities.User;
import com.example.backend.repositories.HabitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HabitsComponentCRUD implements ComponentCrud<HabitsDTO>, ComponentEntityCRUD<Habits> {
    private final HabitsRepository habitsRepository;
    private final HabitsDividorComponent habitsDividorComponent;

    @Override
    public List<HabitsDTO> get(User user) {
        return habitsRepository.findAllByUser(user)
                .stream()
                .map(HabitsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public HabitsDTO getById(Long id) {
        if (habitsRepository.findById(id).isEmpty()) {
            throw new NullPointerException();
        }
        return HabitsMapper.toDto(habitsRepository.findById(id).get());
    }

    @Override
    public HabitsDTO create(HabitsDTO habitsDTO, User user) {
        Habits habit = HabitsMapper.toEntity(habitsDTO, user);
        habitsRepository.save(habit);
        habitsDividorComponent.divide(habit);
        return habitsDTO;
    }

    @Override
    public HabitsDTO update(HabitsDTO habitsDTO, Long id, User user) {
        if(habitsRepository.findById(id).isEmpty()){
            habitsRepository.save(HabitsMapper.toEntity(habitsDTO, user));
            habitsDividorComponent.divide(HabitsMapper.toEntity(habitsDTO, user));
            return habitsDTO;
        }
        habitsRepository.findById(id).map(habit -> {
            habit.setName(habitsDTO.getName());
            habit.setDescription(habitsDTO.getDescription());
            habitsDividorComponent.divide(habit, habitsDTO.getDay_count());
            habit.setDay_count(habitsDTO.getDay_count());
            habitsRepository.save(habit);
            return null;
        });
        return null;
    }

    @Override
    public void delete(Long id) {
        habitsRepository.deleteById(id);
    }

    @Override
    public Habits getEntityById(Long id) {
        if (habitsRepository.findById(id).isEmpty()) {
            throw new NullPointerException();
        }
        return habitsRepository.findById(id).get();
    }

    static class HabitsMapper {
        private static HabitsDTO toDto(Habits habit) {
            return HabitsDTO.builder()
                    .id(habit.getId())
                    .name(habit.getName())
                    .description(habit.getDescription())
                    .day_count(habit.getDay_count())
                    .completed(habit.getCompleted())
                    .build();
        }

        private static Habits toEntity(HabitsDTO habit, User user) {
            return Habits.builder()
                    .name(habit.getName())
                    .description(habit.getDescription())
                    .day_count(habit.getDay_count())
                    .completed(false)
                    .user(user)
                    .build();
        }
    }
}
