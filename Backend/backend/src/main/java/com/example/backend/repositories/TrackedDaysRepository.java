package com.example.backend.repositories;

import com.example.backend.entities.Habits;
import com.example.backend.entities.TrackedDays;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface TrackedDaysRepository extends CrudRepository<TrackedDays, Long> {
    List<TrackedDays> findAllByHabit(Habits habit);

//    @Query(value = "delete from tracked_days t where t.habits_id = 14::int", nativeQuery = true)
//    void deleteAll(Integer habits_id);
}
