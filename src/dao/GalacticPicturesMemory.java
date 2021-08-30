package dao;

import domain.pictures.GalacticPictures;

import java.util.ArrayList;
import java.util.List;

public class GalacticPicturesMemory implements GalacticPicturesDao {
    private List<GalacticPictures> pictures = new ArrayList<>();

    @Override
    public List<GalacticPictures> findAll() {
        return pictures;
    }

    @Override
    public void add(GalacticPictures galacticPictures) {
        pictures.add(galacticPictures);
    }
}
