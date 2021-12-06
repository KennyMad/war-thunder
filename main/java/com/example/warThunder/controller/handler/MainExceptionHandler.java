package com.example.warThunder.controller.handler;

import com.example.warThunder.exception.NotUniqueUsername;
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
    public ResponseEntity<?> onNoResultException(NoResultException ex){
        log.warn("Не найден результат", ex);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotUniqueUsername.class)
    public ResponseEntity<?> onNotUniqueUsername(NotUniqueUsername ex){
        log.warn("Не уникальное имя пользователя {}", ex.getUsername());
        return new ResponseEntity<>("Не уникальное имя пользователя " + ex.getUsername(), HttpStatus.BAD_REQUEST);
    }

}
