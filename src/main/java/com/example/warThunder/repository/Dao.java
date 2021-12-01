package com.example.warThunder.repository;

import com.example.warThunder.model.AbstractEntity;

import java.util.List;

public interface Dao <T extends AbstractEntity> {
    T save(T entity);
    T update(T entity);
    void delete(Long id);
    T getById(Long id);
    List<T> getAll();
}