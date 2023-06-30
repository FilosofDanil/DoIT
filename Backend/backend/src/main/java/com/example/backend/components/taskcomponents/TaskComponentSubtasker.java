package com.example.backend.components.taskcomponents;

import com.example.backend.DTOs.SubtaskDTO;
import com.example.backend.components.interfaces.ComponentCrud;
import com.example.backend.components.interfaces.MarkingInterface;
import com.example.backend.entities.DailyTasks;
import com.example.backend.entities.Subtasks;
import com.example.backend.entities.Tasks;
import com.example.backend.entities.User;
import com.example.backend.repositories.DailyTasksRepository;
import com.example.backend.repositories.SubTasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskComponentSubtasker implements MarkingInterface, ComponentCrud<SubtaskDTO> {
    private final SubTasksRepository subTasksRepository;
    private final DailyTasksRepository dailyTasksRepository;

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

    @Override
    public List<SubtaskDTO> get(User user) {
        return  subTasksRepository.findAllSubtasks().stream().map(SubTaskMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SubtaskDTO getById(Long id) {
        return SubTaskMapper.toDto(subTasksRepository.findById(id).get());
    }

    @Override
    public SubtaskDTO create(SubtaskDTO subtaskDTO, User user) {
        Subtasks subtask = Subtasks.builder()
                .task(subtaskDTO.getTask())
                .name(subtaskDTO.getName())
                .done(false)
                .build();
        DailyTasks dailyTask = dailyTasksRepository.getDailyTasksByTask(subtask.getTask());
        dailyTask.setDone(false);
        dailyTasksRepository.save(dailyTask);
        subTasksRepository.save(subtask);
        return SubTaskMapper.toDto(subtask);
    }

    @Override
    public SubtaskDTO update(SubtaskDTO subtaskDTO, Long id, User user) {
        if (subTasksRepository.findById(id).isPresent()) {
            subTasksRepository.findById(id).map(subtask -> {
                subtask.setName(subtaskDTO.getName());
                subTasksRepository.save(subtask);
                return null;
            });
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Subtasks subtask = subTasksRepository.findById(id).get();
        subTasksRepository.deleteById(id);
        check(subtask, true);
    }

    static class SubTaskMapper {
        protected static SubtaskDTO toDto(Subtasks subtask) {
            return SubtaskDTO.builder()
                    .id(subtask.getId())
                    .done(subtask.getDone())
                    .name(subtask.getName())
                    .build();
        }

        protected static Subtasks toEntity(SubtaskDTO subtaskDTO) {
            return Subtasks.builder()
                    .id(subtaskDTO.getId())
                    .done(subtaskDTO.getDone())
                    .name(subtaskDTO.getName())
                    .build();
        }

    }
}
