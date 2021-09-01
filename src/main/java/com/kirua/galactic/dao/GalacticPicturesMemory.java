package com.kirua.galactic.dao;

import com.kirua.galactic.domain.pictures.GalacticPictures;

import java.util.*;

public class GalacticPicturesMemory implements GalacticPicturesDao {
    private Set<GalacticPictures> pictures = new HashSet<>();

    @Override
    public Set<GalacticPictures> findAll() {
        return pictures;
    }

    @Override
    public GalacticPictures findByDate(String date) {
        Iterator<GalacticPictures> iteratePicture = pictures.iterator();
        while (iteratePicture.hasNext()) {
            GalacticPictures currentPicture = iteratePicture.next();
            if (currentPicture.getDate().equals(date)) return  currentPicture;
        }
        return null;
    }

    @Override
    public void add(GalacticPictures galacticPictures) {
        pictures.add(galacticPictures);
    }

    @Override
    public void deleteById(String id) {
        UUID uid = UUID.fromString(id);
        Iterator<GalacticPictures> iteratePicture = pictures.iterator();
        while (iteratePicture.hasNext()) {
            GalacticPictures currentPicture = iteratePicture.next();
            if (currentPicture.getId().equals(uid)) {
                iteratePicture.remove();
            }
        }
    }

    @Override
    public String seeDescription(String id) {
        UUID uid = UUID.fromString(id);
        Iterator<GalacticPictures> iteratePicture = pictures.iterator();
        while (iteratePicture.hasNext()) {
            GalacticPictures currentPicture = iteratePicture.next();
            if (currentPicture.getId().equals(uid)) return currentPicture.getDescription();
        }
        return null;
    }

    @Override
    public void updatePicture(String id, String name, String description, String date) {
        UUID uid = UUID.fromString(id);
        Iterator<GalacticPictures> iteratePicture = pictures.iterator();
        GalacticPictures selectedPicture = null;
        while (iteratePicture.hasNext()) {
            GalacticPictures currentPicture = iteratePicture.next();
            if (currentPicture.getId().equals(uid)) selectedPicture = currentPicture;
        }
        selectedPicture.setTitle(name);
        selectedPicture.setDescription(description);
        selectedPicture.setDate(date);
    }
}
