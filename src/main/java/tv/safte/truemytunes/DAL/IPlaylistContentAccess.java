package tv.safte.truemytunes.DAL;

// project imports
import tv.safte.truemytunes.BE.PlayList;
import tv.safte.truemytunes.BE.PlaylistContent;
import tv.safte.truemytunes.BE.Song;

//java imports
import java.util.List;

public interface IPlaylistContentAccess {

    List<PlaylistContent> getAllPlaylistContent() throws Exception;

    PlaylistContent createPlaylistContent(PlaylistContent newPlaylistContent) throws Exception;

    void updatePlaylistContent(PlaylistContent playlistContent) throws Exception;

    void deletePlaylistContent(PlaylistContent playlistContent) throws Exception;
}
