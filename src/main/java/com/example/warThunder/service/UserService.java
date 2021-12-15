package com.example.warThunder.service;

import com.example.warThunder.exception.NotUniqueUsernameException;
import com.example.warThunder.service.dto.UserDto;

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
