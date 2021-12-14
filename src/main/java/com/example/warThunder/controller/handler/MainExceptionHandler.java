package com.example.warThunder.controller.handler;

import com.example.warThunder.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;

@ControllerAdvice
@Slf4j
public class MainExceptionHandler {

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<?> onNoResultException(NoResultException ex) {
        log.warn("Не найден результат {}", ex.getMessage());
        return new ResponseEntity<>("Не найден результат " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotUniqueUsernameException.class)
    public ResponseEntity<?> onNotUniqueUsername(NotUniqueUsernameException ex) {
        log.warn("Не уникальное имя пользователя {}", ex.getUsername());
        return new ResponseEntity<>("Не уникальное имя пользователя " + ex.getUsername(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotExistsException.class)
    public ResponseEntity<?> onUserNotExists(UserNotExistsException ex) {
        log.warn("Пользователя не существует {}", ex.getUsername());
        return new ResponseEntity<>("Пользователя не существует " + ex.getUsername(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongUserGameException.class)
    public ResponseEntity<?> onWrongUserGameException(WrongUserGameException exception) {
        log.warn(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongUserTurnException.class)
    public ResponseEntity<?> onWrongUserTurnException(WrongUserTurnException exception) {
        log.warn("Ход не того пользователя");
        return new ResponseEntity<>("Ход не правильного пользователя", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongShootPointException.class)
    public ResponseEntity<?> onWrongShootPointException(WrongShootPointException exception) {
        log.warn(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> onException(Exception e) {
        log.error("Unexpected exception error", e);
        return new ResponseEntity("Unexpected exception error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
