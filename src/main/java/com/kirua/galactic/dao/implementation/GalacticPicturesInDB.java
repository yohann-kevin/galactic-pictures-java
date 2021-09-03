package com.kirua.galactic.dao.implementation;

import com.kirua.galactic.dao.GalacticPicturesDao;
import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.exception.InvalidUuidException;
import com.kirua.galactic.repository.GalacticPictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class GalacticPicturesInDB implements GalacticPicturesDao {
    private final GalacticPictureRepository galacticPictureRepository;

    @Override
    public ArrayList findAll() {
        return (ArrayList) this.galacticPictureRepository.findAll();
    }

    public Optional<GalacticPictures> findById(UUID id) {
        return this.galacticPictureRepository.findById(id);
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

    @Override
    public void likePicture(String id) {
        UUID uid = UUID.fromString(id);
        Optional<GalacticPictures> pictures = this.findById(uid);
        int actuallyLike = pictures.get().getToLike();
        pictures.get().setToLike(actuallyLike + 1);
        galacticPictureRepository.save(pictures.get());
    }

    @Override
    public void downloadPicture(String id) throws InvalidUuidException {
        try {
            UUID uid = UUID.fromString(id);
            Optional<GalacticPictures> pictures = this.findById(uid);
            int numDl = pictures.get().getDownload();
            pictures.get().setDownload(numDl + 1);
            galacticPictureRepository.save(pictures.get());
        } catch (Exception e) {
            throw new InvalidUuidException("id invalid");
        }
    }
}
