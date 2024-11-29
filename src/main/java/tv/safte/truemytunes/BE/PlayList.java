package tv.safte.truemytunes.BE;

public class PlayList {
    private int id;
    private String playListName;
    private String creator;

    public PlayList(int id, String playListName, String creator) {
        this.id = id;
        this.playListName = playListName;
        this.creator = creator;
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private String getPlayListName() {
        return playListName;
    }

    private void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    private String getCreator() {
        return creator;
    }

    private void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return id + ", " + playListName + ", " + creator;
    }
}