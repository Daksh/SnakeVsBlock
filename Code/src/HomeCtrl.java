/**
 * Serves as controller class for HomeScreen FXML file.
 *
 * @author Daksh Shah and Arsh Verma
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeCtrl {
    public Button leaderboardbtn;
    public Button settingsbtn;
    public Button Exitbtn;
    public Button resumebtn;
    public Button playGamebtn;
    public Label Scorelabel = new Label("Previous Score: "+ Integer.toString(0));

    /*public HomeCtrl() {
    	if (Game.isResumable) {
    		resumebtn.setVisible(true);
		}
		else{
			resumebtn.setVisible(false);
		}
	}*/

	/**
	 * Loads Leaderboard elements on stage when Leaderboard Button is clicked by user.
	 * @throws IOException
	 */
	@FXML
    public void openLeaderboard() throws IOException {
        Stage stage = (Stage) leaderboardbtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Leaderboard.fxml"));

        stage.setScene(new Scene(root));
    }

	/**
	 * Loads Game play elements and starts game when play Button is clicked by user.
	 * @throws IOException
	 */
    @FXML
    public void playGame() throws IOException {
        Stage stage = (Stage) playGamebtn.getScene().getWindow();
        Game newGame =new Game();

        newGame.Play(stage);
    }

	/**
	 * Loads game play elements from previously saved state when resume Button is clicked by user.
	 * @throws IOException
	 */
    @FXML
    public void resumeGame() throws IOException {
        Stage stage = (Stage) resumebtn.getScene().getWindow();
        //Parent root = FXMLLoader.load(getClass().getResource("PlayDisp.fxml"));

        //stage.setScene(new Scene(root));
    }

	/**
	 * Exits the game and closes when when Exit button is clicked by user.
	 * @throws IOException
	 */
    @FXML
    public void exitGame() throws IOException {
        Stage stage = (Stage) Exitbtn.getScene().getWindow();
        System.exit(1);
    }

	/**
	 * Loads Settings page on stage when Settings Button is clicked by user.
	 * TODO: Put correct path of settings FXML page.
	 * @throws IOException
	 */
    @FXML
    public void openSettings() throws IOException {
        Stage stage = (Stage) Exitbtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        stage.setScene(new Scene(root));
    }
	@FXML
	public void openHomeScreen(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		stage.setScene(new Scene(root));
	}

	@FXML
	public void updatePrevBest() {
    	Scorelabel.setText("Previous Score: "+ Integer.toString(10));
	}

}

