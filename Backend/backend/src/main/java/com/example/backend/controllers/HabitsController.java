package com.example.backend.controllers;

import com.example.backend.DTOs.HabitsDTO;
import com.example.backend.services.HabitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/habits")
@RequiredArgsConstructor
public class HabitsController {
    private final HabitsService habitsService;

    @GetMapping("")
    public List<HabitsDTO> getAll() {
        return habitsService.get();
    }

    @GetMapping("{id}")
    public HabitsDTO getById(@PathVariable Long id) {
        return habitsService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<HabitsDTO> create(@RequestBody HabitsDTO habitsDTO) {
        HabitsDTO saved = habitsService.create(habitsDTO, SecurityContextHolder.getContext().getAuthentication());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<HabitsDTO> update(@PathVariable Long id, @RequestBody HabitsDTO habitsDTO) {
        HabitsDTO saved = habitsService.update(habitsDTO, id, SecurityContextHolder.getContext().getAuthentication());
        if (saved != null) {
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        habitsService.delete(id);
    }
}
