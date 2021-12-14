package com.example.warThunder.exception;

import lombok.Getter;

public class UserNotExistsException extends RuntimeException {

    @Getter
    private final String username;

    public UserNotExistsException(String username) {
        this.username = username;
    }

    public UserNotExistsException(long id) {
        this.username = String.valueOf(id);
    }

}
