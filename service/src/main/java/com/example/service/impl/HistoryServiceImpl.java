package com.example.service.impl;

import com.example.models.History;
import com.example.repository.HistoryDao;
import com.example.service.HistoryService;
import com.example.service.dto.HistoryDto;
import com.example.service.mapper.HistoryMapper;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryMapper historyMapper;
    private final HistoryDao historyDao;

    @Override
    public List<HistoryDto> getAllHistory() {
        return historyDao.getAll()
                .stream()
                .map(historyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public HistoryDto createHistory(HistoryDto historyDto) {
        History history = historyMapper.toEntity(historyDto);
        historyDao.save(history);
        return historyMapper.toDto(history);
    }

    @Override
    public void deleteHistory(long id) {
        historyDao.delete(id);
    }

    @Override
    public HistoryDto updateHistory(HistoryDto historyDto, long id) {
        History history = historyDao.getById(id);
        History newHistory = historyMapper.toEntity(historyDto);
        history.setMovements(newHistory.getMovements());
        history.setGame(newHistory.getGame());
        return historyMapper.toDto(historyDao.update(history));
    }
}
