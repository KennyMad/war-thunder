package com.example.repository.impl;

import com.example.models.Cell;
import com.example.repository.CellDao;
import org.springframework.stereotype.Repository;

@Repository
public class CellDaoImpl extends AbstractDao<Cell> implements CellDao {

    @Override
    protected Class<Cell> getEntityClass() {
        return Cell.class;
    }
}
