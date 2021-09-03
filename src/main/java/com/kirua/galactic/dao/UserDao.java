package com.kirua.galactic.dao;

import com.kirua.galactic.domain.user.User;

import java.util.ArrayList;

public interface UserDao {

    void add(User user);

    ArrayList findAll();
}
