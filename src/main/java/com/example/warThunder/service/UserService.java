package com.example.warThunder.service;

import com.example.warThunder.service.dto.CreateUserDto;
import com.example.warThunder.service.dto.UserDto;

public interface UserService {

    UserDto createUser(CreateUserDto createUserDto);

}
