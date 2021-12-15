package com.example.service;

import com.example.service.dto.*;

import java.util.List;

public interface GameService {

    GameDto createGame(List<UserDto> players);

    MovementResultDto makeMove(MovementDto movementDto, long gameId);

    List<GameDto> getAllGames();

    GameDto updateGame(GameDto gameDto);

    void deleteGame(long id);

}
