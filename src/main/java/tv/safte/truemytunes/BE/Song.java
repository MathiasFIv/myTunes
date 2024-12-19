package tv.safte.truemytunes.BE;

import javafx.beans.value.ObservableValue;

public class Song {
    private int id;
    private String title;
    private String artist;
    private String category;
    private String duration;
    private String sPath; //Song path
    private String cPath; //Cover path
    private String album;

    public Song(int id, String title, String artist, String category, String duration, String sPath) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.duration = duration;
        this.sPath = sPath;
        //this.cPath = cPath;
    }

    public Song(int id, String title, String artist, String category, String duration, String sPath, String cPath, String album) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.duration = duration;
        this.sPath = sPath;
        this.cPath = cPath;
        this.album = album;
    }

    public Song(int id, String title, String artist, String category, String duration) {
    }

    public Song(String title, String artist, String category, String time, String filePath) {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getCategory() {
        return category;
    }

    public String getDuration() {
        return duration;
    }

    public String getsPath() {
        return sPath;
    }

    public String getcPath() {
        return cPath;
    }

    public String getAlbum() {
        return album;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setsPath(String sPath) {
        this.sPath = sPath;
    }

    public void setcPath(String cPath) {
        this.cPath = cPath;
    }

    @Override
    public String toString() {
        return id + ", " + title + ", " + artist + ", " + category + ", " + duration + ", " + sPath;
    }


}
