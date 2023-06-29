package com.example.backend.components.habitscomponents;

import com.example.backend.entities.Habits;
import com.example.backend.entities.TrackedDays;
import com.example.backend.repositories.TrackedDaysRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class HabitsDividorComponent {
    private final TrackedDaysRepository trackedDaysRepository;

    @PersistenceContext
    private EntityManager entityManager;

    protected void divide(Habits habits) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        trackedDaysRepository.saveAll(formList(habits, calendar, habits.getDay_count()));
    }

    @Transactional
    protected void divide(Habits habits, Integer new_count) {
        int count = habits.getDay_count();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if (habits.getDay_count() > new_count) {
            for (int i = 0; i < new_count-1; i++) {
                calendar.add(Calendar.HOUR, 24);
            }
            entityManager.createNativeQuery(buildDeleteQuery(calendar, habits)).executeUpdate();
        } else if (habits.getDay_count() < new_count) {
            for (int i = 0; i < count; i++) {
                calendar.add(Calendar.HOUR, 24);
            }
            trackedDaysRepository.saveAll(formList(habits, calendar, new_count - count));
        }
    }

    private List<TrackedDays> formList(Habits habits, Calendar calendar, Integer count) {
        List<TrackedDays> trackedDaysList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            trackedDaysList.add(TrackedDays.builder()
                    .habit(habits)
                    .marked(false)
                    .markingDate(calendar.getTime())
                    .build());
            calendar.add(Calendar.HOUR, 24);
        }
        return trackedDaysList;
    }

    private String buildDeleteQuery(Calendar calendar, Habits habits) {
        String date = calendar.getTime().toString();
        StringBuilder query = new StringBuilder();
        query.append("delete from tracked_days where marking_day>='");
        query.append(date);
        query.append("' and habits_id = ");
        query.append(habits.getId());
        return query.toString();
    }
}
