/**
 *Initializes the game play
 *
 * @author	Daksh Shah & Arsh Verma
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Following Name Conventions:
 * 1. private field variables start with _, followed by lowercase letter
 * 2. static variables start with a lowercase letter
 * 3. constants (final) variables are CAPITAL
 */
public class Main extends Application {
    //public static
    public static Random random = new Random();
    public static int prevScore=0;//might want to move to User class

    //private static Variables
    private static Stage mainStage;

    /**
     * Function to start the Game, it is called from HomeCtrl.java
     * @param primaryStage
     * @throws IOException
     */
	public void play(Stage primaryStage) throws IOException {
	    GamePlay gamePlay = new GamePlay();
		gamePlay.setUpGame(primaryStage);
	}

	public void play(Stage primaryStage, int snakeLength, int score, double life){

    }

    /**
     * Indirectly calls the start() method
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * First Function that is called automatically at the very beginning of the Application
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
		addMusic(); // Does not seem to work on Mac
        LeadersBoard.loadData();//Populate the Leaders Board

        primaryStage.setTitle("SnakeVsBlock");

        //We start the Game-Application by opening the Login Page
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInPage.fxml"));
		Parent root = loader.load();
		Group HomeGroup = new Group();
		HomeGroup.getChildren().add(root);

        //@Arsh the next two lines might not be needed TODO
		Scene scene = new Scene(HomeGroup);
		primaryStage.setScene(scene);

		primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void addMusic() {
		String musicFile = "./src/sound.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}

}


