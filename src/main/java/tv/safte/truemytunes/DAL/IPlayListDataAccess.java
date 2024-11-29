package tv.safte.truemytunes.DAL;

//Project Imports
import tv.safte.truemytunes.BE.PlayList;

//Java import
import java.util.List;

public interface IPlayListDataAccess {

    List<PlayList> getAllPlayLists() throws Exception;

    PlayList createPlayList(PlayList newplaylist) throws Exception;

    void updatePlayList(PlayList playList) throws Exception;

    void deletePlayList(PlayList playList) throws Exception;

}