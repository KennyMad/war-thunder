package com.example.warThunder.service.impl;

import com.example.warThunder.exception.UserNotExistsException;
import com.example.warThunder.exception.WrongShootPointException;
import com.example.warThunder.exception.WrongUserGameException;
import com.example.warThunder.exception.WrongUserTurnException;
import com.example.warThunder.model.*;
import com.example.warThunder.repository.*;
import com.example.warThunder.service.GameService;
import com.example.warThunder.service.dto.*;
import com.example.warThunder.service.mapper.FieldMapper;
import com.example.warThunder.service.mapper.GameMapper;
import com.example.warThunder.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {

    private final FieldDao fieldDao;
    private final HistoryDao historyDao;
    private final CellDao cellDao;
    private final UserDao userDao;
    private final ShipDao shipDao;
    private final GameDao gameDao;
    private final MovementDao movementDao;

    private final UserMapper userMapper;
    private final FieldMapper fieldMapper;
    private final GameMapper gameMapper;

    @Transactional
    @Override
    public GameDto createGame(List<UserDto> players) {
        List<User> usersForCheck = players.stream().map(userMapper::toEntity).collect(Collectors.toList());
        List<User> users = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            if (!userDao.isUsernameExist(usersForCheck.get(i).getName())) {
                throw new UserNotExistsException(usersForCheck.get(i).getName());
            } else {
                users.add(userDao.getUserByUsername(usersForCheck.get(i).getName()));
            }
        }

        Field field1 = new Field();
        Field field2 = new Field();

        List<Cell> cells1 = generateEmptyCells();
        List<Cell> cells2 = generateEmptyCells();
        cellDao.saveAll(cells1);
        cellDao.saveAll(cells2);

        field1.setCells(cells1);
        field2.setCells(cells2);

        field1 = generateShips(field1);
        field2 = generateShips(field2);

        field1.setOwnerId(users.get(0).getId());
        field2.setOwnerId(users.get(1).getId());

        fieldDao.save(field1);
        fieldDao.save(field2);

        List<Field> fields = new ArrayList<>();
        fields.add(field1);
        fields.add(field2);

        History history = new History();
        history.setMovements(new ArrayList<>());
        historyDao.save(history);
        Game game = new Game();
        game.setUsers(users);
        history.setGame(game);
        fields.get(0).setGame(game);
        fields.get(1).setGame(game);
        gameDao.save(game);

        return gameMapper.toDto(game);
    }

    @Transactional
    @Override
    public MovementResultDto makeMove(MovementDto movementDto, long gameId) {
        Game game = gameDao.getById(gameId);
        User user = userDao.getById(movementDto.getUserId());
        if (user == null || !game.getUsers().contains(user)) {
            throw new WrongUserGameException("Пользователь не найден либо не играет id=" + movementDto.getUserId());
        }
        History history = game.getHistory();
        int turn = 1;
        if (history.getMovements().size() != 0) {
            Movement lastMovement = history.getMovements().stream()
                    .max(Comparator.comparingInt(Movement::getTurnNumber)).get();
            if (lastMovement.getUserId().equals(user.getId())) {
                throw new WrongUserTurnException();
            }
            turn = lastMovement.getTurnNumber() + 1;
        }

        Field fieldToShoot;
        if (game.getFields().get(0).getOwnerId() == movementDto.getUserId()) {
            fieldToShoot = game.getFields().get(1);
        } else {
            fieldToShoot = game.getFields().get(0);
        }

        Cell cellToShoot = fieldToShoot.getCells().stream()
                .filter(cell -> cell.getX() == movementDto.getX() && cell.getY() == movementDto.getY()).findFirst().orElse(null);
        if (cellToShoot == null || cellToShoot.isShot()) {
            throw new WrongShootPointException(movementDto.getX(), movementDto.getY());
        }
        cellToShoot.setShot(true);
        cellDao.update(cellToShoot);
        Movement movement = new Movement();
        movement.setX(movementDto.getX());
        movement.setY(movementDto.getY());
        movement.setUserId(movementDto.getUserId());
        movement.setTurnNumber(turn);
        history.getMovements().add(movement);
        historyDao.update(history);
        if (cellToShoot.getShip() == null) {
            return MovementResultDto.builder().hit(false).win(false).build();
        }
        return MovementResultDto.builder()
                .hit(true)
                .win(fieldToShoot.getCells().stream().noneMatch(cell -> cell.getShip() != null && cell.isShot()))
                .build();
    }

    @Override
    public List<GameDto> getAllGames() {
        return gameDao.getAll()
                .stream()
                .map(gameMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GameDto updateGame(GameDto gameDto) {
        log.info("Обновление игры: {}", gameDto);
        return gameMapper.toDto(gameDao.update(gameMapper.toEntity(gameDto)));
    }

    @Override
    public void deleteGame(long id) {
        log.info("Удаление игры c id={}", id);
        gameDao.delete(id);
    }

    private List<Cell> generateEmptyCells() {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = new Cell();
                cell.setX((char) (i + '0'));
                cell.setY((char) (j + '0'));
                cell.setShot(false);
                cells.add(cell);
            }
        }
        return cells;
    }

    private Field generateShips(Field field) {
        int[][] bufField = new int[field.getCells().size()][field.getCells().size()];
        ships(bufField);
        List<Ship> ships = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                if (bufField[i][k] == 1) {
                    Ship ship = new Ship();
                    List<Cell> cellsForShip = new ArrayList<>();
                    int verticalLocation = checkForShip(bufField, i, k, 0);
                    int horizontalLocation = checkForShip(bufField, i, k, 1);
                    if (horizontalLocation > verticalLocation) {
                        for (int j = k; j < k + horizontalLocation; j++) {
                            cellsForShip.add(field.getCells().get((i * 10) + j));
                            bufField[i][j] = 0;
                        }
                    } else if (horizontalLocation < verticalLocation) {
                        for (int j = i; j < i + verticalLocation; j++) {
                            cellsForShip.add(field.getCells().get((j * 10) + k));
                            bufField[j][k] = 0;
                        }
                    } else {
                        cellsForShip.add(field.getCells().get((i * 10) + k));
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

    private Field fillField(Field field, List<Ship> ships) {
        for (Ship ship : ships) {
            List<Cell> shipCells = ship.getCells();
            ship.setCells(new ArrayList<>());
            for (int i = 0; i < field.getCells().size(); i++) {
                for (Cell shipCell : shipCells) {
                    if (shipCell.getX() == field.getCells().get(i).getX()
                            && shipCell.getY() == field.getCells().get(i).getY()) {
                        field.getCells().get(i).setShip(ship);
                    }
                }
            }
        }
        shipDao.saveAll(ships);
        return field;
    }


    private int checkForShip(int[][] bufField, int y, int x, int isHorizontal) {
        int size = 0;
        while (bufField[y][x] == 1) {
            size++;
            if (isHorizontal == 1) {
                x++;
            } else {
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
