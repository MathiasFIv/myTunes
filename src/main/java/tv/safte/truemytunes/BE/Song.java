package tv.safte.truemytunes.BE;

public class Song {
    private int id;
    private String title;
    private String artist;
    private String category;
    private String duration;
    private String sPath; //Song path
    private String cPath; //Cover path

    public Song(int id, String title, String artist, String category, String duration, String sPath, String cPath) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.duration = duration;
        this.sPath = sPath;
        this.cPath = cPath;
    }

    private int getId() {
        return id;
    }

    private String getTitle() {
        return title;
    }

    private String getArtist() {
        return artist;
    }

    private String getCategory() {
        return category;
    }

    private String getDuration() {
        return duration;
    }

    private String getsPath() {
        return sPath;
    }

    private String getcPath() {
        return cPath;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setArtist(String artist) {
        this.artist = artist;
    }

    private void setCategory(String category) {
        this.category = category;
    }

    private void setDuration(String duration) {
        this.duration = duration;
    }

    private void setsPath(String sPath) {
        this.sPath = sPath;
    }

    private void setcPath(String cPath) {
        this.cPath = cPath;
    }

    @Override
    public String toString() {
        return id + ", " + title + ", " + artist + ", " + category + ", " + duration + ", " + sPath + ", " + cPath;
    }
}
