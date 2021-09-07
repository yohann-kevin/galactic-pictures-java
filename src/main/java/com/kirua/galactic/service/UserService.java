package com.kirua.galactic.service;

import com.kirua.galactic.dao.UserDao;
import com.kirua.galactic.domain.user.User;

import java.util.ArrayList;
import java.util.UUID;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(String pseudo, String email, String password) {
//        User newUser = new User(UUID.randomUUID(), pseudo, email, this.passwordEncoder.encode(password));
        User newUser = new User(UUID.randomUUID(), pseudo, email, password);
        this.userDao.add(newUser);
    }

    public ArrayList findAll() {
        return this.userDao.findAll();
    }
}
