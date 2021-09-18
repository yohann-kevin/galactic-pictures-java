package com.kirua.galactic.dao;

import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.exception.InvalidUuidException;
import com.kirua.galactic.exception.PictureNotFoundException;

import java.util.ArrayList;
import java.util.UUID;

public interface GalacticPicturesDao {

     ArrayList findAll();

     GalacticPictures findByDate(String date) throws PictureNotFoundException;

     GalacticPictures findById(UUID id) throws PictureNotFoundException;

     void add(GalacticPictures galacticPictures);

     void deleteById(String id) throws PictureNotFoundException;

     void updatePicture(String id, String date, String description, String title, String mediaType, String copyright, String hdurl, String url) throws PictureNotFoundException;

     void likePicture(String id) throws InvalidUuidException;

     void unlikePicture(String id) throws InvalidUuidException;

     void downloadPicture(String id) throws InvalidUuidException;

     void resetDataPicture();
}
