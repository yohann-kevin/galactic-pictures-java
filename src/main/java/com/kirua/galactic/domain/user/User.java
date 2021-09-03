package com.kirua.galactic.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @Column(length = 36)
    @org.hibernate.annotations.Type(type="uuid-char")
    private UUID id;
    private String pseudo;
    private String email;
    private String password;
    private boolean isAdmin;
    private boolean isModerator;

    public User(UUID id, String pseudo, String email, String password, boolean isAdmin, boolean isModerator) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isModerator = isModerator;
    }
}
