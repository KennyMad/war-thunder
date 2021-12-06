package com.example.warThunder.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto{

    private Long id;
    private String username;
    private String password;

    private List<HistoryDto> gameHistory;

}
