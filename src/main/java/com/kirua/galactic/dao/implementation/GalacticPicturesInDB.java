package com.kirua.galactic.dao.implementation;

import com.kirua.galactic.dao.GalacticPicturesDao;
import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.exception.InvalidUuidException;
import com.kirua.galactic.exception.PictureNotFoundException;
import com.kirua.galactic.repository.GalacticPictureRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class GalacticPicturesInDB implements GalacticPicturesDao {
    private final GalacticPictureRepository galacticPictureRepository;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ArrayList findAll() {
        return (ArrayList) this.galacticPictureRepository.findAllByOrderByDateDesc();
    }

    @Override
    public GalacticPictures findById(UUID id) throws PictureNotFoundException {
        try {
            return this.galacticPictureRepository.findById(id).get();
        } catch (Exception e) {
            String err = "this picture is not save. error : " + e;
            logger.error(err);
            throw new PictureNotFoundException(err);
        }
    }

    @Override
    public ArrayList<GalacticPictures> findPictureBetweenTwoDate(String start, String end) {
        return this.galacticPictureRepository.findAllByDateBetween(start, end);
    }

    @Override
    public GalacticPictures findByDate(String date) throws PictureNotFoundException {
        try {
            return this.galacticPictureRepository.findByDate(date);
        } catch (Exception e) {
            String err = "this picture is not save. error : " + e;
            logger.error(err);
            throw new PictureNotFoundException(err);
        }
    }

    @Override
    public void add(GalacticPictures galacticPictures) {
        this.galacticPictureRepository.save(galacticPictures);
    }

    @Override
    public void deleteById(String id) throws PictureNotFoundException {
        UUID uid = UUID.fromString(id);
        try {
            GalacticPictures picture = this.galacticPictureRepository.findById(uid).get();
            this.galacticPictureRepository.delete(picture);
        } catch (Exception e) {
            String err = "this picture is not save or already delete. error : " + e;
            logger.error(err);
            throw new PictureNotFoundException(err);
        }
    }

    @Override
    public void updatePicture(String id, String date, String description, String title, String mediaType, String copyright, String hdurl, String url) throws PictureNotFoundException {
        try {
            UUID uid = UUID.fromString(id);
            GalacticPictures pictures = this.galacticPictureRepository.findById(uid).get();
            pictures.setDate(date);
            pictures.setDescription(description);
            pictures.setTitle(title);
            pictures.setMediaType(mediaType);
            pictures.setCopyright(copyright);
            pictures.setUrl(url);
            pictures.setHdurl(hdurl);
            this.galacticPictureRepository.save(pictures);
        } catch (Exception e) {
            String err = "this picture is not save" + e;
            logger.error(err);
            throw new PictureNotFoundException(err);
        }
    }

    @Override
    public void likePicture(String id) throws InvalidUuidException {
        try {
            UUID uid = UUID.fromString(id);
            GalacticPictures pictures = this.findById(uid);
            int actuallyLike = pictures.getToLike();
            pictures.setToLike(actuallyLike + 1);
            galacticPictureRepository.save(pictures);
        } catch (Exception e) {
            String err = "this id is invalid id accepted is : uuid. error" + e;
            logger.error(err);
            throw new InvalidUuidException(err);
        }
    }

    @Override
    public void unlikePicture(String id) throws InvalidUuidException {
        try {
            UUID uid = UUID.fromString(id);
            GalacticPictures pictures = this.findById(uid);
            int actuallyLike = pictures.getToLike();
            pictures.setToLike(actuallyLike - 1);
            galacticPictureRepository.save(pictures);
        } catch (Exception e) {
            String err = "this id is invalid id accepted is : uuid. error" + e;
            logger.error(err);
            throw new InvalidUuidException(err);
        }
    }

    @Override
    public void downloadPicture(String id) throws InvalidUuidException {
        try {
            UUID uid = UUID.fromString(id);
            GalacticPictures pictures = this.findById(uid);
            int numDl = pictures.getDownload();
            pictures.setDownload(numDl + 1);
            galacticPictureRepository.save(pictures);
        } catch (Exception e) {
            String err = "this id is invalid id accepted is : uuid. error" + e;
            logger.error(err);
            throw new InvalidUuidException(err);
        }
    }

    @Override
    public void resetDataPicture() {
        galacticPictureRepository.deleteAllInBatch();
    }
}
