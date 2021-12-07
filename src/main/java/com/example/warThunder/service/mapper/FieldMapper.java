package com.example.warThunder.service.mapper;

import com.example.warThunder.model.Field;
import com.example.warThunder.service.dto.FieldDto;
import org.springframework.stereotype.Component;

@Component
public class FieldMapper {

    public FieldDto toDto(Field field){
        if (field == null){
            return null;
        }
        FieldDto fieldDto = new FieldDto();
        fieldDto.setId(field.getId());
        fieldDto.setBattlefield(field.getCells());

        return fieldDto;
    }

    public Field toEntity(FieldDto fieldDto){
        if (fieldDto == null){
            return null;
        }
        Field field = new Field();
        field.setId(fieldDto.getId());
        field.setCells(fieldDto.getBattlefield());

        return field;
    }

}
