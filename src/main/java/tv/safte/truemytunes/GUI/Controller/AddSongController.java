package tv.safte.truemytunes.GUI.Controller;
// Java imports
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

        // Handle save action
        String title = titleField.getText();
        String artist = artistField.getText();
        String category = categoryField.getText();
        String time = timeField.getText();
        String filePath = filePathField.getText();

        // Save the song details including the file path


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
}