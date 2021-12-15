package com.example.service.mapper;

import com.example.models.Ship;
import com.example.service.dto.ShipDto;
import org.springframework.stereotype.Component;

@Component
public class ShipMapper {

    public Ship toEntity(ShipDto shipDto) {
        if (shipDto == null) {
            return null;
        }
        Ship ship = new Ship();
        ship.setId(shipDto.getId());
        ship.setSize(shipDto.getSize());

        return ship;
    }

    public ShipDto toDto(Ship ship) {
        if (ship == null) {
            return null;
        }
        ShipDto shipDto = new ShipDto();
        shipDto.setId(ship.getId());
        shipDto.setSize(ship.getSize());

        return shipDto;
    }

}
