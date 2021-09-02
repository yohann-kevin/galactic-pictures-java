package com.kirua.galactic.domain.pictures;

import java.util.UUID;

public class GalacticPictures {
    private UUID id;
    private String date;
    private String description;
    private String title;
    private String mediaType;
    private String copyright;
    private String hdurl;
    private String url;
    private int toLike;
    private int download;

    public GalacticPictures(UUID id, String title, String description, String date, String url, String hdurl, String copyright, String mediaType) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.url = url;
        this.hdurl = hdurl;
        this.copyright = copyright;
        this.mediaType = mediaType;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public UUID getId() { return id; }

    public String getCopyright() { return copyright; }

    public String getHdurl() { return hdurl; }

    public String getUrl() { return url; }

    public String getMediaType() { return mediaType; }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "GalacticPictures{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
