package com.example.backend.components.habitscomponents;

import com.example.backend.DTOs.HabitsDTO;
import com.example.backend.components.interfaces.ComponentCrud;
import com.example.backend.components.taskcomponents.TaskComponentCRUD;
import com.example.backend.entities.Habits;
import com.example.backend.entities.User;
import com.example.backend.repositories.HabitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HabitsComponentCRUD implements ComponentCrud<HabitsDTO> {
    private final HabitsRepository habitsRepository;
    private final HabitsDividorComponent habitsDividorComponent;

    @Override
    public List<HabitsDTO> get() {
        List<HabitsDTO> habits = new ArrayList<>();
        habitsRepository.findAll().forEach(habit -> habits.add(HabitsMapper.toDto(habit)));
        return habits;
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
        habitsRepository.findById(id).map(habit -> {
            habit.setName(habitsDTO.getName());
            habit.setDescription(habitsDTO.getDescription());
            habit.setDay_count(habitsDTO.getDay_count());
            return null;
        }).orElseGet(() -> {
            habitsRepository.save(HabitsMapper.toEntity(habitsDTO, user));
            return habitsDTO;
        });
        return null;
    }

    @Override
    public void delete(Long id) {
        habitsRepository.deleteById(id);
    }

    static class HabitsMapper {
        private static HabitsDTO toDto(Habits habit) {
            return HabitsDTO.builder()
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
                    .completed(habit.getCompleted())
                    .user(user)
                    .build();
        }
    }
}
