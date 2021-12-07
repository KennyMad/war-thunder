package com.example.warThunder.service.mapper;

import com.example.warThunder.model.Game;
import com.example.warThunder.service.dto.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class GameMapper {

    @Autowired
    FieldMapper fieldMapper;
    @Autowired
    HistoryMapper historyMapper;
    @Autowired
    UserMapper userMapper;

    public Game toEntity(GameDto gameDto){
        if (gameDto == null){
            return null;
        }
        Game game = new Game();
        if (gameDto.getFields() == null){
            game.setFields(null);
        }
        else {
            game.setFields(gameDto.getFields().stream().map(fieldMapper::toEntity).collect(Collectors.toList()));
        }
        game.setId(gameDto.getId());
        game.setHistory(historyMapper.toEntity(gameDto.getHistoryDto()));
        if (gameDto.getPlayers() == null){
            game.setUsers(null);
        }
        else {
            game.setUsers(gameDto.getPlayers().stream().map(userMapper::toEntity).collect(Collectors.toList()));
        }

        return game;
    }
    //кажется сделал функцию заполнение кораблями поля, теперь где ее прописать
    public GameDto toDto(Game game){
        if (game == null){
            return null;
        }

        GameDto gameDto = new GameDto();
        if (game.getFields() == null){
            gameDto.setFields(null);
        }
        else {
            gameDto.setFields(game.getFields().stream().map(fieldMapper::toDto).collect(Collectors.toList()));
        }
        gameDto.setId(game.getId());
        gameDto.setHistoryDto(historyMapper.toDto(game.getHistory()));
        if (game.getUsers() == null){
            gameDto.setPlayers(null);
        }
        else {
            gameDto.setPlayers(game.getUsers().stream().map(userMapper::toDto).collect(Collectors.toList()));
        }

        return gameDto;
    }

}
