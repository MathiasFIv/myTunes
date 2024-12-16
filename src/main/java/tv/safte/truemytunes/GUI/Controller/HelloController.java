package tv.safte.truemytunes.GUI.Controller;

//Java Imports
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


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
    private TableView<Object> allSongsTable, playlistsTable, songsOnPlaylistTable;
    @FXML
    private Label welcomeText;

    private MediaPlayer mediaPlayer;
    private FilteredList<Object> filteredAllSongs, filteredPlaylists, filteredSongsOnPlaylist;

    public void initialize() {
        // Initialize MediaPlayer with a sample media file
        Media media = new Media(getClass().getResource("/path/to/your/media/file.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(media);

        // Bind the volume slider to the media player's volume property
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            mediaPlayer.setVolume(newValue.doubleValue() / 100.0);
        });

        // Set play button action
        playBtn.setOnAction(event -> playSelectedSong());

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



    private void playSelectedSong() {
        Object selectedSong = allSongsTable.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            String filePath = songService.getFilePath(selectedSong);
            if (filePath != null) {
                Media media = new Media(getClass().getResource(filePath).toExternalForm());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            }
        }
    }






    @FXML
    private void onAdd() {
        // Handle add song action
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tv/safte/truemytunes/GUI/View/AddSong.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Song");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onEditSong() {

        // Handle edit song action
        Object selectedSong = allSongsTable.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tv/safte/truemytunes/GUI/View/AddSong.fxml"));
                Parent root = fxmlLoader.load();

                // Get the controller and pass the selected song data
                AddSongController controller = fxmlLoader.getController();
                controller.setSongData(selectedSong);

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
        Object selectedSong = allSongsTable.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            allSongsTable.getItems().remove(selectedSong);
        }
    }

    @FXML
    private void onNew() {
        // Handle new playlist action
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tv/safte/truemytunes/GUI/View/AddPlaylist.fxml"));
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

        Object selectedPlaylist = playlistsTable.getSelectionModel().getSelectedItem();
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
            Object selectedSong = songsOnPlaylistTable.getItems().get(selectedIndex);
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
            Object selectedSong = songsOnPlaylistTable.getItems().get(selectedIndex);
            songsOnPlaylistTable.getItems().remove(selectedIndex);
            songsOnPlaylistTable.getItems().add(selectedIndex + 1, selectedSong);
            songsOnPlaylistTable.getSelectionModel().select(selectedIndex + 1);
        }

    }

    @FXML
    private void onSongInPlaylistDelete() {
        // Handle delete song from playlist action
        Object selectedSong = songsOnPlaylistTable.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            songsOnPlaylistTable.getItems().remove(selectedSong);
        }
    }

    @FXML
    private void onAddToPlaylist() {
        // Handle add song to playlist action
        Object selectedSong = allSongsTable.getSelectionModel().getSelectedItem();
        Object selectedPlaylist = playlistsTable.getSelectionModel().getSelectedItem();
        if (selectedSong != null && selectedPlaylist != null) {
            // Assuming Playlist class has a method to add songs
            selectedPlaylist.addSong(selectedSong);
            // Refresh the songsOnPlaylistTable to show the updated list
            songsOnPlaylistTable.setItems(FXCollections.observableArrayList(selectedPlaylist.getSongs()));
        }
    }
}