package dao;

import domain.pictures.GalacticPictures;

import java.util.ArrayList;
import java.util.List;

public class GalacticPicturesRepository implements GalacticPicturesDao {
    private List<GalacticPictures> pictures = new ArrayList<>();

    @Override
    public List<GalacticPictures> findAll() {
        return pictures;
    }
}
