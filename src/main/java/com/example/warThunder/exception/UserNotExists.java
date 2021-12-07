package com.example.warThunder.exception;

import lombok.Getter;

public class UserNotExists extends RuntimeException{

    @Getter
    private final String username;
    public UserNotExists (String username){
        this.username = username;
    }

}
