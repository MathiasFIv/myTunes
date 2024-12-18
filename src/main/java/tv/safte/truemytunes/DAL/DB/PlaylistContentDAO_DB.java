
package tv.safte.truemytunes.DAL.DB;
// Projects import
import tv.safte.truemytunes.BE.PlaylistContent;
// Java imports
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistContentDAO_DB {



    public List<PlaylistContent> getAllPlaylistContent() throws Exception {
        DBConnector dbConnector = new DBConnector();
        List<PlaylistContent> playlistContents = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM PlaylistContent";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int s_id = rs.getInt("s_id");
                int pl_id = rs.getInt("pl_id");
                int sos = rs.getInt("sos");

                PlaylistContent playlistContent = new PlaylistContent(s_id, pl_id, sos);
                playlistContents.add(playlistContent);
            }
            return playlistContents;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error getting playlist content", ex);
        }
    }



    public PlaylistContent createPlaylistContent(PlaylistContent newPlaylistContent) throws Exception {
        String sql = "INSERT INTO PlaylistContent (s_id, pl_id, sos) VALUES (?, ?, ?)";
        DBConnector dbConnector = new DBConnector();

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, newPlaylistContent.getS_id());
            stmt.setInt(2, newPlaylistContent.getPl_id());
            stmt.setInt(3, newPlaylistContent.getSos());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                newPlaylistContent.setS_id(rs.getInt(1));
            }

            return newPlaylistContent;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error creating playlist content", ex);
        }
    }


    public void updatePlaylistContent(PlaylistContent playlistContent) throws Exception {
        String sql = "UPDATE PlaylistContent SET pl_id = ?, sos = ? WHERE s_id = ?";
        DBConnector dbConnector = new DBConnector();

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playlistContent.getPl_id());
            stmt.setInt(2, playlistContent.getSos());
            stmt.setInt(3, playlistContent.getS_id());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error updating playlist content", ex);
        }
    }

    
    public void deletePlaylistContent(PlaylistContent playlistContent) throws Exception {
        String sql = "DELETE FROM PlaylistContent WHERE s_id = ?";
        DBConnector dbConnector = new DBConnector();

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playlistContent.getS_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error deleting playlist content", ex);
        }
    }
}
