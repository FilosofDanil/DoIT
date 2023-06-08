package com.example.backend.repositories;

import com.example.backend.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> getUserByEmail(String email);
}
