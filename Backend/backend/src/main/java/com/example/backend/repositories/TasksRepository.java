package com.example.backend.repositories;

import com.example.backend.entities.Tasks;
import com.example.backend.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface TasksRepository extends CrudRepository<Tasks, Long> {
//    @Query(value="select t.id, task_name, credentials_id from tasks t left join subtasks s on t.id = s.task_id", nativeQuery = true)
    List<Tasks> findAllByUser(User user);
}
