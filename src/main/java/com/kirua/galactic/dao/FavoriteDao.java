package com.kirua.galactic.dao;

import com.kirua.galactic.domain.favorite.Favorite;
import com.kirua.galactic.domain.user.User;

import java.util.ArrayList;

public interface FavoriteDao {
    void add(Favorite favorite);

    ArrayList<Object> findFavoritesPictureByUser(User user);
}
