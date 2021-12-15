package com.example.repository;

import java.util.List;

import com.example.models.User;

public interface UserDao extends Dao<User> {

    User getUserByUsername(String username);

    User getUserByNamePass(User user);

    List<User> getSortByName();

    boolean isUsernameExist(String username);

    List<User> getUsersWithTurnNumber(int turnNumber);

}
