package com.kirua.galactic.repository;

import com.kirua.galactic.domain.pictures.GalacticPictures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface GalacticPictureRepository extends JpaRepository<GalacticPictures, UUID> {

//    @Query("select g from GalacticPictures g where g.date = ?1")
    GalacticPictures findByDate(String date);
}
