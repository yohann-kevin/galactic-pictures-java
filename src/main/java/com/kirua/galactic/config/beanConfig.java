package com.kirua.galactic.config;

import com.kirua.galactic.dao.GalacticPicturesDao;
import com.kirua.galactic.dao.GalacticPicturesMemory;
import com.kirua.galactic.service.GalacticPicturesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class beanConfig {

    @Bean
    GalacticPicturesMemory galacticPicturesMemory() {
        return new GalacticPicturesMemory();
    }

    @Bean
    GalacticPicturesService galacticPicturesService(GalacticPicturesDao galacticPicturesDao) {
        return new GalacticPicturesService(galacticPicturesDao);
    }
}
