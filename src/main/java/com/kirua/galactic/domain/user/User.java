package com.kirua.galactic.domain.user;

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
public class User {
    @Id
    @Column(length = 36)
    @org.hibernate.annotations.Type(type="uuid-char")
    private UUID id;
    private String password;
    @Column(unique=true)
    private String login;
    private String role;

    public User(UUID id, String password, String login, String role) {
        this.id = id;
        this.password = password;
        this.login = login;
        this.role = role;
    }
}
