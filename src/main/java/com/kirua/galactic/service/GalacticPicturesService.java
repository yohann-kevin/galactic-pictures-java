package com.kirua.galactic.service;

import com.kirua.galactic.dao.GalacticPicturesDao;
import com.kirua.galactic.domain.pictures.GalacticPictures;

import java.util.ArrayList;
import java.util.UUID;

public class GalacticPicturesService {
    private GalacticPicturesDao galacticPicturesDao;

    public GalacticPicturesService(GalacticPicturesDao galacticPicturesDao) {
        this.galacticPicturesDao = galacticPicturesDao;
    }

    public GalacticPictures add(String date, String description, String name, String copyright, String mediaType, String url, String hdurl) {
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

    public void likePicture(String id) { galacticPicturesDao.likePicture(id); }

    public void donwloadPicture(String id) { galacticPicturesDao.downloadPicture(id); }
}
