package com.example.backend.services.dbservices;

import com.example.backend.DTOs.SubtaskDTO;
import com.example.backend.DTOs.TaskDTO;
import com.example.backend.components.taskcomponents.TaskComponentCRUD;
import com.example.backend.components.taskcomponents.TaskComponentDailyTasks;
import com.example.backend.components.taskcomponents.TaskComponentSubtasker;
import com.example.backend.components.usercomponents.UserAuthComponent;
import com.example.backend.entities.DailyTasks;
import com.example.backend.entities.Subtasks;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService implements DbaServiceInterface<TaskDTO> {
    private final TaskComponentCRUD taskComponentCRUD;
    private final TaskComponentDailyTasks taskComponentDailyTasks;
    private final TaskComponentSubtasker taskComponentSubtasker;
    private final UserAuthComponent userAuthComponent;

    @Override
    public List<TaskDTO> get(Authentication auth) {
        List<TaskDTO> list = getList(auth);
        list.forEach(taskDTO -> {
            DailyTasks dailyTask = taskComponentDailyTasks.getByTask(taskComponentCRUD.getEntityById(taskDTO.getId()));
            taskDTO.setDone(dailyTask.getDone());
            taskDTO.setToday(dailyTask.getToday());
            taskDTO.setDaily_id(dailyTask.getId());
            taskDTO.setSubtasks(getSubTasks(taskDTO.getId()));
        });
        return list;
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

    public DailyTasks createDailyTask(TaskDTO taskDTO, Authentication auth) {
        TaskDTO created = taskComponentCRUD.create(taskDTO, userAuthComponent.getUserByAuthorities(auth));
        if (taskDTO.getToday() != null) {
            return taskComponentDailyTasks.createDailyTask(taskComponentCRUD.getEntityById(created.getId()), taskDTO.getToday());
        }
        return taskComponentDailyTasks.createDailyTask(taskComponentCRUD.getEntityById(created.getId()));
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

    public List<TaskDTO> getAllTodayTasks(Authentication auth) {
        List<TaskDTO> list = getList(auth);
        list.forEach(taskDTO -> {
            DailyTasks dailyTask = taskComponentDailyTasks.getByTask(taskComponentCRUD.getEntityById(taskDTO.getId()));
            taskDTO.setDone(dailyTask.getDone());
            taskDTO.setToday(dailyTask.getToday());
            taskDTO.setDaily_id(dailyTask.getId());
            taskDTO.setSubtasks(getSubTasks(taskDTO.getId()));
        });
        return list.stream().filter(taskDTO -> compareDates(new Date(), taskDTO.getToday())).collect(Collectors.toList());
    }

    public List<TaskDTO> getAllByDate(Authentication auth, Date date) {
        List<TaskDTO> list = getList(auth);
        list.forEach(taskDTO -> {
            DailyTasks dailyTask = taskComponentDailyTasks.getByTask(taskComponentCRUD.getEntityById(taskDTO.getId()));
            taskDTO.setDone(dailyTask.getDone());
            taskDTO.setToday(dailyTask.getToday());
            taskDTO.setDaily_id(dailyTask.getId());
            taskDTO.setSubtasks(getSubTasks(taskDTO.getId()));
        });
        return list.stream().filter(taskDTO -> compareDates(date, taskDTO.getToday())).collect(Collectors.toList());
    }

    private List<TaskDTO> getList(Authentication auth) {
        return taskComponentCRUD.get(userAuthComponent.getUserByAuthorities(auth));
    }

    private List<SubtaskDTO> getSubTasks(Long id) {
        return taskComponentSubtasker.getAllSubtasks(taskComponentCRUD.getEntityById(id));
    }

    private boolean compareDates(Date date1, Date date2) {
        return date1.getDate() == date2.getDate();
    }
}
