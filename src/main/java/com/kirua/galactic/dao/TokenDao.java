package com.kirua.galactic.dao;

import com.kirua.galactic.domain.api.Token;

import java.util.HashMap;

public interface TokenDao {
    void add(Token token);

    HashMap findTokenByName(String name);

    HashMap regenerateToken(String name);

    Boolean verifyTokenExist(String token);
}
