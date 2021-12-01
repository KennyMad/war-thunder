package com.example.warThunder.repository.impl;

import com.example.warThunder.model.Movement;
import com.example.warThunder.repository.MovementDao;
import org.springframework.stereotype.Repository;

@Repository
public class MovementDaoImpl extends AbstractDao<Movement> implements MovementDao {

    @Override
    protected Class<Movement> getEntityClass() {
        return Movement.class;
    }
}
