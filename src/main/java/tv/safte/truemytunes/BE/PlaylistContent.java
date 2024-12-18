package tv.safte.truemytunes.BE;

public class PlaylistContent {
    private int s_id;
    private int pl_id;
    private int sos;

    public PlaylistContent(int s_id, int pl_id, int sos) {
        this.s_id = s_id;
        this.pl_id = pl_id;
        this.sos = sos;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public int getPl_id() {
        return pl_id;
    }

    public void setPl_id(int pl_id) {
        this.pl_id = pl_id;
    }

    public int getSos() {
        return sos;
    }

    public void setSos(int sos) {
        this.sos = sos;
    }

    @Override
    public String toString() {
        return s_id + ", " + pl_id + ", " + sos;
    }
}