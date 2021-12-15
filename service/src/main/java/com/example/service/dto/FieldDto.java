package com.example.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldDto {

    private Long id;
    private List<CellDto> battlefield;
    private Long ownerId;
}
