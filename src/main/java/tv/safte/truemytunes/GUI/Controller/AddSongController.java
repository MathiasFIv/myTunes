package tv.safte.truemytunes.GUI.Controller;
// Java imports
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tv.safte.truemytunes.BE.Song;

import java.io.*;

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
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private void onSave() {
        // Handle save action
        String title = titleField.getText();
        String artist = artistField.getText();
        String category = categoryField.getText();
        String time = timeField.getText();
        System.out.println("Song saved: " + title + " by " + artist);
    }

    @FXML
    private void onCancel() {
        // Handle cancel action
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        System.out.println("Action cancelled");
    }

    public void setSongData(Song song) {
        // Set the song data to the fields
        // Assuming song is an instance of a Song class with appropriate getters
        titleField.setText(song.getTitle());
        artistField.setText(song.getArtist());
        categoryField.setText(song.getCategory());
        timeField.setText(song.getDuration());
    }
}