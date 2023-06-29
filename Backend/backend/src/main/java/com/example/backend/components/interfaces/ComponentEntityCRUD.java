package com.example.backend.components.interfaces;

import com.example.backend.entities.Habits;

public interface ComponentEntityCRUD <T>{
    T getEntityById(Long id);
}
