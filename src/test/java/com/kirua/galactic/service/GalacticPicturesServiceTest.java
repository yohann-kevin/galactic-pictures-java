package com.kirua.galactic.service;

import com.kirua.galactic.dao.GalacticPicturesDao;
import com.kirua.galactic.domain.pictures.GalacticPictures;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GalacticPicturesServiceTest {
//    GalacticPicturesDao picturesDao;
//    GalacticPicturesService picturesService;
//    GalacticPictures pictureTestOne;
//    GalacticPictures pictureTestTwo;
//
//    @Before
//    public void setUp() {
//        picturesDao = mock(GalacticPicturesDao.class);
//        picturesService = new GalacticPicturesService(picturesDao);
//        pictureTestOne = picturesService.add("plop", "plop plop", "2020-10-12");
//        pictureTestTwo = picturesService.add("plop 2", "plop plop 2", "2020-10-13");
//    }
//
//    @Test
//    public void add() {
//        GalacticPictures galacticPictures = picturesService.add("plop", "plop plop", "2020-10-12");
//        verify(picturesDao).add(galacticPictures);
//    }
//
//    @Test
//    public void findAll() {
//        Set<GalacticPictures> expectedPictures = new HashSet<GalacticPictures>();
//        expectedPictures.add(pictureTestOne);
//        expectedPictures.add(pictureTestTwo);
//
//        when(picturesDao.findAll()).thenReturn(expectedPictures);
//        Set<GalacticPictures> actualPicture = picturesService.findAll();
//        assertThat(actualPicture, equalTo(expectedPictures));
//    }
//
//    @Test
//    public void findByDate() {
//        GalacticPictures expectedPicture = pictureTestOne;
//        String date = "2020-10-12";
//
//        when(picturesDao.findByDate(date)).thenReturn(expectedPicture);
//        GalacticPictures actualPicture = picturesService.findByDate(date);
//        assertThat(actualPicture, equalTo(expectedPicture));
//    }
//
//    @Test
//    public void findByWrongDate() {
//        String date = "2021-11-06";
//        GalacticPictures actualPicture = picturesService.findByDate(date);
//        assertThat(actualPicture, equalTo(null));
//    }
//
//    @Test
//    public void deleteById() {
//        UUID uid = pictureTestOne.getId();
//        picturesService.deleteById(uid.toString());
//        verify(picturesDao).deleteById(uid.toString());
//    }
//
//    @Test
//    public void seeDescription() {
//        UUID uid = pictureTestOne.getId();
//        picturesService.seeDescription(uid.toString());
//        verify(picturesDao).seeDescription(uid.toString());
//    }
}