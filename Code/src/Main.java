/**
 *Initializes the game play
 *
 * @author	Daksh Shah & Arsh Verma
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.Random;

/**
 * Following Name Conventions:
 * 1. private field variables start with _, followed by lowercase letter
 * 2. static variables start with a lowercase letter
 * 3. constants (final) variables are CAPITAL
 */
public class Main extends Application {
    /**
     * Constants
     *
     * WIDTH, BLOCK_HEIGHT : state of the blocks to be spawned.
     * BLOCK_SPEED : Defines the speed of downward transition of the block.
     */
    public static final int BLOCK_WIDTH = 98, BLOCK_HEIGHT = 100, SNAKE_RADIUS = 15;
    public static final String fPathDS = "dataStore.txt";

    public static double SPEED = 3;

    // We need to have it, for ex. AnimationTimers need to be added to the GamePlay's list of Timers
    // _gamePlay.increaseScore, over,
    public static GamePlay _gamePlay;

    public static Random random = new Random();
    public static int prevScore=0;//might want to move to User class

    /**
     * Function to start the Game, it is called from HomeCtrl.java
     * @param primaryStage
     * @throws IOException
     */
	public void play(Stage primaryStage) throws IOException {
	    if(new File(fPathDS).isFile()){
            DataStore ds = null;
            try {
                ds = DataStore.deserialize();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            _gamePlay = new GamePlay(primaryStage, ds._score, ds._life);
            _gamePlay.startGame(primaryStage, ds._snakeLength);
            Main.SPEED = ds._speed;
            Snake.colour = ds._snakeColor;
            Main._gamePlay.sceneCol = ds._sceneCol;
        } else{
            _gamePlay = new GamePlay(primaryStage, 0, 0f);
            _gamePlay.startGame(primaryStage, 20);
        }
	}

	public void playNew(Stage primaryStage) throws IOException{
        _gamePlay = new GamePlay(primaryStage, 0, 0f);
        _gamePlay.startGame(primaryStage, 20);
    }

	public void play(Stage primaryStage, int snakeLength, int score, double life){
        _gamePlay = new GamePlay(primaryStage, score, life);
        _gamePlay.startGame(primaryStage, snakeLength);
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

	public static void removeGameRef(){
        Main.SPEED = 3;
        try {
            DataStore.serialize(new DataStore(20,0,3,0,0,0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Snake.setNull();
        _gamePlay = null;
    }

    public void restartGame(Stage primaryStage){
        Main._gamePlay.over();
        try {
            playNew(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


