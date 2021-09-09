package com.kirua.galactic.dao.implementation;

import com.kirua.galactic.dao.FavoriteDao;
import com.kirua.galactic.domain.favorite.Favorite;
import com.kirua.galactic.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FavoriteInDB implements FavoriteDao {
    private final FavoriteRepository favoriteRepository;

    @Override
    public void add(Favorite favorite) {
        this.favoriteRepository.save(favorite);
    }
}
