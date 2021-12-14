package com.example.warThunder.service.mapper;

import com.example.warThunder.model.Ship;
import com.example.warThunder.service.dto.ShipDto;
import org.springframework.security.core.parameters.P;
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
