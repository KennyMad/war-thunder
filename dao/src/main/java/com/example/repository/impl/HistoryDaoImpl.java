package com.example.repository.impl;

import com.example.models.History;
import com.example.repository.HistoryDao;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryDaoImpl extends AbstractDao<History> implements HistoryDao {

    @Override
    protected Class<History> getEntityClass() {
        return History.class;
    }
}
