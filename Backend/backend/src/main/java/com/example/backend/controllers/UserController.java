package com.example.backend.controllers;

import com.example.backend.DTOs.UserDTO;
import com.example.backend.services.dbservices.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/profile")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public UserDTO getProfile() {
        return userService.getProfile(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/calc_tasks")
    public Integer calculateCompletedTasks() {
        return userService.calculateCompletedTasks(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/calc_habits")
    public Integer calculateCompletedHabits() {
        return userService.calculateCompletedHabits(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/calc_un_habits")
    public Integer calculateUncompletedHabits() {
        return userService.calculateUncompletedHabits(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/calc_un_tasks")
    public Integer calculateUncompletedTasks() {
        return userService.calculateUncompletedTasks(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/calc_age")
    public Integer calculateAge() {
        return userService.calculateAge(SecurityContextHolder.getContext().getAuthentication());
    }
}
