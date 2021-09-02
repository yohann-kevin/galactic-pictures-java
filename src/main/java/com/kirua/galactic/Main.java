package com.kirua.galactic;

import com.kirua.galactic.controller.GalacticPictureController;
import com.kirua.galactic.service.GalacticPicturesService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx =  SpringApplication.run(Main.class, args);

        GalacticPicturesService galacticPicturesService = ctx.getBean(GalacticPicturesService.class);

        GalacticPictureController galacticPictureController = new GalacticPictureController(galacticPicturesService);
        galacticPictureController.findDataToNasaApi("2021-08-30");
        galacticPictureController.findDataToNasaApi("2021-08-31");
        galacticPictureController.findDataToNasaApi("2021-09-01");
        galacticPictureController.findDataToNasaApi("2021-09-02");
    }
}
