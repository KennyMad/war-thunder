package com.example.service.mapper;

import com.example.models.User;
import com.example.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    HistoryMapper historyMapper;

    public User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        return user;
    }

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getName());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

}
