package tv.safte.truemytunes.BLL;
// Project imports
import tv.safte.truemytunes.BE.Song;
import tv.safte.truemytunes.BLL.util.SongSearcher;
import tv.safte.truemytunes.DAL.DB.SongDAO_DB;
import tv.safte.truemytunes.DAL.ISongDataAccess;
// Java imports
import java.util.List;

public class SongManager {

    private SongSearcher songSearcher = new SongSearcher();
    private ISongDataAccess songDAO;

    // Initialiserer SongDAO_DB klassen og implementerer ISongDataAccess interface
    public SongManager()
    {
        songDAO = new SongDAO_DB();
    }

    // Henter alle sange fra datakilden via SongDAO objektet
    public List<Song> getAllSongs() throws Exception {
        return songDAO.getAllSongs();
    }

    // Henter alle sange fra datakilden og filtrerer sange via SongSearcher
    public List<Song> searchSongs(String query) throws Exception {
        List<Song> allSongs = getAllSongs();
        List<Song> searchResult = songSearcher.search(allSongs, query);
        return searchResult;
    }

    public Song createSong(Song newSong) throws Exception {
        return songDAO.createSong(newSong);
    }

    public Song updateSong(Song updatedSong) throws Exception {
        songDAO.updateSong(updatedSong);
        return updatedSong;
    }

    public void deleteSong(Song deletedSong) throws Exception {
        songDAO.deleteSong(deletedSong);
    }


}