package com.kirua.galactic.repository;

import com.kirua.galactic.domain.favorite.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
}
