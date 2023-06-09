package com.example.backend.services.dbservices;

import com.example.backend.DTOs.TaskDTO;
import com.example.backend.components.taskcomponents.TaskComponentCRUD;
import com.example.backend.components.taskcomponents.TaskComponentDailyTasks;
import com.example.backend.components.taskcomponents.TaskComponentSubtasker;
import com.example.backend.components.usercomponents.UserAuthComponent;
import com.example.backend.entities.DailyTasks;
import com.example.backend.entities.Subtasks;
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
    public List<TaskDTO> get(Authentication auth) {
        return taskComponentCRUD.get(userAuthComponent.getUserByAuthorities(auth));
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

    public Subtasks createSubTask(Long id, String name) {
        return taskComponentSubtasker.createSubtask(taskComponentCRUD.getEntityById(id), name);
    }

    public DailyTasks createDailyTask(Long id) {
        return taskComponentDailyTasks.createDailyTask(taskComponentCRUD.getEntityById(id));
    }

    public void markDailyTask(Long id) {
        taskComponentDailyTasks.markIt(id);
    }

    public void unmarkDailyTask(Long id) {
        taskComponentDailyTasks.unmarkIt(id);
    }

    public void markSubTask(Long id) {
        taskComponentSubtasker.markIt(id);
    }

    public void unmarkSubTask(Long id) {
        taskComponentSubtasker.unmarkIt(id);
    }
}
