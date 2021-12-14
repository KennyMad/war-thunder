package com.example.warThunder.exception;

import lombok.Getter;

public class WrongUserGameException extends RuntimeException {

    @Getter
    private final String message;

    public WrongUserGameException(String message) {
        this.message = message;
    }

}
