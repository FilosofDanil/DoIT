package com.example.backend.repositories;

import com.example.backend.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> getUserByEmail(String email);

    User findByActivationCode(String code);

    @Query(value = "select count(*) from users u join tasks t on t.credentials_id = :id and u.id=:id join daily_tasks dt on t.id = dt.task_id where dt.done=:done", nativeQuery = true)
    int undoneTasks(Long id, Boolean done);

    @Query(value = "select count(*) from users u join habits h on u.id = :id and h.credentials_id = :id where h.completed=:done", nativeQuery = true)
    int undoneHabits(Long id, Boolean done);
}
