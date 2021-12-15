package com.example.repository;

import com.example.models.AbstractEntity;

import java.util.List;

public interface Dao<T extends AbstractEntity> {
    void save(T entity);

    void saveAll(List<T> entities);

    T update(T entity);

    void delete(Long id);

    T getById(Long id);

    List<T> getAll();
}
