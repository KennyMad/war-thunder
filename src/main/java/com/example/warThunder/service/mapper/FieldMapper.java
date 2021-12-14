package com.example.warThunder.service.mapper;

import com.example.warThunder.model.Field;
import com.example.warThunder.service.dto.FieldDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FieldMapper {

    @Autowired
    private CellMapper cellMapper;

    public FieldDto toDto(Field field) {
        if (field == null) {
            return null;
        }
        FieldDto fieldDto = new FieldDto();
        fieldDto.setId(field.getId());
        if (field.getCells() == null) {
            fieldDto.setBattlefield(null);
        } else {
            fieldDto.setBattlefield(field.getCells().stream().map(cellMapper::toDto).collect(Collectors.toList()));
        }
        fieldDto.setOwnerId(field.getOwnerId());

        return fieldDto;
    }

    public Field toEntity(FieldDto fieldDto) {
        if (fieldDto == null) {
            return null;
        }
        Field field = new Field();
        field.setId(fieldDto.getId());
        if (fieldDto.getBattlefield() == null) {
            field.setCells(null);
        } else {
            field.setCells(fieldDto.getBattlefield().stream().map(cellMapper::toEntity).collect(Collectors.toList()));
        }
        field.setOwnerId(fieldDto.getOwnerId());

        return field;
    }

}
