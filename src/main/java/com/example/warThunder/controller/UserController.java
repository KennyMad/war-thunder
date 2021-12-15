package com.example.warThunder.controller;

import com.example.warThunder.repository.UserDao;
import com.example.warThunder.service.UserService;
import com.example.warThunder.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto) {
        userService.deleteUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<UserDto>> getSortByName() {
        return new ResponseEntity<>(userService.getSortByName(), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<UserDto> getUserByNameAndPass(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.getUserByNamePass(userDto), HttpStatus.OK);
    }

    @GetMapping("/exist/{username}")
    public ResponseEntity<?> isExist(@PathVariable(name = "username") String username){
        return new ResponseEntity<>(userService.isUsernameExist(username), HttpStatus.OK);
    }

    @GetMapping("/turn/{number}")
    public ResponseEntity<?> getUsersWithTurnNumber (@PathVariable(name = "number") int number){
        return new ResponseEntity<>(userService.getUsersWithTurnNumber(number),HttpStatus.OK);
    }
}
