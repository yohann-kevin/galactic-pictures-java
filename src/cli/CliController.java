package cli;

import domain.pictures.GalacticPictures;
import service.GalacticPicturesService;

import java.util.List;
import java.util.Scanner;

public class CliController {
    private GalacticPicturesService galacticPicturesService;

    public CliController(GalacticPicturesService galacticPicturesService) {
        this.galacticPicturesService = galacticPicturesService;
    }

    public void start() {
        System.out.println("--------------------");
        System.out.println("1 - Display all pictures");
        System.out.println("--------------------");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

        if (choice.equals("1")) {
            List<GalacticPictures> galacticPicturesList = this.galacticPicturesService.findAll();
            for (GalacticPictures picture : galacticPicturesList) {
                System.out.println(picture);
            }
        }
    }
}
