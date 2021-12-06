package com.example.warThunder.repository.impl;

import com.example.warThunder.model.Ship;
import com.example.warThunder.repository.ShipDao;
import org.springframework.stereotype.Repository;

@Repository
public class ShipDaoImpl extends AbstractDao<Ship> implements ShipDao {

    @Override
    protected Class<Ship> getEntityClass() {
        return Ship.class;
    }
}
