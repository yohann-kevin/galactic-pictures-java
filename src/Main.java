import cli.CliController;
import dao.GalacticPicturesDao;
import dao.GalacticPicturesMemory;
import domain.user.User;
import service.GalacticPicturesService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GalacticPicturesDao galacticPicturesDao = new GalacticPicturesMemory();
        GalacticPicturesService galacticPicturesService = new GalacticPicturesService(galacticPicturesDao);
        CliController cli = new CliController(galacticPicturesService);
        initData(galacticPicturesService);
        cli.start();
    }

    private static void initData(GalacticPicturesService galacticPicturesService) {
        galacticPicturesService.add(
                "A Blue Moon in Exaggerated Colors",
                descOne(),
                "2021-08-31"
        );
        galacticPicturesService.add(
                "A Fire Rainbow over West Virginia",
                descTwo(),
                "2021-08-30"
        );
    }

    public static String descOne() {
        return "The Moon is normally seen in subtle shades of grey or gold.  " +
                "But small, measurable color differences have been greatly exaggerated " +
                "to make this telescopic, multicolored, moonscape captured during the Moon's full phase.  " +
                "The different colors are recognized to correspond to real differences in the " +
                "chemical makeup of the lunar surface.  Blue hues reveal titanium rich " +
                "areas while orange and purple colors show regions relatively poor in titanium and iron.  " +
                "The familiar Sea of Tranquility, or Mare Tranquillitatis, is the blue area toward the upper right.  " +
                "White lines radiate across the orange-hued southern lunar highlands from 85-kilometer wide ray-crater " +
                "Tycho at bottom right.  The full moon that occurred earlier this month could be counted as a " +
                "seasonal blue moon because it was, unusually, the third of four full moons to occur during " +
                "northern summer (and hence southern winter).  The featured 272-image composite " +
                "demonstrates that the full Moon is always blue, but usually not blue enough in hue to ooh.   " +
                "Almost Hyperspace: Random APOD Generator";
    }

    public static String descTwo() {
        return "What's happening to this cloud? Ice crystals in a distant cirrus cloud are acting " +
                "like little floating prisms.  Known informally as a fire rainbow for its flame-like " +
                "appearance, a circumhorizon arc appears parallel to the horizon. For a circumhorizontal arc to " +
                "be visible, the Sun must be at least 58 degrees high in a sky where cirrus clouds present below --  " +
                "in this case cirrus fibrates.  The numerous, flat, hexagonal ice-crystals that compose the " +
                "cirrus cloud must be aligned horizontally to properly refract sunlight in a collectively similar manner.  " +
                "Therefore, circumhorizontal arcs are somewhat unusual to see.  The featured fire rainbow was photographed " +
                "earlier this month near North Fork Mountain in West Virginia, USA.";
    }






    private static void initMenu() {
        System.out.println("1 - see all pictures");
        System.out.println("2 - see your favorites pictures");
    }

    public static boolean loginName(User user) {
        System.out.println("please log in");
        System.out.println("enter your name or your email : ");
        Object responseName = new Scanner(System.in).nextLine();
        System.out.println(responseName);
        if (responseName.equals(user.getPseudo()) || responseName.equals(user.getEmail())) {
            if (loginPass(user)) return true;
        } else {
            System.out.println("name or email is unknown");
            System.out.println("please try again");
            loginName(user);
        }
        return false;
    }

    public static boolean loginPass(User user) {
        System.out.println("please enter your password : ");
        Object responsePass = new Scanner(System.in).nextLine();
        if (responsePass.equals(user.getPassword())) {
            System.out.println("you connected well !");
            return true;
        } else {
            loginPass(user);
        }
        return false;
    }
}
