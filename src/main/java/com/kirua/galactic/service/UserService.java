package com.kirua.galactic.service;

import com.kirua.galactic.dao.UserDao;
import com.kirua.galactic.domain.user.User;
import com.kirua.galactic.dto.SignUp;
import com.kirua.galactic.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(getClass());

//    public void add(String pseudo, String email, String password) {
//        User newUser = new User(UUID.randomUUID(), pseudo, email, password);
//        this.userDao.add(newUser);
//    }
//
    public ArrayList findAll() {
        return (ArrayList) this.userRepository.findAll();
    }

    public void signUp(SignUp signUp) {
        User person = User.builder()
                .id(UUID.randomUUID())
                .login(signUp.getLogin())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .role("USER")
                .build();
        userRepository.save(person);
        logger.info("New subscription : login={}", person.getLogin());
    }
}
