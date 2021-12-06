package com.example.warThunder.repository;

import com.example.warThunder.model.User;

public interface UserDao extends Dao<User> {

    User getUserByUsername(String username);

    User getUserByNamePass(User user);

    boolean isUsernameExist(String username);

}
