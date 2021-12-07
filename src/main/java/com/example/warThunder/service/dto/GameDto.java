package com.example.warThunder.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    private Long id;

    private List<UserDto> players;
    private List<FieldDto> fields;

    private HistoryDto historyDto;

}
