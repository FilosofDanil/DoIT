package com.example.backend.components.taskcomponents;

import com.example.backend.entities.DailyTasks;
import com.example.backend.entities.Tasks;
import com.example.backend.repositories.DailyTasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskComponentDailyTasks {
    private final DailyTasksRepository dailyTasksRepository;

    public void createDailyTask(Tasks task){

    }
}
