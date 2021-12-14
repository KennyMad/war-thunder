package com.example.warThunder.service.mapper;

import com.example.warThunder.model.History;
import com.example.warThunder.service.dto.HistoryDto;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Data
public class HistoryMapper {

    private final MovementMapper movementMapper;

    public History toEntity(HistoryDto historyDto) {
        if (historyDto == null) {
            return null;
        }
        History history = new History();
        if (historyDto.getMovements() == null) {
            history.setMovements(null);
        } else {
            history.setMovements(historyDto.getMovements().stream().map(movementMapper::toEntity).collect(Collectors.toList()));
        }
        return new History();
    }

    public HistoryDto toDto(History history) {
        if (history == null) {
            return null;
        }
        HistoryDto historyDto = new HistoryDto();
        if (history.getMovements() == null) {
            historyDto.setMovements(null);
        } else {
            historyDto.setMovements(history.getMovements().stream().map(movementMapper::toDto).collect(Collectors.toList()));
        }
        return historyDto;
    }

}
