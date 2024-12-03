package tv.safte.truemytunes.DAL.DB;

import tv.safte.truemytunes.BE.Song;
import tv.safte.truemytunes.DAL.ISongDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO_DB implements ISongDataAccess {

    @Override
    public List<Song> getAllSongs() throws Exception {

        DBConnector dbConnector = new DBConnector();

        ArrayList<Song> allSongs = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection(); Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM schema_name.AllSongs";
            ResultSet rs = stmt.executeQuery(sql);

            // Loop gennem rækker fra database
            while (rs.next()) {
                int id = rs.getInt("id");
                String artist = rs.getString("artist");
                String title = rs.getString("title");
                String category = rs.getString("category");
                String duration = rs.getString("duration");
                String sPath = rs.getString("path");
                String cPath = rs.getString("cpath");

                Song song = new Song(id, artist, title, category, duration, sPath, cPath);
                allSongs.add(song);
            }
            return allSongs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error getting all songs", ex);
        }

    }
    @Override
    public Song createSong(Song newSong) throws Exception {
        String sql = "INSERT INTO schema_name.AllSongs (Title, Artist, Category, Duration) VALUES (?, ?, ?, ?)";
        DBConnector dbConnector = new DBConnector();

        try (Connection conn = dbConnector.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameterne
            stmt.setString(1, newSong.getTitle());
            stmt.setString(2, newSong.getArtist());
            stmt.setString(3, newSong.getCategory());
            stmt.setString(4, newSong.getDuration());

            stmt.executeUpdate();

            // Få genereret ID fra DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            Song createdSong = new Song(id, newSong.getTitle(), newSong.getArtist(), newSong.getCategory(), newSong.getDuration());

            return createdSong;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Error creating new song", ex);
        }
    }

    @Override
    public void updateSong(Song song) throws Exception {

        String sql = "UPDATE schema_name.AllSongs SET Title = ?, Artist = ?, Category = ?, Duration = ? WHERE id = ? ";
        DBConnector dbConnector = new DBConnector();

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameterne
            stmt.setString(1, song.getTitle());
            stmt.setString(2, song.getArtist());
            stmt.setString(3, song.getCategory());
            stmt.setString(4, song.getDuration());
            stmt.setInt(5, song.getId());

            stmt.executeUpdate();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Error updating song", ex);
        }

    }

    @Override
    public void deleteSong(Song song) throws Exception {
        String sql = "DELETE FROM schema_name.AllSongs WHERE id = ?";
        DBConnector dbConnector = new DBConnector();

        try (Connection conn = dbConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameterne
            stmt.setInt(1, song.getId());

            stmt.executeUpdate();
        }
        catch (SQLException ex){
            throw new Exception("Error deleting song", ex);
        }
    }
}
