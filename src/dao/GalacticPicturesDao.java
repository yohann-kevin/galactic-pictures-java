package dao;

import domain.pictures.GalacticPictures;

import java.util.Set;

public interface GalacticPicturesDao {

     Set<GalacticPictures> findAll();

     GalacticPictures findByDate(String date);

     void add(GalacticPictures galacticPictures);

     void deleteById(String id);

     String seeDescription(String id);
}
