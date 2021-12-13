package com.example.warThunder.exception;

import lombok.Getter;

public class WrongShootPointException extends RuntimeException {

    @Getter
    private String message;
    public WrongShootPointException (char x, char y){
        message = "Wrong point x: " + x + " y: " + y;
    }

}
