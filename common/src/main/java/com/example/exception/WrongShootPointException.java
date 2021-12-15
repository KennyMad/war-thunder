package com.example.exception;

import lombok.Getter;

public class WrongShootPointException extends RuntimeException {

    @Getter
    private final String message;

    public WrongShootPointException(char x, char y) {
        message = "В точку x: " + x + " y: " + y + " уже стреляли";
    }

}
