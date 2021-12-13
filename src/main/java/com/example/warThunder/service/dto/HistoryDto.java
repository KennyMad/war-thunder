package com.example.warThunder.service.dto;

import com.example.warThunder.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HistoryDto{

    List<MovementDto> movements;

}
