package domain.pictures;

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

    public GalacticPictures(UUID id, String title, String description, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
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

    public UUID getId() {
        return id;
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
