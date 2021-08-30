package service;

import dao.GalacticPicturesDao;
import domain.pictures.GalacticPictures;

import java.util.List;
import java.util.UUID;

public class GalacticPicturesService {
    private GalacticPicturesDao galacticPicturesDao;

    public GalacticPicturesService(GalacticPicturesDao galacticPicturesDao) {
        this.galacticPicturesDao = galacticPicturesDao;
    }

    public void add(String name) {
        GalacticPictures newPictures = new GalacticPictures(UUID.randomUUID(), name);
        galacticPicturesDao.add(newPictures);
    }

    public List<GalacticPictures> findAll() {
        return galacticPicturesDao.findAll();
    }
}
