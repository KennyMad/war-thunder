package com.example.controller;

import com.example.service.GameService;
import com.example.service.dto.*;
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
    public ResponseEntity<GameDto> createGame(@RequestBody List<UserDto> players) {
        return new ResponseEntity<>(gameService.createGame(players), HttpStatus.OK);
    }

    @PostMapping("/{gameId}/move")
    public ResponseEntity<MovementResultDto> makeMove(@RequestBody MovementDto movementDto, @PathVariable long gameId) {
        return new ResponseEntity<>(gameService.makeMove(movementDto, gameId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody GameDto gameDto) {
        return new ResponseEntity<>(gameService.updateGame(gameDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        gameService.deleteGame(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getGames() {
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }
}
