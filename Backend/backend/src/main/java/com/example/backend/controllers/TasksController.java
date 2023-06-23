package com.example.backend.controllers;

import com.example.backend.DTOs.SubtaskDTO;
import com.example.backend.DTOs.TaskDTO;
import com.example.backend.entities.DailyTasks;
import com.example.backend.entities.Subtasks;
import com.example.backend.services.dbservices.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TasksController {
    private final TaskService taskService;

    @GetMapping("")
    public List<TaskDTO> getAll() {
        return taskService.get(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/date/{date}")
    public List<TaskDTO> getAllByDate(@PathVariable String date) throws ParseException {
        return taskService.getAllByDate(SecurityContextHolder.getContext().getAuthentication(), new SimpleDateFormat("yyyy-MM-dd").parse(date));
    }

    @GetMapping("/today")
    public List<TaskDTO> getAllTasksForToday() {
        return taskService.getAllTodayTasks(SecurityContextHolder.getContext().getAuthentication());
    }

//    @GetMapping("/subtasks/{id}")
//    public List<SubtaskDTO> getAllSubTasks(@PathVariable Long id) {
//        return taskService.getSubTasks(id);
//    }

    @GetMapping("{id}")
    public TaskDTO getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    //    @PostMapping("")
//    public ResponseEntity<TaskDTO> createCommonTask(@RequestBody TaskDTO TaskDTO) {
//        TaskDTO saved = taskService.create(TaskDTO, SecurityContextHolder.getContext().getAuthentication());
//        return new ResponseEntity<>(saved, HttpStatus.CREATED);
//    }
    @PostMapping("")
    public ResponseEntity<DailyTasks> createDailyTask(@RequestBody TaskDTO TaskDTO) {
        DailyTasks saved = taskService.createDailyTask(TaskDTO, SecurityContextHolder.getContext().getAuthentication());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/subtask/{id}")
    public ResponseEntity<Subtasks> createSubtask(@PathVariable Long id, @RequestBody SubtaskDTO subtaskDTO) {
        Subtasks saved = taskService.createSubTask(id, subtaskDTO.getName());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable Long id, @RequestBody TaskDTO TaskDTO) {
        TaskDTO saved = taskService.update(TaskDTO, id, SecurityContextHolder.getContext().getAuthentication());
        if (saved != null) {
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    @DeleteMapping("/subtask/{id}")
    public void deleteSubTask(@PathVariable Long id) {
        taskService.deleteSubTask(id);
    }

    @PatchMapping("/subtask/mark/{id}")
    public void markSubtask(@PathVariable Long id) {
        taskService.markSubTask(id);
    }

    @PatchMapping("/subtask/unmark/{id}")
    public void unmarkSubtask(@PathVariable Long id) {
        taskService.unmarkSubTask(id);
    }

    @PostMapping("/mark/{id}")
    public void markDailyTask(@PathVariable Long id) {
        taskService.markDailyTask(id);
    }

    @PostMapping("/unmark/{id}")
    public void unmarkDailyTask(@PathVariable Long id) {
        taskService.unmarkDailyTask(id);
    }
}
