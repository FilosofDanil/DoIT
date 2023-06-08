package com.example.backend.controllers;

import com.example.backend.DTOs.TaskDTO;
import com.example.backend.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Task")
@RequiredArgsConstructor
public class TasksController {
    private final TaskService taskService;

    @GetMapping("")
    public List<TaskDTO> getAll() {
        return taskService.get();
    }

    @GetMapping("{id}")
    public TaskDTO getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO TaskDTO) {
        TaskDTO saved = taskService.create(TaskDTO, SecurityContextHolder.getContext().getAuthentication());
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
}
