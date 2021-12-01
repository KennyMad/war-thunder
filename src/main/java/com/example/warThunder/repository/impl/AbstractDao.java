package com.example.warThunder.repository.impl;

import com.example.warThunder.model.AbstractEntity;
import com.example.warThunder.repository.Dao;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Queue;

@Slf4j
public abstract class AbstractDao<T extends AbstractEntity> implements Dao<T> {

    @PersistenceContext
    @Getter(AccessLevel.PROTECTED)
    private EntityManager entityManager;

    protected abstract Class<T> getEntityClass();

    @Override
    public T save(T entity) {
        log.info("Сохранение объекта: " + entity);
        return entityManager.merge(entity);
    }

    @Override
    public T update(T entity) {
        log.info("Обновление объекта класса " + getEntityClass().getSimpleName() + " объектом: " + entity);
        return entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        log.info("Удаление объекта класса " + getEntityClass().getSimpleName() + " id=" + id);
        String queryString = "delete from " + getEntityClass().getName() + " entity where entity.id = :id";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public T getById(Long id) {
        log.info("Поиск по id объекта " + getEntityClass().getSimpleName() + " id=" + id);
        return entityManager.find(getEntityClass(), id);
    }

    @Override
    public List<T> getAll() {
        log.info("Поиск всех объектов " + getEntityClass().getSimpleName());
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getEntityClass());
        return entityManager.createQuery(query).getResultList();
    }
}
