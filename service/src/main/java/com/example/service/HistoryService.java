package com.example.service;

import com.example.service.dto.HistoryDto;

import java.util.List;

public interface HistoryService {

    List<HistoryDto> getAllHistory();

    HistoryDto createHistory(HistoryDto historyDto);

    void deleteHistory(long id);

    HistoryDto updateHistory(HistoryDto historyDto, long id);

}
