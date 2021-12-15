package com.example.service.mapper;

import com.example.models.Movement;
import com.example.service.dto.MovementDto;
import org.springframework.stereotype.Component;

@Component
public class MovementMapper {

    public Movement toEntity(MovementDto movementDto) {
        if (movementDto == null) {
            return null;
        }
        Movement movement = new Movement();
        movement.setUserId(movement.getUserId());
        movement.setX(movement.getX());
        movement.setY(movement.getY());

        return movement;
    }

    public MovementDto toDto(Movement movement) {
        if (movement == null) {
            return null;
        }
        MovementDto movementDto = new MovementDto();
        movementDto.setUserId(movement.getUserId());
        movementDto.setX(movement.getX());
        movementDto.setY(movement.getY());

        return movementDto;
    }
}
