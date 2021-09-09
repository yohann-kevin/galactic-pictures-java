package com.kirua.galactic.repository;

import com.kirua.galactic.domain.favorite.Favorite;
import com.kirua.galactic.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
    ArrayList<Favorite> findFavoriteByUser(User user);
}
