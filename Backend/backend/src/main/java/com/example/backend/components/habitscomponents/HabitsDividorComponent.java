package com.example.backend.components.habitscomponents;

import com.example.backend.entities.Habits;
import com.example.backend.repositories.TrackedDaysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HabitsDividorComponent {
    private final TrackedDaysRepository trackedDaysRepository;

    protected void divide(Habits habits) {

    }
}
