package tv.safte.truemytunes.GUI.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddPlaylistController {

    @FXML
    private TextField playlistIdField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private void onSave() {
        // Handle save action
        String playlistId = playlistIdField.getText();
        String name = nameField.getText();
        String description = descriptionField.getText();
        System.out.println("Playlist saved: " + name + " with ID " + playlistId);
    }

    @FXML
    private void onCancel() {
        // Handle cancel action
        System.out.println("Action cancelled");
    }
}