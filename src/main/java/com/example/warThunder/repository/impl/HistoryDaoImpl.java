package com.example.warThunder.repository.impl;

import com.example.warThunder.model.History;
import com.example.warThunder.repository.HistoryDao;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryDaoImpl extends AbstractDao<History> implements HistoryDao {

    @Override
    protected Class<History> getEntityClass() {
        return History.class;
    }
}
