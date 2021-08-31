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
        // for (int i = 0; i < pictures.size(); i++) {
            // if (pictures.get(i).getDate().equals(date)) return pictures.get(i);
        // }
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
}
