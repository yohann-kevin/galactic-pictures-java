package cli;

import domain.pictures.GalacticPictures;
import service.GalacticPicturesService;

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
        System.out.println("3 - Delete picture with id");
        System.out.println("4 - See picture description");
        System.out.println("5 - Quit this program");
        System.out.println("--------------------");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        this.manageChoice(choice);
    }

    public void manageChoice(String choice) {
        if (choice.equals("1")) {
            this.displayAllPicture();
            this.start();
        } else if (choice.equals("2")) {
            this.displayPictureBydate();
            this.start();
        } else if (choice.equals("3")) {
            this.deletePicture();
            this.start();
        } else if (choice.equals("4")) {
            this.displayDescription();
            this.start();
        } else if (choice.equals("5")) {
            System.out.println("Good Bye !");
        }
    }

    public void displayAllPicture() {
        Set<GalacticPictures> galacticPicturesList = this.galacticPicturesService.findAll();
        for (GalacticPictures picture : galacticPicturesList) {
            System.out.println(picture);
        }
    }

    public void displayPictureBydate() {
        String date = this.getDateEntry();
        GalacticPictures galacticPicture = this.galacticPicturesService.findByDate(date);
        if (galacticPicture == null) {
            System.out.println("No image is available for this date !");
        } else {
            System.out.println(galacticPicture);
        }
    }

    public void deletePicture() {
        Set<GalacticPictures> galacticPicturesList = this.galacticPicturesService.findAll();
        for (GalacticPictures picture : galacticPicturesList) {
            System.out.println(picture.getTitle() + " : " + picture.getId());
        }
        String id = this.getIdEntry();
        this.galacticPicturesService.deleteById(id);
        System.out.println("Picture is deleted");
    }

    public void displayDescription() {
        Set<GalacticPictures> galacticPicturesList = this.galacticPicturesService.findAll();
        for (GalacticPictures picture : galacticPicturesList) {
            System.out.println(picture.getTitle() + " : " + picture.getId());
        }
        String id = this.getIdEntry();
        String description = this.galacticPicturesService.seeDescription(id);
        if (description == null) {
            System.out.println("No image is available for this id !");
        } else {
            System.out.println(description);
        }
    }

    public String getIdEntry() {
        System.out.println("--------------------");
        System.out.println("please enter a id");
        System.out.println("--------------------");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        return id;
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
