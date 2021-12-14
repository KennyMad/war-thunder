package com.example.warThunder.exception;

import lombok.Getter;

public class NotUniqueUsernameException extends RuntimeException {

    @Getter
    private final String username;

    public NotUniqueUsernameException(String username) {
        this.username = username;
    }
}
