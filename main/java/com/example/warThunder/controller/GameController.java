package com.example.warThunder.controller;

import com.example.warThunder.service.GameService;
import com.example.warThunder.service.dto.GameDto;
import com.example.warThunder.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping
    public ResponseEntity<GameDto> createGame(@RequestBody UserDto userDto1, @RequestBody UserDto userDto2){
        return new ResponseEntity<>(gameService.createGame(userDto1, userDto2), HttpStatus.OK);
    }

}
