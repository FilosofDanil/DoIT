package com.example.backend.services.dbservices;

import com.example.backend.DTOs.SubtaskDTO;
import com.example.backend.DTOs.TaskDTO;
import com.example.backend.components.interfaces.ComponentCrud;
import com.example.backend.components.interfaces.ComponentEntityCRUD;
import com.example.backend.components.interfaces.MarkingInterface;
import com.example.backend.components.taskcomponents.TaskComponentDailyTasks;
import com.example.backend.components.usercomponents.UserAuthComponent;
import com.example.backend.entities.DailyTasks;
import com.example.backend.entities.Tasks;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService implements DbaServiceInterface<TaskDTO> {
    private final ComponentCrud<TaskDTO> taskComponentCRUD;
    private final ComponentEntityCRUD<Tasks> tasksComponentEntityCRUD;
    private final ComponentCrud<SubtaskDTO> taskComponentSubtaskerCRUD;
    private final MarkingInterface taskComponentSubtasker;
    private final TaskComponentDailyTasks taskComponentDailyTasks;
    private final UserAuthComponent userAuthComponent;

    @Override
    public List<TaskDTO> get(Authentication auth) {
        List<TaskDTO> list = getList(auth);
        list.forEach(taskDTO -> {
            DailyTasks dailyTask = taskComponentDailyTasks.getByTask(tasksComponentEntityCRUD.getEntityById(taskDTO.getId()));
            taskDTO.setDone(dailyTask.getDone());
            taskDTO.setToday(dailyTask.getToday());
            taskDTO.setDaily_id(dailyTask.getId());
        });
        return list;
    }

    public List<TaskDTO> getAllTodayTasks(Authentication auth) {
        List<TaskDTO> list = getList(auth);
        list.forEach(taskDTO -> {
            DailyTasks dailyTask = taskComponentDailyTasks.getByTask(tasksComponentEntityCRUD.getEntityById(taskDTO.getId()));
            taskDTO.setDone(dailyTask.getDone());
            taskDTO.setToday(dailyTask.getToday());
            taskDTO.setDaily_id(dailyTask.getId());
        });
        return list.stream().filter(taskDTO -> compareDates(new Date(), taskDTO.getToday())).collect(Collectors.toList());
    }

    public List<TaskDTO> getAllByDate(Authentication auth, Date date) {
        List<TaskDTO> list = getList(auth);
        list.forEach(taskDTO -> {
            DailyTasks dailyTask = taskComponentDailyTasks.getByTask(tasksComponentEntityCRUD.getEntityById(taskDTO.getId()));
            taskDTO.setDone(dailyTask.getDone());
            taskDTO.setToday(dailyTask.getToday());
            taskDTO.setDaily_id(dailyTask.getId());
        });
        return list.stream().filter(taskDTO -> compareDates(date, taskDTO.getToday())).collect(Collectors.toList());
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

    public SubtaskDTO createSubTask(Long id, SubtaskDTO subtaskDTO, Authentication auth) {
        subtaskDTO.setTask(tasksComponentEntityCRUD.getEntityById(id));
        return taskComponentSubtaskerCRUD.create(subtaskDTO, userAuthComponent.getUserByAuthorities(auth));
    }

    public void deleteSubTask(Long id) {
        taskComponentSubtaskerCRUD.delete(id);
    }

    public DailyTasks createDailyTask(TaskDTO taskDTO, Authentication auth) {
        TaskDTO created = taskComponentCRUD.create(taskDTO, userAuthComponent.getUserByAuthorities(auth));
        if (taskDTO.getToday() != null) {
            return taskComponentDailyTasks.createDailyTask(tasksComponentEntityCRUD.getEntityById(created.getId()), taskDTO.getToday());
        }
        return taskComponentDailyTasks.createDailyTask(tasksComponentEntityCRUD.getEntityById(created.getId()));
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

    public void updateSubtask(SubtaskDTO subtaskDTO, Long id, Authentication auth) {
        taskComponentSubtaskerCRUD.update(subtaskDTO, id, userAuthComponent.getUserByAuthorities(auth));
    }

    private List<TaskDTO> getList(Authentication auth) {
        return taskComponentCRUD.get(userAuthComponent.getUserByAuthorities(auth));
    }

    private boolean compareDates(Date date1, Date date2) {
        return date1.getDate() == date2.getDate();
    }
}
