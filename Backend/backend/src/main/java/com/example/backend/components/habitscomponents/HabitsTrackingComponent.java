package com.example.backend.components.habitscomponents;

import com.example.backend.DTOs.TrackedDaysDTO;
import com.example.backend.entities.Habits;
import com.example.backend.entities.TrackedDays;
import com.example.backend.repositories.TrackedDaysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HabitsTrackingComponent {
    private final TrackedDaysRepository trackedDaysRepository;

    public List<TrackedDaysDTO> getAllTracksByHabit(Habits habit) {
        return trackedDaysRepository.findAllByHabit(habit)
                .stream()
                .map(TrackedMapper::toDto)
                .collect(Collectors.toList());
    }

    static class TrackedMapper {
        private static TrackedDaysDTO toDto(TrackedDays trackedDays) {
            return TrackedDaysDTO.builder()
                    .marked(trackedDays.getMarked())
                    .marking_day(trackedDays.getMarkingDate())
                    .build();
        }
    }
}
