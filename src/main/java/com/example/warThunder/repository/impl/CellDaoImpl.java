package com.example.warThunder.repository.impl;

import com.example.warThunder.model.Cell;
import com.example.warThunder.repository.CellDao;
import org.springframework.stereotype.Repository;

@Repository
public class CellDaoImpl extends AbstractDao<Cell> implements CellDao {

    @Override
    protected Class<Cell> getEntityClass() {
        return Cell.class;
    }
}
