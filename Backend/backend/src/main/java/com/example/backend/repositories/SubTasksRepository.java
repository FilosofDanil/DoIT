package com.example.backend.repositories;

import com.example.backend.entities.Subtasks;
import org.springframework.data.repository.CrudRepository;

public interface SubTasksRepository extends CrudRepository<Subtasks, Long> {
}
