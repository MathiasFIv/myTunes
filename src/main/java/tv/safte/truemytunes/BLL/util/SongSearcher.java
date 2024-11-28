package tv.safte.truemytunes.BLL.util;

import tv.safte.truemytunes.BE.Song;

import java.util.ArrayList;
import java.util.List;

public class SongSearcher {

    public List<Song> search (List<Song> searchBase, String query) {
        List<Song> searchResult = new ArrayList<>();

        for (Song song : searchBase) {
            if(compareToSongTitle(query, song) || compareToSongArtist(query, song) || compareToSongGenre(query, song) || comparetoSongAlbum(query, song))
            {
                searchResult.add(song);
            }
            {
                searchResult.add(song);
            }
        }
        return searchResult;
    }

    private boolean compareToSongTitle(String query, Song song) {
        return song.getTitle().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToSongArtist(String query, Song song) {
        return song.getArtist().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToSongGenre(String query, Song song) {
        return song.getCategory().toLowerCase().contains(query.toLowerCase());
    }

    private boolean comparetoSongAlbum(String query, Song song) {
        return song.getAlbum().toLowerCase().contains(query.toLowerCase());
    }
}
