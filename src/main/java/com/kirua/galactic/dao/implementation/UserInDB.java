package com.kirua.galactic.dao.implementation;

import com.kirua.galactic.dao.UserDao;
import com.kirua.galactic.domain.user.User;
import com.kirua.galactic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class UserInDB implements UserDao {
    private final UserRepository userRepository;

    @Override
    public void add(User user) {
        this.userRepository.save(user);
    }

    @Override
    public ArrayList findAll() {
        return (ArrayList) this.userRepository.findAll();
    }
}
