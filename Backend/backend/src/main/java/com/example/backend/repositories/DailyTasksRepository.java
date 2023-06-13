package com.example.backend.repositories;

import com.example.backend.entities.DailyTasks;
import com.example.backend.entities.Tasks;
import org.springframework.data.repository.CrudRepository;

public interface DailyTasksRepository extends CrudRepository<DailyTasks, Long> {
    DailyTasks getDailyTasksByTask(Tasks task);
}
