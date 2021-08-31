package cli;

import domain.pictures.GalacticPictures;
import service.GalacticPicturesService;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CliController {
    private GalacticPicturesService galacticPicturesService;

    public CliController(GalacticPicturesService galacticPicturesService) {
        this.galacticPicturesService = galacticPicturesService;
    }

    public void start() {
        System.out.println("--------------------");
        System.out.println("1 - Display all pictures");
        System.out.println("2 - Display picture by date");
        System.out.println("3 - Quit this program");
        System.out.println("--------------------");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        this.manageChoice(choice);
    }

    public void manageChoice(String choice) {
        if (choice.equals("1")) {
            Set<GalacticPictures> galacticPicturesList = this.galacticPicturesService.findAll();
            for (GalacticPictures picture : galacticPicturesList) {
                System.out.println(picture);
            }
            this.start();
        } else if (choice.equals("2")) {
            String date = this.getDateEntry();
            GalacticPictures galacticPicture = this.galacticPicturesService.findByDate(date);
            if (galacticPicture == null) {
                System.out.println("No image is available for this date !");
            } else {
                System.out.println(galacticPicture);
            }
            this.start();
        } else if (choice.equals("3")) {
            System.out.println("Good Bye !");
        }
    }

    public String getDateEntry() {
        System.out.println("--------------------");
        System.out.println("please enter a date");
        System.out.println("--------------------");
        Scanner scanner = new Scanner(System.in);
        String date = scanner.next();
        return date;
    }
}
