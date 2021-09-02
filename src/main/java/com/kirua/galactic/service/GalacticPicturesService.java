package com.kirua.galactic.service;

import com.kirua.galactic.dao.GalacticPicturesDao;
import com.kirua.galactic.domain.pictures.GalacticPictures;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class GalacticPicturesService {
    private GalacticPicturesDao galacticPicturesDao;

    public GalacticPicturesService(GalacticPicturesDao galacticPicturesDao) {
        this.galacticPicturesDao = galacticPicturesDao;
    }

    public GalacticPictures add(String name, String description, String date, String url, String hdurl, String copyright, String mediaType) {
        GalacticPictures newPictures = new GalacticPictures(UUID.randomUUID(), date, description, name, mediaType, copyright, hdurl, url);
        galacticPicturesDao.add(newPictures);
        return newPictures;
    }

    public ArrayList<GalacticPictures> findAll() {
        return galacticPicturesDao.findAll();
    }

    public GalacticPictures findByDate(String date) { return galacticPicturesDao.findByDate(date); }

    public void deleteById(String id) { galacticPicturesDao.deleteById(id); }

    public String seeDescription(String id) { return galacticPicturesDao.seeDescription(id); }

    public void updatePicture(String id, String name, String description, String date) { galacticPicturesDao.updatePicture(id, name, description, date); }
}
