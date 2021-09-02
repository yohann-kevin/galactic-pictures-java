package com.kirua.galactic.controller;

import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.service.GalacticPicturesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(GalacticPictureController.class)
public class GalacticPictureControllerTest {
    @MockBean
    private GalacticPicturesService galacticPicturesService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void displayAllPicture() throws Exception {
        Set<GalacticPictures> allPicture = new HashSet<>();
        UUID uid = UUID.randomUUID();
        allPicture.add(new GalacticPictures(uid, "plop", "plop", "plop", "plop", "plop", "plop", "plop"));
        when(galacticPicturesService.findAll()).thenReturn(allPicture);
        mockMvc.perform(get("/picture"))
                .andExpect(jsonPath("$[0].id", is(uid.toString())))
                .andExpect(jsonPath("$[0].title", is("plop")))
                .andExpect(jsonPath("$[0].description", is("plop")))
                .andExpect(jsonPath("$[0].date", is("plop")))
                .andExpect(jsonPath("$[0].url", is("plop")))
                .andExpect(jsonPath("$[0].hdurl", is("plop")))
                .andExpect(jsonPath("$[0].copyright", is("plop")))
                .andExpect(jsonPath("$[0].mediaType", is("plop")));
    }
}