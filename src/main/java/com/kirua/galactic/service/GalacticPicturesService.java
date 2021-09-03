package com.kirua.galactic.service;

import com.kirua.galactic.dao.GalacticPicturesDao;
import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.exception.InvalidUuidException;
import com.kirua.galactic.exception.PictureNotFoundException;

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

    public GalacticPictures findByDate(String date) throws PictureNotFoundException { return galacticPicturesDao.findByDate(date); }

    public GalacticPictures findById(String id) throws PictureNotFoundException {
        UUID uid = UUID.fromString(id);
        return galacticPicturesDao.findById(uid);
    }

    public void deleteById(String id) throws PictureNotFoundException { galacticPicturesDao.deleteById(id); }

    public String seeDescription(String id) { return galacticPicturesDao.seeDescription(id); }

    public void updatePicture(String id, String date, String description, String title, String mediaType, String copyright, String hdurl, String url) throws PictureNotFoundException { galacticPicturesDao.updatePicture(id, date, description, title, mediaType, copyright, hdurl, url); }

    public void likePicture(String id) throws InvalidUuidException { galacticPicturesDao.likePicture(id); }

    public void donwloadPicture(String id) throws InvalidUuidException { galacticPicturesDao.downloadPicture(id); }
}
