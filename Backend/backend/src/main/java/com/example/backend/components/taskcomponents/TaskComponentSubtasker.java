package com.example.backend.components.taskcomponents;

import com.example.backend.entities.Tasks;
import com.example.backend.repositories.SubTasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskComponentSubtasker {
    private final SubTasksRepository subTasksRepository;

    public void createSubtask(Tasks task){

    }
}
