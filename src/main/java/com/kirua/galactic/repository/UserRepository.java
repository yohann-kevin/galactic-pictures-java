package com.kirua.galactic.repository;

import com.kirua.galactic.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findPersonByLogin(String login);
}
