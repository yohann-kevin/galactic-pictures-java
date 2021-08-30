package domain.pictures;

public class GalacticPictures {
    private int id;
    private String date;
    private String description;
    private String title;
    private String mediaType;
    private String hdurl;
    private String url;
    private int toLike;
    private int download;

    public GalacticPictures(int id, String date, String description, String title, String mediaType, String hdurl, String url) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.title = title;
        this.mediaType = mediaType;
        this.hdurl = hdurl;
        this.url = url;
    }
}
