package com.example.warThunder.service;

import com.example.warThunder.exception.NotUniqueUsername;
import com.example.warThunder.service.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto) throws NotUniqueUsername;

    List<UserDto> getAllUsers();

    UserDto getUserByNamePass(UserDto userDto);

}
