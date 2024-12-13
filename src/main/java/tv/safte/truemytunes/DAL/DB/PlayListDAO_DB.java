package tv.safte.truemytunes.DAL.DB;
// Projects import
import tv.safte.truemytunes.BE.PlayList;
// Java imports
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayListDAO_DB {

    @Override
    public List<PlayList> getAllPlayLists() throws Exception, IOException {
        DBConnector dbConnector = new DBConnector();
        List<PlayList> playLists = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT Playlist_id, PlaylistTitle, Creator FROM Playlists";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int Playlist_id = rs.getInt("Playlist_id");
                int PlaylistTitle = rs.getInt("PlaylistTitle");
                int Creator = rs.getInt("Creator");
            }
            return playLists;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error getting playlists", ex);
        }

    }

    @Override
    public PlayList createPlaylist(PlayList newplaylist) throws Exception {
        DBConnector dbConnector = new DBConnector();
        String sql = "INSERT INTO Playlists(Playlist_id, PlaylistTitle, Creator) VALUES (?,?,?)";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newplaylist.getId());
            stmt.setString(2, newplaylist.getPlayListName());
            stmt.setString(3, newplaylist.getCreator());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            PlayList createdPlaylist = new PlayList(id, newplaylist.getPlayListName(), newplaylist.getCreator());
            return createdPlaylist;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error creating playlist", ex);
        }
    }

    @Override
    public void updatePlaylist(PlayList playlist) throws Exception {
        DBConnector dbConnector = new DBConnector();
        String sql = "UPDATE Playlists SET PlaylistTitle = ?, Creator = ? WHERE Playlist_id = ?";

        try (Connection conn = dbConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, playlist.getId());
            stmt.setString(2, playlist.getPlayListName());
            stmt.setString(3, playlist.getCreator());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error updating playlist", ex);
        }

    }


    public void deletePlayList(PlayList playlist) throws Exception {
        DBConnector dbConnector = new DBConnector();
        String sql = "DELETE FROM Playlists WHERE Playlist_id = ?";

        try (Connection conn = dbConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playlist.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error deleting playlist", ex);
        }
    }
}