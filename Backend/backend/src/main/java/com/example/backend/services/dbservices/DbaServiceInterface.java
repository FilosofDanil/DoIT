package com.example.backend.services.dbservices;

import org.springframework.security.core.Authentication;

import java.util.List;

public interface DbaServiceInterface<T> {
    List<T> get(Authentication auth);

    T getById(Long id);

    T create(T t, Authentication auth);

    T update(T t, Long id, Authentication auth);

    void delete(Long id);
}
