package com.kirua.galactic.domain.comment;

import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @Column(length = 36)
    @org.hibernate.annotations.Type(type="uuid-char")
    private UUID id;

    private String content;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "picture_id", nullable = false)
    private GalacticPictures galacticPictures;

    public Comment(String content, User user, GalacticPictures galacticPictures) {
        this.content = content;
        this.user = user;
        this.galacticPictures = galacticPictures;
    }
}
