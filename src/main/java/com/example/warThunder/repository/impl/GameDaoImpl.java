package com.example.warThunder.repository.impl;

import com.example.warThunder.model.Game;
import com.example.warThunder.repository.GameDao;
import org.springframework.stereotype.Repository;

@Repository
public class GameDaoImpl extends AbstractDao<Game> implements GameDao {
    @Override
    protected Class<Game> getEntityClass() {
        return Game.class;
    }
}
