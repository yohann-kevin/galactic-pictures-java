import domain.user.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // User user = new User(1, "kirua", "yo44prg@icloud.com", "plop");
        // if (loginName(user)) {
           // initMenu();
        // }
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
