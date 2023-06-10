package com.example.backend.components.taskcomponents;

import com.example.backend.DTOs.TaskDTO;
import com.example.backend.components.interfaces.ComponentCrud;
import com.example.backend.entities.Tasks;
import com.example.backend.entities.User;
import com.example.backend.repositories.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskComponentCRUD implements ComponentCrud<TaskDTO> {
    private final TasksRepository tasksRepository;

    @Override
    public List<TaskDTO> get(User user) {
        return tasksRepository.findAllByUser(user)
                .stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getById(Long id) {
        if (tasksRepository.findById(id).isEmpty()) {
            throw new NullPointerException();
        }
        return TaskMapper.toDto(tasksRepository.findById(id).get());
    }

    @Override
    public TaskDTO create(TaskDTO taskDTO, User user) {
        Tasks task = tasksRepository.save(TaskMapper.toEntity(taskDTO, user));
        return TaskMapper.toDto(task);
    }

    @Override
    public TaskDTO update(TaskDTO taskDTO, Long id, User user) {
        tasksRepository.findById(id).map(task -> {
            task.setName(taskDTO.getName());
            return null;
        }).orElseGet(() -> {
            tasksRepository.save(TaskMapper.toEntity(taskDTO, user));
            return taskDTO;
        });
        return null;
    }

    public Tasks getEntityById(Long id) {
        if (tasksRepository.findById(id).isEmpty()) {
            throw new NullPointerException();
        }
        return tasksRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        tasksRepository.deleteById(id);
    }

    static class TaskMapper {
        private static TaskDTO toDto(Tasks task) {
            return TaskDTO.builder()
                    .id(task.getId())
                    .name(task.getName())
                    .build();
        }

        private static Tasks toEntity(TaskDTO task, User user) {
            return Tasks.builder()
                    .name(task.getName())
                    .user(user)
                    .build();
        }
    }
}
