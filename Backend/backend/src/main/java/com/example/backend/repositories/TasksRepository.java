package com.example.backend.repositories;

import com.example.backend.entities.Tasks;
import com.example.backend.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface TasksRepository extends CrudRepository<Tasks, Long> {
    List<Tasks> findAllByUser(User user);
}
