package tv.safte.truemytunes.GUI.Controller;
// Java imports
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tv.safte.truemytunes.BE.PlayList;
import tv.safte.truemytunes.DAL.DB.PlayListDAO_DB;

public class AddPlaylistController {

    @FXML
    private TextField playlistIdField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField creatorField;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    private Stage dialogStage;
    private boolean saveClicked = false;
    @FXML
    private TextField CreatorField;

    @FXML
private void onSave() {
    // Retrieve data from text fields
    String name = nameField.getText();
    String creator = creatorField.getText(); // Correctly use creatorField

    // Create a new PlayList object
    PlayList playlist = new PlayList(0, name, creator);

    // Insert the playlist into the database
    try {
        PlayListDAO_DB playlistDAO = new PlayListDAO_DB();
        playlistDAO.createPlaylist(playlist);
        System.out.println("Playlist saved to database successfully.");
    } catch (Exception e) {
        e.printStackTrace();
        showAlert("Database Error", "An error occurred while saving the playlist to the database.");
        return;
    }

    saveClicked = true;
    dialogStage.close();
}

private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    @FXML
    private void onCancel() {
        // Handle cancel action
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    public void setPlaylistData(PlayList playlist) {
        // Set the playlist data to the fields
        playlistIdField.setText(String.valueOf(playlist.getId()));
        nameField.setText(playlist.getPlayListName());
        creatorField.setText(playlist.getCreator());
    }
}





