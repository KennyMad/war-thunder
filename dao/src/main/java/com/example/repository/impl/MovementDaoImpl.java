package com.example.repository.impl;

import com.example.models.Movement;
import com.example.repository.MovementDao;
import org.springframework.stereotype.Repository;

@Repository
public class MovementDaoImpl extends AbstractDao<Movement> implements MovementDao {

    @Override
    protected Class<Movement> getEntityClass() {
        return Movement.class;
    }
}
