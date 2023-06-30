package com.example.backend.components.usercomponents;

import com.example.backend.entities.DailyTasks;
import com.example.backend.entities.Habits;
import com.example.backend.entities.Tasks;
import com.example.backend.entities.User;
import com.example.backend.repositories.DailyTasksRepository;
import com.example.backend.repositories.HabitsRepository;
import com.example.backend.repositories.TasksRepository;
import com.example.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UserCalcComponent {
    private final TasksRepository tasksRepository;
    private final DailyTasksRepository dailyTasksRepository;
    private final UserRepository userRepository;

    public Integer calculateCompletedTasks(User user) {
        //        List<Tasks> tasks = tasksRepository.findAllByUser(user);
//        if (!tasks.isEmpty()) {
//            for (Tasks task : tasks) {
//                DailyTasks dailyTask = dailyTasksRepository.getDailyTasksByTask(task);
//                if (dailyTask != null) {
//                    if (dailyTask.getDone()) {
//                        calc += 1;
//                    }
//                }
//            }
//        }
        return userRepository.undoneTasks(user.getId(), true);
    }

    public Integer calculateUncompletedTasks(User user) {
        //        List<Tasks> tasks = tasksRepository.findAllByUser(user);
//        if (!tasks.isEmpty()) {
//            for (Tasks task : tasks) {
//                DailyTasks dailyTask = dailyTasksRepository.getDailyTasksByTask(task);
//                if (dailyTask != null) {
//                    if (!dailyTask.getDone()) {
//                        calc += 1;
//                    }
//                }
//            }
//        }
        return userRepository.undoneTasks(user.getId(), false);
    }

    public Integer calculateCompletedHabits(User user) {
        //        List<Habits> habits = habitsRepository.findAllByUser(user);
//        if (!habits.isEmpty()) {
//            for (Habits habit : habits) {
//                if (habit.getCompleted()) {
//                    calc += 1;
//                }
//            }
//        }
        return userRepository.undoneHabits(user.getId(), true);
    }

    public Integer calculateUncompletedHabits(User user) {
        //        List<Habits> habits = habitsRepository.findAllByUser(user);
//        if (!habits.isEmpty()) {
//            for (Habits habit : habits) {
//                if (!habit.getCompleted()) {
//                    calc += 1;
//                }
//            }
//        }
        return userRepository.undoneHabits(user.getId(), false);
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

    public Integer calculateUncompletedTodayTasks(User user) {
        int calc = 0;
        List<Tasks> tasks = tasksRepository.findAllByUser(user);
        if (!tasks.isEmpty()) {
            for (Tasks task : tasks) {
                DailyTasks dailyTask = dailyTasksRepository.getDailyTasksByTask(task);
                Date today = new Date();
                today.setTime(today.getTime()-today.getHours()*3600000-today.getMinutes()*60000-today.getSeconds()*1000);
                Date trackedDate = dailyTask.getToday();
                trackedDate.setTime(trackedDate.getTime()-trackedDate.getHours()*3600000-trackedDate.getMinutes()*60000-trackedDate.getSeconds()*1000);
                if (trackedDate.getDate() == today.getDate() && trackedDate.getMonth() == today.getMonth() && trackedDate.getYear() == today.getYear()) {
                    if (!dailyTask.getDone()) {
                        calc += 1;
                    }
                }
            }
        }
        return calc;
    }
}
