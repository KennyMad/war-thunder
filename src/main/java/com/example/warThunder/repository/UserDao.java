package com.example.warThunder.repository;
import java.util.List;
import com.example.warThunder.model.User;

public interface UserDao extends Dao<User> {

    User getUserByUsername(String username);

    User getUserByNamePass(User user);

    List<User> getSortByName();

    boolean isUsernameExist(String username);

}
