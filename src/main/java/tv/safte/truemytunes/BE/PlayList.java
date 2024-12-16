package tv.safte.truemytunes.BE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tv.safte.truemytunes.DAL.DB.DBConnector;

public class PlayList {
    private int id;
    private String playListName;
    private String creator;

    public PlayList(int id, String playListName, String creator) {
        this.id = id;
        this.playListName = playListName;
        this.creator = creator;
    }

    public int getId()
    {
        return id;
    }

    private void setId(int id)
    {
        this.id = id;
    }

    public String getPlayListName() {
        return playListName;
    }

    private void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public String getCreator() {
        return creator;
    }

    private void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return id + ", " + playListName + ", " + creator;
    }

    public void addSong(Song selectedSong) {
        String sql = "INSERT INTO PlaylistSongs (playlistId, songId) VALUES (?, ?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, this.id);
            pstmt.setInt(2, selectedSong.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Song> getSongs() {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT s.id, s.title, s.artist FROM Songs s " +
                "JOIN PlaylistSongs ps ON s.id = ps.songId " +
                "WHERE ps.playlistId = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, this.id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String category = rs.getString("category");
                String duration = rs.getString("duration");
                String sPath = rs.getString("sPath");

                songs.add(new Song(id, title, artist, category, duration, sPath));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }
}