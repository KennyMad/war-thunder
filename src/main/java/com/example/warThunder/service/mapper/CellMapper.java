package com.example.warThunder.service.mapper;

import com.example.warThunder.model.Cell;
import com.example.warThunder.service.dto.CellDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CellMapper {

    @Autowired
    ShipMapper shipMapper;

    public CellDto toDto(Cell cell) {
        if (cell == null) {
            return null;
        }
        CellDto cellDto = new CellDto();
        cellDto.setId(cell.getId());
        cellDto.setShipDto(shipMapper.toDto(cell.getShip()));
        cellDto.setShot(cell.isShot());
        cellDto.setX(cell.getX());
        cellDto.setY(cell.getY());

        return cellDto;
    }

    public Cell toEntity(CellDto cellDto) {
        if (cellDto == null) {
            return null;
        }
        Cell cell = new Cell();
        cell.setId(cellDto.getId());
        cell.setShot(cellDto.isShot());
        cell.setX(cellDto.getX());
        cell.setY(cellDto.getY());
        cell.setShip(shipMapper.toEntity(cellDto.getShipDto()));

        return cell;
    }
}
