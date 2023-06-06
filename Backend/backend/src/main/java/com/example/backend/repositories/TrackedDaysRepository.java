package com.example.backend.repositories;

import com.example.backend.entities.TrackedDays;
import org.springframework.data.repository.CrudRepository;

public interface TrackedDaysRepository extends CrudRepository<TrackedDays, Long> {
}
