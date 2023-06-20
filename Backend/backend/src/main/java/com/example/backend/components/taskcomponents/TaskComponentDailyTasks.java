package com.example.backend.components.taskcomponents;

import com.example.backend.DTOs.TaskDTO;
import com.example.backend.components.interfaces.MarkingInterface;
import com.example.backend.entities.DailyTasks;
import com.example.backend.entities.Subtasks;
import com.example.backend.entities.Tasks;
import com.example.backend.repositories.DailyTasksRepository;
import com.example.backend.repositories.SubTasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class TaskComponentDailyTasks implements MarkingInterface {
    private final DailyTasksRepository dailyTasksRepository;
    private final SubTasksRepository subTasksRepository;

    public DailyTasks createDailyTask(Tasks task) {
        Date today = new Date();
        DailyTasks dailyTask = DailyTasks.builder()
                .task(task)
                .today(today)
                .done(false)
                .build();
        dailyTasksRepository.save(dailyTask);
        return dailyTask;
    }

    public DailyTasks createDailyTask(Tasks task, Date date) {
        DailyTasks dailyTask = DailyTasks.builder()
                .task(task)
                .today(date)
                .done(false)
                .build();
        dailyTasksRepository.save(dailyTask);
        return dailyTask;
    }


    public void markIt(Long id) {
        dailyTasksRepository.save(marking(true, id));
    }

    public void unmarkIt(Long id) {
        dailyTasksRepository.save(marking(false, id));
    }

    public DailyTasks getByTask(Tasks task) {
        return dailyTasksRepository.getDailyTasksByTask(task);
    }

    public void check(Tasks tasks, Boolean mark) {
        List<Subtasks> list = subTasksRepository.findAllByTask(tasks);
        list.forEach(subtasks -> {
            subtasks.setDone(mark);
        });
        subTasksRepository.saveAll(list);
    }

    private DailyTasks marking(Boolean mark, Long id) {
        if (dailyTasksRepository.findById(id).isEmpty()) {
            throw new NullPointerException();
        }
        DailyTasks dailyTask = dailyTasksRepository.findById(id).get();
        dailyTask.setDone(mark);
        check(dailyTask.getTask(), mark);
        return dailyTask;
    }
}
