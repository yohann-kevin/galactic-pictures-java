package main.java.com.kirua.galactic.domain.user;

public class User {
    private int id;
    private String pseudo;
    private String email;
    private String password;

    public User(int id, String pseudo, String email, String password) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
