package com.example.warThunder.repository;

import com.example.warThunder.model.AbstractEntity;

import java.util.List;

public interface Dao<T extends AbstractEntity> {
    void save(T entity);

    void saveAll(List<T> entities);

    T update(T entity);

    void delete(Long id);

    T getById(Long id);

    List<T> getAll();
}
