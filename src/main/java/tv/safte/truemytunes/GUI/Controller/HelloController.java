package tv.safte.truemytunes.GUI.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private Button playBtn, pauseBtn, stopBtn, allSongsAddBtn, allSongsEditBtn, allSongsDeleteBtn;
    @FXML
    private Button playListNewBtn, playListEditBtn, playListDeleteBtn, songPlayListUpBtn, songPlayListDownBtn, songPlayListDeleteBtn, addToPlaylistBtn;
    @FXML
    private Slider volumeSlider;
    @FXML
    private TextField filterSearchBar;
    @FXML
    private TableView<?> allSongsTable, playlistsTable, songsOnPlaylistTable;
    @FXML
    private Label welcomeText;



    @FXML
    private void onAdd() {
        // Handle add song action
    }

    @FXML
    private void onEditSong() {
        // Handle edit song action
    }

    @FXML
    private void onDelete() {
        // Handle delete song action
    }

    @FXML
    private void onNew() {
        // Handle new playlist action
    }

    @FXML
    private void onEditPlaylist() {
        // Handle edit playlist action
    }

    @FXML
    private void onDeletePlaylist() {
        // Handle delete playlist action
    }

    @FXML
    private void onMoveUp() {
        // Handle move song up in playlist action
    }

    @FXML
    private void onMoveDown() {
        // Handle move song down in playlist action
    }

    @FXML
    private void onSongInPlaylistDelete() {
        // Handle delete song from playlist action
    }

    @FXML
    private void onAddToPlaylist() {
        // Handle add song to playlist action
    }
}