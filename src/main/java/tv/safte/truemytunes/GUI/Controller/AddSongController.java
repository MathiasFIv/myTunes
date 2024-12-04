package tv.safte.truemytunes.GUI.Controller;
// Java imports
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        System.out.println("Action cancelled");
    }
}