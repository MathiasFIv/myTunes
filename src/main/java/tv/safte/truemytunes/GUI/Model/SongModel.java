package tv.safte.truemytunes.GUI.Model;
// Java imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
// Project imports
import tv.safte.truemytunes.BE.Song;
import tv.safte.truemytunes.BLL.SongManager;

import java.util.List;

public class SongModel {

    private ObservableList<Song> songsToBeHeard;

    private SongManager songManager;

    public SongModel() throws Exception
    {
        // Initialiserer BLL
      songManager = new SongManager();
      // Laver tom observable list
      songsToBeHeard = FXCollections.observableArrayList();
      // Henter film fra datakilden og udfylder observable list
      songsToBeHeard.addAll(songManager.getAllSongs());
    }

    // Giver adgang til observable list over film
    public ObservableList<Song> getObservableSongs()
    {
        return songsToBeHeard;
    }

    public void searchSongs(String query) throws Exception
    {
        // Filtrerer sange og opdaterer observable listen med søgeresultater
        List<Song> searchResults = songManager.searchSongs(query);
        songsToBeHeard.clear();
        songsToBeHeard.addAll(searchResults);
    }

    public Song createSong(Song song) throws Exception
    {
        // Krearer ny sang i datakilde og tilføøjer ny sang til obserable list
        Song songCreated = songManager.createSong(song);
        songsToBeHeard.add(songCreated);
        return songCreated;
    }

    public void updateSong(Song updatedSong) throws Exception
    {
        // Updater sanngen i DAL laget
        songManager.updateSong(updatedSong);

        // Updater observable list
        Song s = songsToBeHeard.get(songsToBeHeard.indexOf(updatedSong));
        s.setTitle(updatedSong.getTitle());
        s.setArtist(updatedSong.getArtist());
        s.setCategory(updatedSong.getCategory());
        s.setDuration(updatedSong.getDuration());

    }

    public void deleteSong(Song deletedSong) throws Exception
    {
        // Sletter sangen i DAL laget
        songManager.deleteSong(deletedSong);

        // Sletter fra observable list
        songsToBeHeard.remove(deletedSong);
    }



}
