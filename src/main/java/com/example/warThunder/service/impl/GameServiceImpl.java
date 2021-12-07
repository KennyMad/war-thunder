package com.example.warThunder.service.impl;

import com.example.warThunder.exception.NotUniqueUsername;
import com.example.warThunder.exception.UserNotExists;
import com.example.warThunder.model.*;
import com.example.warThunder.repository.*;
import com.example.warThunder.service.GameService;
import com.example.warThunder.service.dto.*;
import com.example.warThunder.service.mapper.FieldMapper;
import com.example.warThunder.service.mapper.GameMapper;
import com.example.warThunder.service.mapper.UserMapper;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {

    @Autowired
    FieldDao fieldDao;
    @Autowired
    HistoryDao historyDao;
    @Autowired
    CellDao cellDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ShipDao shipDao;

    @Autowired
    UserMapper userMapper;
    @Autowired
    FieldMapper fieldMapper;
    @Autowired
    GameMapper gameMapper;

    @Override
    public GameDto createGame(List<UserDto> players){
        List<User> usersForCheck = players.stream().map(userMapper::toEntity).collect(Collectors.toList());
        List<User> users = new ArrayList<>();
        for (int i = 0; i < players.size(); i++){
            if (!userDao.isUsernameExist(usersForCheck.get(i).getName())){
                throw new UserNotExists(usersForCheck.get(i).getName());
            }
            else {
                users.add(userDao.getUserByUsername(usersForCheck.get(i).getName()));
            }
        }

        Field field1 = new Field();
        Field field2 = new Field();

        field1.setCells(cellDao.saveAll(generateEmptyCells()));
        field2.setCells(cellDao.saveAll(generateEmptyCells()));

        field1 = generateShips(field1);
        field2 = generateShips(field2);

        field1.setCells(field1.getCells());
        field2.setCells(field2.getCells());

        List<Field> fields = new ArrayList<>();
        long field1Id = fieldDao.save(field1).getId();
        long field2Id = fieldDao.save(field2).getId();
        fields.add(fieldDao.getById(field1Id));
        fields.add(fieldDao.getById(field2Id));

        History history = new History();
        history.setUsers(users);
        history.setMovements(new ArrayList<>());
        history.setUsers(users);
        history = historyDao.save(history);
        Game game = new Game();
        game.setHistory(history);
        game.setUsers(users);
        game.setFields(fields);

        return gameMapper.toDto(game);
    }

    @Override
    public MovementResultDto makeMove(MovementDto movementDto, int fieldId) {
        return null;
    }

    private List<Cell> generateEmptyCells(){
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++) {
                Cell cell = new Cell();
                cell.setX((char)(i + '0'));
                cell.setY((char)(j + '0'));
                cell.setShooted(false);
                cells.add(cell);
            }
        }
        return cells;
    }

    private Field generateShips(Field field){
        int[][] bufField = new int[field.getCells().size()][field.getCells().size()];
        ships(bufField);
        List<Ship> ships = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                    if(bufField[i][k]==1){
                        Ship ship = new Ship();
                        List<Cell> cellsForShip = new ArrayList<>();
                        int verticalLocation = checkForShip(bufField, i, k, 0);
                        int horizontalLocation = checkForShip(bufField, i, k, 1);
                        if(horizontalLocation>verticalLocation){
                            for(int j = k; j<k+horizontalLocation; j++){
                                cellsForShip.add(field.getCells().get((i*10)+j));
                                bufField[i][j] = 0;
                            }
                        }else if(horizontalLocation<verticalLocation){
                            for(int j = i; j<i+verticalLocation; j++){
                                cellsForShip.add(field.getCells().get((j*10)+k));
                                bufField[j][k] = 0;
                            }
                        }
                        else{
                            cellsForShip.add(field.getCells().get((i*10)+k));//////////////
                            bufField[i][k] = 0;
                        }
                        ship.setSize(cellsForShip.size());
                        ship.setCells(cellsForShip);
                        ships.add(ship);

                    }
            }
        }
        return fillField(field, ships);
    }

    private Field fillField(Field field, List<Ship> ships){
        for (Ship ship : ships) {
            List<Cell> shipCells = ship.getCells();
            ship.setCells(new ArrayList<>());
            for (int i = 0; i < field.getCells().size(); i++) {
                for (Cell shipCell : shipCells) {
                    if (shipCell.getX() == field.getCells().get(i).getX()
                            && shipCell.getY() == field.getCells().get(i).getY()) {
                        ship.getCells().add(field.getCells().get(i));
                    }
                }
            }
        }
        shipDao.saveAll(ships);
        return field;
    }


    private int checkForShip(int[][] bufField, int y, int x, int isHorizontal){
        int size = 0;
        while(bufField[y][x]==1){
            size++;
            if(isHorizontal==1){
                x++;
            }else {
                y++;
            }
        }
        return size;
    }

    private boolean freedom(int x, int y, int[][] Pole) {

        int dx, dy;

        if ((x >= 0) & (x < 10) & (y >= 0) & (y < 10) && ((Pole[x][y] == 0) || (Pole[x][y] == 2))) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    dx = x + i;
                    dy = y + j;
                    if ((dx >= 0) & (dx < 10) & (dy >= 0) & (dy < 10) && (Pole[dx][dy] == 1)) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private void ships(int[][] Pole) {
        int x, y, kx, ky;
        boolean B;
        Random random = new Random();

        for (int N = 3; N >= 0; N--) {
            for (int M = 0; M <= 3 - N; M++) {
                do {
                    x = random.nextInt(10);
                    y = random.nextInt(10);
                    kx = random.nextInt(2);
                    if (kx == 0) {
                        ky = 1;
                    } else {
                        ky = 0;
                    }
                    B = true;
                    for (int j = 0; j <= N; j++) {
                        if (!(freedom(x + kx * j, y + ky * j, Pole))) {
                            B = false;
                            break;
                        }
                    }
                    if (B) {
                        for (int k = 0; k <= N; k++) {
                            Pole[x + kx * k][y + ky * k] = 1;
                        }
                    }
                }
                while (!(B));
            }
        }

    }
}
