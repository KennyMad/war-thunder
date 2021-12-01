package com.example.warThunder.repository.impl;

import com.example.warThunder.model.User;
import com.example.warThunder.repository.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
