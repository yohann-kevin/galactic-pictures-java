package com.kirua.galactic.config;

import com.kirua.galactic.dao.GalacticPicturesDao;
import com.kirua.galactic.dao.UserDao;
import com.kirua.galactic.service.GalacticPicturesService;
import com.kirua.galactic.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    GalacticPicturesService galacticPicturesService(GalacticPicturesDao galacticPicturesDao) {
        return new GalacticPicturesService(galacticPicturesDao);
    }

    @Bean
    UserService userService(UserDao userDao) {
        return new UserService(userDao);
    }
}
