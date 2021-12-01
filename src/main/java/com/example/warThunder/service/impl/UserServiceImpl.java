package com.example.warThunder.service.impl;

import com.example.warThunder.model.User;
import com.example.warThunder.repository.UserDao;
import com.example.warThunder.service.UserService;
import com.example.warThunder.service.dto.CreateUserDto;
import com.example.warThunder.service.dto.UserDto;
import com.example.warThunder.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(CreateUserDto createUserDto) {
        log.info("Создание пользователя " + createUserDto);
        return userMapper.toDto(userDao.save(User.builder()
                .name(createUserDto.getUsername())
                .password(createUserDto.getPassword())
                .build()));
    }
}
