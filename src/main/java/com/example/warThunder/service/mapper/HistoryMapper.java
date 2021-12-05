package com.example.warThunder.service.mapper;

import com.example.warThunder.model.History;
import com.example.warThunder.service.dto.HistoryDto;
import org.springframework.stereotype.Component;

@Component
public class HistoryMapper {

    public History toEntity(HistoryDto historyDto){
        return new History();
    }

    public HistoryDto toDto(History history){
        return new HistoryDto();
    }

}
