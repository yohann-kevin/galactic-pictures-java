package dao;

import domain.pictures.GalacticPictures;

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
                System.out.println("plop plop");
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
}
