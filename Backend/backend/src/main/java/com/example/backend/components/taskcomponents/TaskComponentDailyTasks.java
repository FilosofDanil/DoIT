package com.example.backend.components.taskcomponents;

import com.example.backend.components.interfaces.MarkingInterface;
import com.example.backend.entities.DailyTasks;
import com.example.backend.entities.Tasks;
import com.example.backend.repositories.DailyTasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TaskComponentDailyTasks implements MarkingInterface {
    private final DailyTasksRepository dailyTasksRepository;

    public DailyTasks createDailyTask(Tasks task) {
        Date today = new Date();
        DailyTasks dailyTask = DailyTasks.builder()
                .task(task)
                .today(today)
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

    private DailyTasks marking(Boolean mark, Long id) {
        if (dailyTasksRepository.findById(id).isEmpty()) {
            throw new NullPointerException();
        }
        DailyTasks dailyTask = dailyTasksRepository.findById(id).get();
        dailyTask.setDone(mark);
        return dailyTask;
    }
}
