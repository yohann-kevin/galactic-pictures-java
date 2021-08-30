package domain.pictures;

import java.util.UUID;

public class GalacticPictures {
    private UUID id;
    private String date;
    private String description;
    private String title;
    private String mediaType;
    private String hdurl;
    private String url;
    private int toLike;
    private int download;

    public GalacticPictures(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "GalacticPictures{" +
                "title='" + title + '\'' +
                '}';
    }
}
