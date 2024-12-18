
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
        if (!isValidFilePath(filePathField.getText())) {
            showAlert("Invalid File Path", "The file path is not valid. Please enter a valid file path.");
            return;
        }

        // Retrieve data from text fields
        String title = titleField.getText();
        String artist = artistField.getText();
        String category = categoryField.getText();
        String time = timeField.getText();
        String filePath = filePathField.getText();

        // Create a new Song object
        Song song = new Song(title, artist, category, time, filePath);

        // Insert the song into the PlayList table in the SQL database
        try {
            songDAO.createSong(song);
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