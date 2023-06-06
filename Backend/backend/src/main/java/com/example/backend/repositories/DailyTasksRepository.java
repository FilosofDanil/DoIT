package com.example.backend.repositories;

import com.example.backend.entities.DailyTasks;
import org.springframework.data.repository.CrudRepository;

public interface DailyTasksRepository extends CrudRepository<DailyTasks, Long> {
}
