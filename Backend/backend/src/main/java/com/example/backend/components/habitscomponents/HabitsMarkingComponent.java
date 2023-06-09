package com.example.backend.components.habitscomponents;

import com.example.backend.components.interfaces.MarkingInterface;
import com.example.backend.entities.DailyTasks;
import com.example.backend.entities.TrackedDays;
import com.example.backend.repositories.TrackedDaysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class HabitsMarkingComponent implements MarkingInterface {
    private final TrackedDaysRepository trackedDaysRepository;

    @Override
    public void markIt(Long id) {
        trackedDaysRepository.save(marking(true, id));
    }

    @Override
    public void unmarkIt(Long id) {
        trackedDaysRepository.save(marking(false, id));
    }

    private TrackedDays marking(Boolean mark, Long id) {
        if (trackedDaysRepository.findById(id).isEmpty()) {
            throw new NullPointerException();
        }
        TrackedDays trackedDays = trackedDaysRepository.findById(id).get();
        trackedDays.setMarked(mark);
        return trackedDays;
    }
}
