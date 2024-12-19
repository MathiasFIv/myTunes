package tv.safte.truemytunes.DAL.DB;

import tv.safte.truemytunes.BE.PlayList;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayListDAO_DB {

    public PlayList getPlayListById(int id) throws Exception, IOException {
        DBConnector dbConnector = new DBConnector();
        PlayList playList = null;

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT Playlist_id, PlaylistTitle, Creator FROM Playlists WHERE Playlist_id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int Playlist_id = rs.getInt("Playlist_id");
                String PlaylistTitle = rs.getString("PlaylistTitle");
                String Creator = rs.getString("Creator");
                playList = new PlayList(Playlist_id, PlaylistTitle, Creator);
            }
            return playList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error getting playlist", ex);
        }
    }

    public PlayList createPlaylist(PlayList newplaylist) throws Exception {
        DBConnector dbConnector = new DBConnector();
        String sql = "INSERT INTO Playlists(PlaylistTitle, Creator) VALUES (?, ?)";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, newplaylist.getPlayListName());
            stmt.setString(2, newplaylist.getCreator());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            return new PlayList(id, newplaylist.getPlayListName(), newplaylist.getCreator());
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error creating playlist", ex);
        }
    }

    public PlayList updatePlaylist(PlayList playlist) throws Exception {
        DBConnector dbConnector = new DBConnector();
        String sql = "UPDATE Playlists SET PlaylistTitle = ?, Creator = ? WHERE Playlist_id = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, playlist.getPlayListName());
            stmt.setString(2, playlist.getCreator());
            stmt.setInt(3, playlist.getId());
            stmt.executeUpdate();
            return playlist;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error updating playlist", ex);
        }
    }

    public void deletePlayList(PlayList playlist) throws Exception {
        DBConnector dbConnector = new DBConnector();
        String sql = "DELETE FROM Playlists WHERE Playlist_id = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playlist.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error deleting playlist", ex);
        }
    }
}