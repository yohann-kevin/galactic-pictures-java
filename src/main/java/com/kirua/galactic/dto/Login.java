package com.kirua.galactic.dto;

import lombok.Data;

@Data
public class Login {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
