package com.example.warThunder.service;

import com.example.warThunder.service.dto.*;

import java.util.List;

public interface GameService {

    GameDto createGame(List<UserDto> players);

    MovementResultDto makeMove(MovementDto movementDto, int gameId);

}
