package com.example.backend.components.taskcomponents;

import com.example.backend.DTOs.SubtaskDTO;
import com.example.backend.components.interfaces.MarkingInterface;
import com.example.backend.entities.Subtasks;
import com.example.backend.entities.Tasks;
import com.example.backend.repositories.SubTasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskComponentSubtasker implements MarkingInterface {
    private final SubTasksRepository subTasksRepository;

    public Subtasks createSubtask(Tasks task, String name) {
        Subtasks subtask = Subtasks.builder()
                .task(task)
                .name(name)
                .done(false)
                .build();
        subTasksRepository.save(subtask);
        return subtask;
    }

    public void markIt(Long id) {
        subTasksRepository.save(marking(true, id));
    }

    public void unmarkIt(Long id) {
        subTasksRepository.save(marking(false, id));
    }

    public List<SubtaskDTO> getAllSubtasks(Tasks task){
        return subTasksRepository.findAllByTask(task).stream().map(SubTaskMapper::toDto).collect(Collectors.toList());
    }

    private Subtasks marking(Boolean mark, Long id) {
        if (subTasksRepository.findById(id).isEmpty()) {
            throw new NullPointerException();
        }
        Subtasks subtask = subTasksRepository.findById(id).get();
        subtask.setDone(mark);
        return subtask;
    }

    static class SubTaskMapper {
        private static SubtaskDTO toDto(Subtasks subtask) {
            return SubtaskDTO.builder()
                    .id(subtask.getId())
                    .done(subtask.getDone())
                    .name(subtask.getName())
                    .build();
        }

    }
}
