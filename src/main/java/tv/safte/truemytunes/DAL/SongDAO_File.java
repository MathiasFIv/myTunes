package tv.safte.truemytunes.DAL;

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
            return songs;
        }

        public Song createSong (Song newSong) throws Exception {
            return null;
        }

        public void updateSong (Song song) throws Exception {
            return null;
        }

        public void deleteSong (Song song) throws Exception {
            return null;
        }
    }
    }




