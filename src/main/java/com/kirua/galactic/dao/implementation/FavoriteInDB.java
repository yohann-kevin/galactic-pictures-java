package com.kirua.galactic.dao.implementation;

import com.kirua.galactic.dao.FavoriteDao;
import com.kirua.galactic.domain.favorite.Favorite;
import com.kirua.galactic.domain.user.User;
import com.kirua.galactic.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FavoriteInDB implements FavoriteDao {
    private final FavoriteRepository favoriteRepository;

    @Override
    public void add(Favorite favorite) {
        this.favoriteRepository.save(favorite);
    }

    public ArrayList<Object> findFavoritesPictureByUser(User user) {
        ArrayList<Favorite> favoriteList = this.favoriteRepository.findFavoriteByUser(user);
        ArrayList<Object> sortedList = new ArrayList<>();
        for (int i = 0; i < favoriteList.size(); i++) {
            HashMap<String, Object> details = new HashMap<>();
            details.put("id", favoriteList.get(i).getId().toString());
            details.put("user_id", favoriteList.get(i).getUser().getId().toString());
            details.put("pictures", favoriteList.get(i).getGalacticPictures());
            sortedList.add(details);
        }
        return sortedList;
    }

    public void deleteFavoritePictureById(String id) {
        UUID uid = UUID.fromString(id);
        this.favoriteRepository.deleteById(uid);
    }
}
