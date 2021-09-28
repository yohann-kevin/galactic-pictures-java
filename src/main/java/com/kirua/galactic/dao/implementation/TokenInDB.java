package com.kirua.galactic.dao.implementation;

import com.kirua.galactic.dao.TokenDao;
import com.kirua.galactic.domain.api.Token;
import com.kirua.galactic.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@RequiredArgsConstructor
public class TokenInDB implements TokenDao {
    private final TokenRepository tokenRepository;

    @Override
    public void add(Token token) {
        this.tokenRepository.save(token);
    }

    @Override
    public HashMap findTokenByName(String name) {
        HashMap token = new HashMap();
        token.put("token", this.tokenRepository.findTokenByName(name).getToken());
        return token;
    }
}
