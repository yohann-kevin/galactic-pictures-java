package service;

import dao.GalacticPicturesDao;
import domain.pictures.GalacticPictures;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class GalacticPicturesService {
    private GalacticPicturesDao galacticPicturesDao;

    public GalacticPicturesService(GalacticPicturesDao galacticPicturesDao) {
        this.galacticPicturesDao = galacticPicturesDao;
    }

    public void add(String name, String date) {
        GalacticPictures newPictures = new GalacticPictures(UUID.randomUUID(), name, date);
        galacticPicturesDao.add(newPictures);
    }

    public Set<GalacticPictures> findAll() {
        return galacticPicturesDao.findAll();
    }

    public GalacticPictures findByDate(String date) { return galacticPicturesDao.findByDate(date); }

    public void deleteById(String id) { galacticPicturesDao.deleteById(id); }
}
