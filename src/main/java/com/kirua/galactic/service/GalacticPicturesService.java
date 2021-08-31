package com.kirua.galactic.service;

import com.kirua.galactic.dao.GalacticPicturesDao;
import com.kirua.galactic.domain.pictures.GalacticPictures;

import java.util.Set;
import java.util.UUID;

public class GalacticPicturesService {
    private GalacticPicturesDao galacticPicturesDao;

    public GalacticPicturesService(GalacticPicturesDao galacticPicturesDao) {
        this.galacticPicturesDao = galacticPicturesDao;
    }

    public GalacticPictures add(String name, String description, String date) {
        GalacticPictures newPictures = new GalacticPictures(UUID.randomUUID(), name, description, date);
        galacticPicturesDao.add(newPictures);
        return newPictures;
    }

    public Set<GalacticPictures> findAll() {
        return galacticPicturesDao.findAll();
    }

    public GalacticPictures findByDate(String date) { return galacticPicturesDao.findByDate(date); }

    public void deleteById(String id) { galacticPicturesDao.deleteById(id); }

    public String seeDescription(String id) { return galacticPicturesDao.seeDescription(id); }
}
