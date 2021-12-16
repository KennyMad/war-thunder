package com.example.controller;

import com.example.service.HistoryService;
import com.example.service.dto.HistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping
    public ResponseEntity<List<HistoryDto>> getHistory() {
        return new ResponseEntity<>(historyService.getAllHistory(), OK);
    }

    @PostMapping
    public ResponseEntity<HistoryDto> createHistory(@RequestBody HistoryDto historyDto){
        return new ResponseEntity<>(historyService.createHistory(historyDto), OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoryDto> updateHistory(@RequestBody HistoryDto historyDto, @PathVariable(name = "id") long id){
        return new ResponseEntity<>(historyService.updateHistory(historyDto, id), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHistory(@PathVariable(name = "id") long id){
        historyService.deleteHistory(id);
        return new ResponseEntity<>(OK);
    }

}
