package com.example.backend.services;

import com.example.backend.DTOs.TaskDTO;
import com.example.backend.components.taskcomponents.TaskComponentCRUD;
import com.example.backend.components.taskcomponents.TaskComponentDailyTasks;
import com.example.backend.components.taskcomponents.TaskComponentSubtasker;
import com.example.backend.components.usercomponents.UserAuthComponent;
import com.example.backend.services.dbservices.DbaServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements DbaServiceInterface<TaskDTO> {
    private final TaskComponentCRUD taskComponentCRUD;
    private final TaskComponentDailyTasks taskComponentDailyTasks;
    private final TaskComponentSubtasker taskComponentSubtasker;
    private final UserAuthComponent userAuthComponent;

    @Override
    public List<TaskDTO> get() {
        return taskComponentCRUD.get();
    }

    @Override
    public TaskDTO getById(Long id) {
        return taskComponentCRUD.getById(id);
    }

    @Override
    public TaskDTO create(TaskDTO taskDTO, Authentication auth) {
        return taskComponentCRUD.create(taskDTO, userAuthComponent.getUserByAuthorities(auth));
    }

    @Override
    public TaskDTO update(TaskDTO taskDTO, Long id, Authentication auth) {
        return taskComponentCRUD.update(taskDTO, id, userAuthComponent.getUserByAuthorities(auth));
    }

    @Override
    public void delete(Long id) {
        taskComponentCRUD.delete(id);
    }
}
