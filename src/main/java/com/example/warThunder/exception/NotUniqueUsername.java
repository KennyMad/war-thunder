package com.example.warThunder.exception;

import lombok.Getter;

public class NotUniqueUsername extends RuntimeException{

    @Getter
    private final String username;
    public NotUniqueUsername(String username){
        this.username = username;
    }
}
