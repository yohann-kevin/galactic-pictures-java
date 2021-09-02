package com.kirua.galactic.dao.implementation;

import com.kirua.galactic.dao.GalacticPicturesDao;
import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.repository.GalacticPictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class GalacticPicturesInDB implements GalacticPicturesDao {
    private final GalacticPictureRepository galacticPictureRepository;

    @Override
    public ArrayList findAll() {
        return (ArrayList) this.galacticPictureRepository.findAll();
    }

    @Override
    public GalacticPictures findByDate(String date) {
        return null;
    }

    @Override
    public void add(GalacticPictures galacticPictures) {
        galacticPictureRepository.save(galacticPictures);
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public String seeDescription(String id) {
        return null;
    }

    @Override
    public void updatePicture(String id, String name, String description, String date) {

    }
}
