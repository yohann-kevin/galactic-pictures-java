package com.kirua.galactic.dao;

import com.kirua.galactic.domain.pictures.GalacticPictures;

import java.util.ArrayList;

public interface GalacticPicturesDao {

     ArrayList findAll();

     GalacticPictures findByDate(String date);

     void add(GalacticPictures galacticPictures);

     void deleteById(String id);

     String seeDescription(String id);

     void updatePicture(String id, String name, String description, String date);
}
