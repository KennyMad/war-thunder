package com.example.warThunder.service.mapper;

import com.example.warThunder.model.User;
import com.example.warThunder.service.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

}
