package com.example.backend.repositories;

import com.example.backend.entities.Habits;
import com.example.backend.entities.TrackedDays;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface TrackedDaysRepository extends CrudRepository<TrackedDays, Long> {
    List<TrackedDays> findAllByHabit(Habits habit);
}
