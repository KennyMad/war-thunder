package com.example.service.dto;

import lombok.Data;

@Data
public class CellDto {

    private Long id;

    private char x;
    private char y;

    private boolean isShot;
    private ShipDto shipDto;

}
