package com.kirua.galactic;

import com.kirua.galactic.controller.GalacticPictureController;
import com.kirua.galactic.service.GalacticPicturesService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx =  SpringApplication.run(Main.class, args);

        GalacticPicturesService galacticPicturesService = ctx.getBean(GalacticPicturesService.class);
        GalacticPictureController galacticPictureController = new GalacticPictureController(galacticPicturesService);

        Date currentDate = new Date();
        for (int i = 0; i > -5; i--) {
            Date beforeToday = addDays(currentDate, i);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            galacticPictureController.getDataFromNasaApi(dateFormat.format(beforeToday));
        }
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
}
