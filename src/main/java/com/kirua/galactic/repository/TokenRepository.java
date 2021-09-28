package com.kirua.galactic.repository;

import com.kirua.galactic.domain.api.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID>  {
}
