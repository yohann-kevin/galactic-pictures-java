package com.kirua.galactic.service;

import com.kirua.galactic.dao.FavoriteDao;
import com.kirua.galactic.domain.favorite.Favorite;
import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class FavoriteService {
    private FavoriteDao favoriteDao;

    public FavoriteService(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    public void add(User user, GalacticPictures pictures) {
        Favorite newFavoritePicture = new Favorite(UUID.randomUUID(), user, pictures);
        this.favoriteDao.add(newFavoritePicture);
    }

    public ArrayList<Object> findFavoriteByUser(User user) {
        return this.favoriteDao.findFavoritesPictureByUser(user);
    }
}
