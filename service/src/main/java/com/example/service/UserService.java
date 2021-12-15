package com.example.service;

import com.example.exception.NotUniqueUsernameException;
import com.example.service.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto) throws NotUniqueUsernameException;

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto);

    void deleteUser(UserDto userDto);

    UserDto getUserByNamePass(UserDto userDto);

    List<UserDto> getSortByName();

    boolean isUsernameExist(String username);

    List<UserDto> getUsersWithTurnNumber(int turnNumber);

}
