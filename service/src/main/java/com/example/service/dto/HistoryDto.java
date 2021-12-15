package com.example.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HistoryDto {

    List<MovementDto> movements;

}
