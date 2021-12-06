package com.example.warThunder.service;

import com.example.warThunder.service.dto.GameDto;
import com.example.warThunder.service.dto.UserDto;

public interface GameService {

    GameDto createGame(UserDto player1, UserDto player2);

}
