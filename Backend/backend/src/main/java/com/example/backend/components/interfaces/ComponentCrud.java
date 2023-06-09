package com.example.backend.components.interfaces;

import com.example.backend.entities.User;

import java.util.List;

public interface ComponentCrud<T> {
    List<T> get(User user);

    T getById(Long id);

    T create(T t, User user);

    T update(T t, Long id, User user);

    void delete(Long id);
}
