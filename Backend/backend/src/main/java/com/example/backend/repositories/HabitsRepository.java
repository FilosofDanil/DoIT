package com.example.backend.repositories;

import com.example.backend.entities.Habits;
import com.example.backend.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface HabitsRepository extends CrudRepository<Habits, Long> {
    List<Habits> findAllByUser(User user);
}
