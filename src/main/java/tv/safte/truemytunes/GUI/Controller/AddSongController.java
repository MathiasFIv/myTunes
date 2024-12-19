package tv.safte.truemytunes.GUI.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tv.safte.truemytunes.BE.Song;
import tv.safte.truemytunes.DAL.DB.SongDAO_DB;

import java.io.File;

public class AddSongController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField artistField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField filePathField;

    private Stage dialogStage;
    private boolean saveClicked = false;
    private SongDAO_DB songDAO = new SongDAO_DB();

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
private void onSave() {
    // Validate file path
    String filePath = filePathField.getText();
    if (!isValidFilePath(filePath)) {
        showAlert("Invalid File Path", "The file path is not valid. Please enter a valid file path.");
        return;
    }

    // Retrieve data from text fields
    String title = titleField.getText();
    String artist = artistField.getText();
    String category = categoryField.getText();
    String time = timeField.getText();

    // Log the retrieved data
    System.out.println("Title: " + title);
    System.out.println("Artist: " + artist);
    System.out.println("Category: " + category);
    System.out.println("Time: " + time);
    System.out.println("File Path: " + filePath);

    // Create a new Song object
    Song song = new Song(0, title, artist, category, time, filePath);

    // Log the song object
    System.out.println("Created Song: " + song);

    // Insert the song into the database
    try {
        songDAO.createSong(song);
        System.out.println("Song saved to database successfully.");
    } catch (Exception e) {
        e.printStackTrace();
        showAlert("Database Error", "An error occurred while saving the song to the database.");
        return;
    }

    saveClicked = true;
    dialogStage.close();
}

    @FXML
    private void onCancel() {
        dialogStage.close();
    }

    private boolean isValidFilePath(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setSongData(Song selectedSong) {
        titleField.setText(selectedSong.getTitle());
        artistField.setText(selectedSong.getArtist());
        categoryField.setText(selectedSong.getCategory());
        timeField.setText(selectedSong.getDuration());
        filePathField.setText(selectedSong.getsPath());
    }
}
