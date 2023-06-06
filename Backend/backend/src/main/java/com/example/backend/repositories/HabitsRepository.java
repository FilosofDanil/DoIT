package com.example.backend.repositories;

import com.example.backend.entities.Habits;
import org.springframework.data.repository.CrudRepository;

public interface HabitsRepository extends CrudRepository<Habits, Long> {
}
