package com.kirua.galactic.domain.pictures;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class GalacticPictures {
    @Id
    @Column(length = 36)
    @org.hibernate.annotations.Type(type="uuid-char")
    private UUID id;
    private String date;
    @Column(length = 25000, columnDefinition="LONGTEXT")
    private String description;
    private String title;
    private String mediaType;
    private String copyright;
    private String hdurl;
    private String url;
    private int toLike;
    private int download;

    public GalacticPictures(UUID id, String date, String description, String title, String mediaType, String copyright, String hdurl, String url) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.title = title;
        this.mediaType = mediaType;
        this.copyright = copyright;
        this.hdurl = hdurl;
        this.url = url;
    }
}
