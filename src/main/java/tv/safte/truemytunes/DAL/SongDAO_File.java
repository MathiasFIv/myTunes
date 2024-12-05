package tv.safte.truemytunes.DAL;
//Project Imports
import tv.safte.truemytunes.BE.Song;
//Java Imports
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;

public class SongDAO_File {

    private static final String Song_Files = "data/Songname.txt";
    private Path songPath = Paths.get(Song_Files);


    public List<Song> getAllSongs() throws IOException {

        //laver en string som læser alle linjer i filen
        List<String> lines = Files.readAllLines(songPath);
        List<Song> songs = new ArrayList<>();

        for (String line : lines) {
            String[] separatedLine = line.split("-");


            int id = Integer.parseInt(separatedLine[0]);
            String title = separatedLine[1];
            String artist = separatedLine[2];
            String category = separatedLine[3];
            String duration = separatedLine[4];
            String spath = separatedLine[5];
            String cpath = separatedLine[6];

            if (separatedLine.length > 7) {
                for (int i = 7; i < separatedLine.length; i++) {
                    cpath += "-" + separatedLine[i];
                }
                Song song = new Song(id, title, artist, category, duration, spath, cpath);
                songs.add(song);
            }

        }
        return songs;
    }

    public Song createSong (Song newSong) throws Exception
    {
        List<String> songs = Files.readAllLines(songPath);

        if(songs.size() > 0){
            // get next ID
            String[] separatedLine = songs.get(songs.size() - 1).split("-");
            int nextId = Integer.parseInt(separatedLine[0]) + 1;
            String newSongLine = nextId + "-" + newSong.getTitle() + "-" + newSong.getArtist() + "-" +
                    newSong.getCategory() + "-" + newSong.getDuration() + "-" + newSong.getsPath() + "-" +
                    newSong.getcPath();
            Files.write(songPath, (newSongLine + "\r\n").getBytes(), APPEND);

            return new Song(nextId, newSong.getTitle(), newSong.getArtist(), newSong.getCategory(),
                    newSong.getDuration(), newSong.getsPath(), newSong.getcPath());
        }
        return null;
    }

    public void updateSong (Song song) throws Exception {
        List<String> lines = Files.readAllLines(songPath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines)
        {
            String[] separatedLine = line.split("-");
            int id = Integer.parseInt(separatedLine[0]);

            if (id == song.getId())
            {
                line = song.getId() + "-" + song.getTitle() + "-" + song.getArtist() + "-" + song.getCategory() +
                        "-" + song.getDuration() + "-" + song.getsPath() + "-" + song.getcPath();
            }
            updatedLines.add(line);
        }
        Files.write(songPath, updatedLines);
    }

    public void deleteSong (Song song) throws Exception {
        List<String> lines = Files.readAllLines(songPath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines)
        {
            String[] separatedLine = line.split("-");
            int id = Integer.parseInt(separatedLine[0]);

            if (id != song.getId())
            {
                updatedLines.add(line);
            }
        }
        Files.write(songPath, updatedLines);
    }
}




