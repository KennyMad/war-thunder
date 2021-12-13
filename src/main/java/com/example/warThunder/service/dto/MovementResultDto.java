package com.example.warThunder.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovementResultDto {

    private boolean hit;
    private boolean win;

}