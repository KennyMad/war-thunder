package com.example.warThunder.service.mapper;

import com.example.warThunder.model.User;
import com.example.warThunder.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    HistoryMapper historyMapper;

    public User toEntity(UserDto userDto){
        if (userDto == null){
            return null;
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        if (userDto.getGameHistory() == null){
            user.setGameHistory(null);
        }
        else {
            user.setGameHistory(userDto.getGameHistory().stream()
                    .map(historyDto -> historyMapper.toEntity(historyDto))
                    .collect(Collectors.toList()));
        }
        return user;
    }

    public UserDto toDto(User user){
        if (user == null){
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getName());
        userDto.setPassword(user.getPassword());
        if (user.getGameHistory() == null){
            user.setGameHistory(null);
        }
        else {
            userDto.setGameHistory(user.getGameHistory().stream()
                    .map(history -> historyMapper.toDto(history))
                    .collect(Collectors.toList()));
        }
        return userDto;
    }

}
