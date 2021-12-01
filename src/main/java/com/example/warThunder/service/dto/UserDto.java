package com.example.warThunder.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto extends AbstractDto{

    private Long id;
    private String username;
    private String password;

}
