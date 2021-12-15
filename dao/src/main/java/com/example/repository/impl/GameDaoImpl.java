package com.example.repository.impl;

import com.example.models.Game;
import com.example.repository.GameDao;
import org.springframework.stereotype.Repository;

@Repository
public class GameDaoImpl extends AbstractDao<Game> implements GameDao {
    @Override
    protected Class<Game> getEntityClass() {
        return Game.class;
    }
}
