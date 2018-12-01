/**
 * Serves as controller class for Leaderboard FXML file.
 *
 * @author Daksh Shah and Arsh Verma
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LeadCtrl {
    private Button homebtn;

	/**
	 * Loads Home Screen elements on stage when Home Button is clicked by user.
	 * @throws IOException
	 */
	@FXML
    public void handleHomeBtnClick() throws IOException {
        Stage stage = (Stage) homebtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));

        stage.setScene(new Scene(root));
    }
}

