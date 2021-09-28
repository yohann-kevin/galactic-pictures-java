package com.kirua.galactic.repository;

import com.kirua.galactic.domain.api.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID>  {
    Token findTokenByName(String name);

    Token findTokenByToken(String token);
}
