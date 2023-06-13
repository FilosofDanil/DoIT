package com.example.backend.components.usercomponents;

import com.example.backend.entities.DailyTasks;
import com.example.backend.entities.Habits;
import com.example.backend.entities.Tasks;
import com.example.backend.entities.User;
import com.example.backend.repositories.DailyTasksRepository;
import com.example.backend.repositories.HabitsRepository;
import com.example.backend.repositories.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UserCalcComponent {
    private final TasksRepository tasksRepository;
    private final DailyTasksRepository dailyTasksRepository;
    private final HabitsRepository habitsRepository;

    public Integer calculateCompletedTasks(User user) {
        int calc = 0;
        List<Tasks> tasks = tasksRepository.findAllByUser(user);
        if (!tasks.isEmpty()) {
            for (Tasks task : tasks) {
                DailyTasks dailyTask = dailyTasksRepository.getDailyTasksByTask(task);
                if (dailyTask != null) {
                    if (dailyTask.getDone()) {
                        calc += 1;
                    }
                }
            }
        }

        return calc;
    }

    public Integer calculateUncompletedTasks(User user) {
        int calc = 0;
        List<Tasks> tasks = tasksRepository.findAllByUser(user);
        if (!tasks.isEmpty()) {
            for (Tasks task : tasks) {
                DailyTasks dailyTask = dailyTasksRepository.getDailyTasksByTask(task);
                if (dailyTask != null) {
                    if (!dailyTask.getDone()) {
                        calc += 1;
                    }
                }
            }
        }
        return calc;
    }

    public Integer calculateCompletedHabits(User user) {
        int calc = 0;
        List<Habits> habits = habitsRepository.findAllByUser(user);
        if (!habits.isEmpty()) {
            for (Habits habit : habits) {
                if (habit.getCompleted()) {
                    calc += 1;
                }
            }
        }
        return calc;
    }

    public Integer calculateUncompletedHabits(User user) {
        int calc = 0;
        List<Habits> habits = habitsRepository.findAllByUser(user);
        if (!habits.isEmpty()) {
            for (Habits habit : habits) {
                if (!habit.getCompleted()) {
                    calc += 1;
                }
            }
        }
        return calc;
    }

    public Integer calculateAge(User user) {
        int years = 0;
        Date date = new Date();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(user.getBirthDate());
        while (calendar1.getTime().getYear() < calendar2.getTime().getYear()) {
            years += 1;
            calendar1.add(Calendar.YEAR, 1);
        }
        return years;
    }
}
