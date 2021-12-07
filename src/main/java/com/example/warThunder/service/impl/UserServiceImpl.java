package com.example.warThunder.service.impl;

import com.example.warThunder.exception.NotUniqueUsername;
import com.example.warThunder.repository.UserDao;
import com.example.warThunder.service.UserService;
import com.example.warThunder.service.dto.UserDto;
import com.example.warThunder.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Создание пользователя " + userDto);
        if (userDao.isUsernameExist(userDto.getUsername())) {
            throw new NotUniqueUsername(userDto.getUsername());
        }
        return userMapper.toDto(userDao.save(userMapper.toEntity(userDto)));
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Получение всех пользователей");
        return userDao.getAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByNamePass(UserDto userDto) {
        log.info("Получение пользователя по имени и паролю");
        return userMapper.toDto(userDao.getUserByNamePass(userMapper.toEntity(userDto)));
    }

    @Override
    public List<UserDto> getSortByName(){
        log.info("Получение пользователей отсортированных по имени ");
        return userDao.getSortByName().stream().map(userMapper::toDto).collect(Collectors.toList());
    }
}
