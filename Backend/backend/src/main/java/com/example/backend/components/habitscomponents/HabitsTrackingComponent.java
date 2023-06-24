package com.example.backend.components.habitscomponents;

import com.example.backend.DTOs.TrackedDaysDTO;
import com.example.backend.comparators.TrackedComparator;
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
                .sorted(new TrackedComparator())
                .collect(Collectors.toList());
    }

    static class TrackedMapper {
        private static TrackedDaysDTO toDto(TrackedDays trackedDays) {
            return TrackedDaysDTO.builder()
                    .id(trackedDays.getId())
                    .marked(trackedDays.getMarked())
                    .marking_day(trackedDays.getMarkingDate())
                    .status(getStatus(trackedDays))
                    .date(trackedDays.getMarkingDate().toString().substring(0,10))
                    .build();
        }

        private static String getStatus(TrackedDays trackedDays) {
            Date today = new Date();
            today.setTime(today.getTime()-today.getHours()*3600000-today.getMinutes()*60000-today.getSeconds()*1000);
            Date trackedDate = trackedDays.getMarkingDate();
            trackedDate.setTime(trackedDate.getTime()-trackedDate.getHours()*3600000-trackedDate.getMinutes()*60000-trackedDate.getSeconds()*1000);
            if(trackedDate.getDate()== today.getDate()&&trackedDate.getMonth()== today.getMonth()&&trackedDate.getYear()== today.getYear()){
                return "today";
            }
            if (trackedDate.before(today)) {
                return "before";
            }
            if (trackedDate.after(today)) {
                return "after";
            }
            return "today";
        }
    }
}
