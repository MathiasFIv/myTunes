package tv.safte.truemytunes.GUI.Controller;

//Java Imports
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import tv.safte.truemytunes.BE.PlayList;
import tv.safte.truemytunes.BE.Song;
import tv.safte.truemytunes.BLL.SongManager;

import java.net.URL;


public class myTunesController {


    //buttons
    @FXML
    private Button playBtn, pauseBtn, stopBtn, allSongsAddBtn, allSongsEditBtn, allSongsDeleteBtn;
    @FXML
    private Button playListNewBtn, playListEditBtn, playListDeleteBtn, songPlayListUpBtn, songPlayListDownBtn, songPlayListDeleteBtn, addToPlaylistBtn;

    //Slider
    @FXML
    private Slider volumeSlider;
    @FXML
    private TextField filterSearchBar;
    @FXML
    private TableView<Song> allSongsTable;

    @FXML
    private TableView<PlayList> playlistsTable;

    @FXML
    private TableView<Song> songsOnPlaylistTable;

    @FXML
    private TableColumn<Song, String> colTitle, colArtist, colCategory, colTime,allSongsDurationCol;

    private MediaPlayer mediaPlayer;
    private FilteredList<Song> filteredAllSongs, filteredSongsOnPlaylist;
    private FilteredList<PlayList>  filteredPlaylists;

    private SongManager songManager;


