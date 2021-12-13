package com.example.warThunder.controller;

import com.example.warThunder.service.GameService;
import com.example.warThunder.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/create")
    public ResponseEntity<GameDto> createGame(@RequestBody List<UserDto> players){
        return new ResponseEntity<>(gameService.createGame(players), HttpStatus.OK);
    }

    @PostMapping("/{gameId}/move")
    public ResponseEntity<MovementResultDto> makeMove(@RequestBody MovementDto movementDto, @PathVariable long gameId){
        return new ResponseEntity<>(gameService.makeMove(movementDto, gameId), HttpStatus.OK);
    }

}
