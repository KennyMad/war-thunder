package com.example.repository.impl;

import com.example.models.Ship;
import com.example.repository.ShipDao;
import org.springframework.stereotype.Repository;

@Repository
public class ShipDaoImpl extends AbstractDao<Ship> implements ShipDao {

    @Override
    protected Class<Ship> getEntityClass() {
        return Ship.class;
    }
}
