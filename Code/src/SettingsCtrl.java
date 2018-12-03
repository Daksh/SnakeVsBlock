import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Serves as controller class for Settings FXML file.
 *
 * @author Daksh Shah and Arsh Verma
 */
public class SettingsCtrl {
	public Button homebtn;
	public Button yellowbtn;
	public Button redbtn;
	public Button greenbtn;
	public Button bluebtn;

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

	@FXML
	public void handlegreenBtnClick() throws IOException {
		Snake.colour = 0;
	}
	@FXML
	public void handleblueBtnClick() throws IOException {
		Snake.colour = 1;
	}
	@FXML
	public void handleyellowBtnClick() throws IOException {
		Snake.colour = 2;
	}

	@FXML
	public void handleredBtnClick() throws IOException {
		Snake.colour = 3;
	}

	@FXML
	public void handleGreenBGClick() throws IOException {
		Main._gamePlay.sceneCol = 2;
	}

	@FXML
	public void handleBlackBGClick() throws IOException {
		Main._gamePlay.sceneCol = 0;
	}

	@FXML
	public void handleBlueBGClick() throws IOException {
		Main._gamePlay.sceneCol = 1;
	}

	@FXML
	public void handleYellowBGClick() throws IOException {
		Main._gamePlay.sceneCol = 3;
	}


}
