package com.example.backend.components.taskcomponents;

import com.example.backend.DTOs.SubtaskDTO;
import com.example.backend.components.interfaces.MarkingInterface;
import com.example.backend.entities.DailyTasks;
import com.example.backend.entities.Subtasks;
import com.example.backend.entities.Tasks;
import com.example.backend.repositories.DailyTasksRepository;
import com.example.backend.repositories.SubTasksRepository;
import com.example.backend.repositories.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskComponentSubtasker implements MarkingInterface {
    private final SubTasksRepository subTasksRepository;
    private final DailyTasksRepository dailyTasksRepository;

    public Subtasks createSubtask(Tasks task, String name) {
        Subtasks subtask = Subtasks.builder()
                .task(task)
                .name(name)
                .done(false)
                .build();
        DailyTasks dailyTask = dailyTasksRepository.getDailyTasksByTask(subtask.getTask());
        dailyTask.setDone(false);
        dailyTasksRepository.save(dailyTask);
        subTasksRepository.save(subtask);
        return subtask;
    }

    public void updateSubtask(SubtaskDTO subtaskDTO, Long id) {
        if (subTasksRepository.findById(id).isPresent()) {
            subTasksRepository.findById(id).map(subtask -> {
                subtask.setName(subtaskDTO.getName());
                subTasksRepository.save(subtask);
                return null;
            });
        }
    }

    public void markIt(Long id) {
        subTasksRepository.save(marking(true, id));
    }

    public void unmarkIt(Long id) {
        subTasksRepository.save(marking(false, id));
    }

    public List<SubtaskDTO> getAllSubtasks(Tasks task) {
        return subTasksRepository.findAllByTask(task).stream().map(SubTaskMapper::toDto).collect(Collectors.toList());
    }

    private Subtasks marking(Boolean mark, Long id) {
        if (subTasksRepository.findById(id).isEmpty()) {
            throw new NullPointerException();
        }
        Subtasks subtask = subTasksRepository.findById(id).get();
        subtask.setDone(mark);
        check(subtask, mark);
        return subtask;
    }

    private void check(Subtasks subtask, Boolean mark) {
        List<Subtasks> list = subTasksRepository.findAllByTask(subtask.getTask());
        if (mark && !list.isEmpty()) {
            for (Subtasks s : list) {
                if (!s.getDone()) {
                    return;
                }
            }
            DailyTasks dailyTask = dailyTasksRepository.getDailyTasksByTask(subtask.getTask());
            dailyTask.setDone(true);
            dailyTasksRepository.save(dailyTask);
        } else {
            DailyTasks dailyTask = dailyTasksRepository.getDailyTasksByTask(subtask.getTask());
            dailyTask.setDone(false);
            dailyTasksRepository.save(dailyTask);
        }
    }

    public void deleteSubTasks(Long id) {
        Subtasks subtask = subTasksRepository.findById(id).get();
        subTasksRepository.deleteById(id);
        check(subtask, true);
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