    public void initialize() {

        songManager = new SongManager();
        loadAllSongs();

        // Initialize columns
        colTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        colArtist.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArtist()));
        colCategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
        colTime.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDuration()));





        // Set play button action
        playBtn.setOnAction(event -> mediaPlayer.play());

        // Set pause button action
        pauseBtn.setOnAction(event -> mediaPlayer.pause());

        // Set stop button action
        stopBtn.setOnAction(event -> mediaPlayer.stop());

        // Set allSongsAddBtn action
        allSongsAddBtn.setOnAction(event -> onAdd());

        // Set allSongsEditBtn action
        allSongsEditBtn.setOnAction(event -> onEditSong());

        // Set allSongsDeleteBtn action
        allSongsDeleteBtn.setOnAction(event -> onDelete());

        // Set playListNewBtn action
        playListNewBtn.setOnAction(event -> onNew());

        // Set playListEditBtn action
        playListEditBtn.setOnAction(event -> onEditPlaylist());

        // Set playListDeleteBtn action
        playListDeleteBtn.setOnAction(event -> onDeletePlaylist());

        // Set songPlayListUpBtn action
        songPlayListUpBtn.setOnAction(event -> onMoveUp());

        // Set songPlayListDownBtn action
        songPlayListDownBtn.setOnAction(event -> onMoveDown());

        // Set songPlayListDeleteBtn action
        songPlayListDeleteBtn.setOnAction(event -> onSongInPlaylistDelete());





        // Initialize filtered lists
        filteredAllSongs = new FilteredList<>(FXCollections.observableArrayList(allSongsTable.getItems()), p -> true);
        filteredPlaylists = new FilteredList<>(FXCollections.observableArrayList(playlistsTable.getItems()), p -> true);
        filteredSongsOnPlaylist = new FilteredList<>(FXCollections.observableArrayList(songsOnPlaylistTable.getItems()), p -> true);

        // Set filtered lists to tables
        allSongsTable.setItems(filteredAllSongs);
        playlistsTable.setItems(filteredPlaylists);
        songsOnPlaylistTable.setItems(filteredSongsOnPlaylist);

        // Add listener for filterSearchBar
        filterSearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredAllSongs.setPredicate(item -> {
                // Implement your filtering logic here
                return item.toString().toLowerCase().contains(newValue.toLowerCase());
            });
            filteredPlaylists.setPredicate(item -> {
                // Implement your filtering logic here
                return item.toString().toLowerCase().contains(newValue.toLowerCase());
            });
            filteredSongsOnPlaylist.setPredicate(item -> {
                // Implement your filtering logic here
                return item.toString().toLowerCase().contains(newValue.toLowerCase());
            });
        });





        // Add listeners for TableView item selection
        allSongsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Selected item in allSongsTable: " + newValue);
            }
        });

        playlistsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Selected item in playlistsTable: " + newValue);
            }
        });

        songsOnPlaylistTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Selected item in songsOnPlaylistTable: " + newValue);
            }
        });
    }

    private void loadAllSongs() {
        try {
            ObservableList<Song> allSongs = FXCollections.observableArrayList(songManager.getAllSongs());
            allSongsTable.setItems(allSongs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






@FXML
private void onAdd() {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tv/safte/truemytunes/AddSong.fxml"));
        Parent root = fxmlLoader.load();

        // Get the controller and set the dialog stage
        AddSongController controller = fxmlLoader.getController();
        Stage stage = new Stage();
        controller.setDialogStage(stage);

        stage.setTitle("Add Song");
        stage.setScene(new Scene(root));
        stage.showAndWait();

        // Check if the save button was clicked
        if (controller.isSaveClicked()) {
            // Refresh the allSongsTable with the new song
            loadAllSongs();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @FXML
    private void onEditSong() {
        // Handle edit song action
        // Handle edit song action
        Object selectedSong = allSongsTable.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tv/safte/truemytunes/GUI/View/AddSong.fxml"));
                Parent root = fxmlLoader.load();

                // Get the controller and pass the selected song data
                AddSongController controller = fxmlLoader.getController();
                controller.setSongData((Song) selectedSong);

                Stage stage = new Stage();
                stage.setTitle("Edit Song");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onDelete() {
        // Handle delete song action
        // Handle delete song action
        Object selectedSong = allSongsTable.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            allSongsTable.getItems().remove(selectedSong);
        }
    }

    @FXML
    private void onNew() {
        // Handle new playlist action
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/data/Musikdata.Nummere/1.mp3"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Playlist");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onEditPlaylist() {
        // Handle edit playlist action

        PlayList selectedPlaylist = (PlayList) playlistsTable.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tv/safte/truemytunes/GUI/View/AddPlaylist.fxml"));
                Parent root = fxmlLoader.load();

                // Get the controller and pass the selected playlist data
                AddPlaylistController controller = fxmlLoader.getController();
                controller.setPlaylistData(selectedPlaylist);

                Stage stage = new Stage();
                stage.setTitle("Edit Playlist");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onDeletePlaylist() {
        // Handle delete playlist action
        Object selectedPlaylist = playlistsTable.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null) {
            playlistsTable.getItems().remove(selectedPlaylist);
        }
    }

    @FXML
    private void onMoveUp() {
        // Handle move song up in playlist action
        int selectedIndex = songsOnPlaylistTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex > 0) {
            Song selectedSong = songsOnPlaylistTable.getItems().get(selectedIndex);
            songsOnPlaylistTable.getItems().remove(selectedIndex);
            songsOnPlaylistTable.getItems().add(selectedIndex - 1, selectedSong);
            songsOnPlaylistTable.getSelectionModel().select(selectedIndex - 1);
        }
    }

    @FXML
    private void onMoveDown() {
        // Handle move song down in playlist action
        int selectedIndex = songsOnPlaylistTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex < songsOnPlaylistTable.getItems().size() - 1) {
            Song selectedSong = songsOnPlaylistTable.getItems().get(selectedIndex);
            songsOnPlaylistTable.getItems().remove(selectedIndex);
            songsOnPlaylistTable.getItems().add(selectedIndex + 1, selectedSong);
            songsOnPlaylistTable.getSelectionModel().select(selectedIndex + 1);
        }

    }

    @FXML
    private void onSongInPlaylistDelete() {
        // Handle delete song from playlist action
        Song selectedSong = songsOnPlaylistTable.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            songsOnPlaylistTable.getItems().remove(selectedSong);
        }
    }

    @FXML
    private void onAddToPlaylist() {
        // Handle add song to playlist action
        Song selectedSong = (Song) allSongsTable.getSelectionModel().getSelectedItem();
        PlayList selectedPlaylist = (PlayList) playlistsTable.getSelectionModel().getSelectedItem();
        if (selectedSong != null && selectedPlaylist != null) {
            // Assuming Playlist class has a method to add songs
            selectedPlaylist.addSong(selectedSong);
            // Refresh the songsOnPlaylistTable to show the updated list
            songsOnPlaylistTable.setItems(FXCollections.observableArrayList(selectedPlaylist.getSongs()));
        }
    }
}