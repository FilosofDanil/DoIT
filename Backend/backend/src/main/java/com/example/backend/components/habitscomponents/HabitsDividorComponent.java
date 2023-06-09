package com.example.backend.components.habitscomponents;

import com.example.backend.entities.Habits;
import com.example.backend.entities.TrackedDays;
import com.example.backend.repositories.TrackedDaysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class HabitsDividorComponent {
    private final TrackedDaysRepository trackedDaysRepository;

    protected void divide(Habits habits) {
        List<TrackedDays> trackedDaysList = new ArrayList<>();
        int count = habits.getDay_count();
        for (int i = 0; i < count; i++) {
            trackedDaysList.add(TrackedDays.builder().build());
        }
        trackedDaysRepository.saveAll(trackedDaysList);
    }
}
