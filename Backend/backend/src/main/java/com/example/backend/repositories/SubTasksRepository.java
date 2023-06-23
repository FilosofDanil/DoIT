package com.example.backend.repositories;

import com.example.backend.entities.Subtasks;
import com.example.backend.entities.Tasks;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface SubTasksRepository extends CrudRepository<Subtasks, Long> {
    List<Subtasks> findAllByTask(Tasks task);
}
