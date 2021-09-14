package com.kirua.galactic.repository;

import com.kirua.galactic.domain.pictures.GalacticPictures;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.UUID;

public interface GalacticPictureRepository extends JpaRepository<GalacticPictures, UUID> {
    ArrayList findAllByOrderByDateDesc();

    GalacticPictures findByDate(String date);
}
