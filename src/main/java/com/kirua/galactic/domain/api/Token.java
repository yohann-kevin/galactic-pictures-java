package com.kirua.galactic.domain.api;

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
public class Token {
    @Id
    @Column(length = 36)
    @org.hibernate.annotations.Type(type="uuid-char")
    private UUID id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String token;
}
