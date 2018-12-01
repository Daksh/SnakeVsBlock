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
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeCtrl {
    private Button leaderboardbtn;
    private Button settingsbtn;
    private Button Exitbtn;
    private Button resumebtn;
    private Button playGamebtn;

    @FXML
    public void openLeaderboard() throws IOException {
        Stage stage = (Stage) leaderboardbtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Leaderboard.fxml"));

        stage.setScene(new Scene(root));
    }

    @FXML
    public void playGame() throws IOException {
        Stage stage = (Stage) playGamebtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("PlayDisp.fxml"));

        stage.setScene(new Scene(root));
    }
    @FXML
    public void resumeGame() throws IOException {
        Stage stage = (Stage) resumebtn.getScene().getWindow();
        //Parent root = FXMLLoader.load(getClass().getResource("PlayDisp.fxml"));

        //stage.setScene(new Scene(root));
    }
    @FXML
    public void exitGame() throws IOException {
        Stage stage = (Stage) Exitbtn.getScene().getWindow();
        //Parent root = FXMLLoader.load(getClass().getResource("PlayDisp.fxml"));

        //stage.setScene(new Scene(root));
    }

    @FXML
    public void settingsGame() throws IOException {
        Stage stage = (Stage) Exitbtn.getScene().getWindow();
        //Parent root = FXMLLoader.load(getClass().getResource("PlayDisp.fxml"));

        //stage.setScene(new Scene(root));
    }
}

