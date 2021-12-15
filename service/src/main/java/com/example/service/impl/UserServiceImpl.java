package com.example.service.impl;

import com.example.exception.NotUniqueUsernameException;
import com.example.exception.UserNotExistsException;
import com.example.models.User;
import com.example.repository.UserDao;
import com.example.service.UserService;
import com.example.service.dto.UserDto;
import com.example.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Создание пользователя " + userDto);
        if (userDao.isUsernameExist(userDto.getUsername())) {
            throw new NotUniqueUsernameException(userDto.getUsername());
        }
        User user = userMapper.toEntity(userDto);
        userDao.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Получение всех пользователей");
        return userDao.getAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto) {
        log.info("Обновление данных пользователя {}", userDto);
        User userInDb = userDao.getById(userDto.getId());
        if (userInDb == null) {
            throw new UserNotExistsException(userDto.getId());
        }
        userInDb.setName(userDto.getUsername());
        userInDb.setPassword(userDto.getPassword());
        return userMapper.toDto(userDao.update(userInDb));
    }

    @Transactional
    @Override
    public void deleteUser(UserDto userDto) {
        log.info("Удаление пользователя {}", userDto);
        User user = userDao.getUserByNamePass(userMapper.toEntity(userDto));
        if (user == null) {
            throw new UserNotExistsException(userDto.getUsername());
        }
        userDao.delete(user.getId());
    }

    @Override
    public UserDto getUserByNamePass(UserDto userDto) {
        log.info("Получение пользователя по имени и паролю");
        return userMapper.toDto(userDao.getUserByNamePass(userMapper.toEntity(userDto)));
    }

    @Override
    public List<UserDto> getSortByName() {
        log.info("Получение пользователей отсортированных по имени ");
        return userDao.getSortByName().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public boolean isUsernameExist(String username) {
        log.info("Проверка пользователя по имени {}", username);
        return userDao.isUsernameExist(username);
    }

    @Override
    public List<UserDto> getUsersWithTurnNumber(int turnNumber) {
        return userDao.getUsersWithTurnNumber(turnNumber)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
