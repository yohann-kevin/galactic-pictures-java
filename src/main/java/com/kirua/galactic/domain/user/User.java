package com.kirua.galactic.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(length = 36)
    @org.hibernate.annotations.Type(type="uuid-char")
    private UUID id;
//    private String pseudo;
//    private String email;
    private String password;

    private String login;
    private String role;

//    public User(UUID id, String pseudo, String email, String password) {
//        this.id = id;
//        this.pseudo = pseudo;
//        this.email = email;
//        this.password = password;
//    }
}
