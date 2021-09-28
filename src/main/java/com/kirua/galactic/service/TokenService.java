package com.kirua.galactic.service;

import com.kirua.galactic.dao.TokenDao;
import com.kirua.galactic.domain.api.Token;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    private TokenDao tokenDao;

    public TokenService(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    public void registerToken(String name, String initialToken) {
        Token finalToken = new Token(UUID.randomUUID(), name, initialToken);
        this.tokenDao.add(finalToken);
    }
}
