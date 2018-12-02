/**
 *Initializes the game play
 *
 * @author	Daksh Shah & Arsh Verma
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static javafx.scene.paint.Color.*;

public class Game extends Application implements Serializable {
    private Snake masterSnake;
    private Blocks testBlocks= new Blocks();
    private Wall testWall = new Wall(); //White lines
    private Tokens testMagnet = new Magnet();
    private BorderPane layout;
    private Random random = new Random();
    private int rInt = random.nextInt(5);

    private static Menu gameMenu3;

    public static int Score = 0;
    public static int prevScore=0;
    public static int sceneCol=0;
    public static boolean isResumable = true;
    public static Scene scene1;
    public static ArrayList<AnimationTimer> ANIMTimers = new ArrayList<AnimationTimer>();

    Game playGame;
    static Stage mainStage;

	protected void setUpGame (Snake masterSnake, Blocks testBlocks, Wall testWall, Tokens testMagnet, Stage primaryStage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayDisp.fxml"));
		Group ballGroup= new Group();
		Parent root = loader.load();
		Group superGroup = new Group();

		Menu gameMenu = new Menu("Game");
		gameMenu.getItems().add(new MenuItem("Restart Game"));
		gameMenu.getItems().add(new MenuItem("Exit Game"));
		Menu gameMenu2 = new Menu("Settings");
		gameMenu2.getItems().add(new MenuItem("Modify settings"));
		Menu gameMenu4 = new Menu ( "								Score: ");
		gameMenu3 = new Menu (Integer.toString(Score));

		MenuBar Bar = new MenuBar();
		Bar.getMenus().addAll(gameMenu,gameMenu2, gameMenu4,gameMenu3);
		Bar.setMinWidth(500.0);

		BorderPane layout = new BorderPane();
		layout.setTop(Bar);
		ballGroup.getChildren().add(Bar);

		superGroup.getChildren().addAll(ballGroup, root);
		Scene scene = new Scene(superGroup, 500,700, BLACK);
		Color[] arr = {BLACK, LIGHTBLUE, LIGHTGREEN, WHITE};
		scene.setFill(arr[sceneCol]);
		mainStage = primaryStage;
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);

		//There is a problem as both of the following need each other :\
		masterSnake = Snake.getInstance(20, scene);
		testBlocks = new Blocks(masterSnake,scene);//needs Snake to know what kind of blocks to spell
		masterSnake.setBlocksRef(testBlocks);
		Tokens.setBlocks(testBlocks);
		Tokens.setSnake(masterSnake);

		BallTokens.setBlocks(testBlocks);
		BallTokens.setSnake(masterSnake);

		Wall.setSnake(masterSnake);

		testMagnet.addToken(scene);
		Tokens testMagnet2 = new Magnet();
		testMagnet2.addToken(scene);
		scene1=scene;
		new Magnet().addToken(scene);

        new TokenBallInher().addToken(scene);
        new TokenBallInher().addToken(scene);
        new TokenBallInher().addToken(scene);
        new TokenBallInher().addToken(scene);

		testWall.addWall(scene);
	}

	public void Play(Stage primaryStage) throws IOException
	{
		this.setUpGame(masterSnake, testBlocks, testWall, testMagnet, primaryStage);
	}

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
		addMusic();
		if (isResumable) {
			//Game.deserializeScore();
			//masterSnake=Game.deserializeSnake();
		}
		//Game.deserializeLeaderboard();
		//Game.deserializeUser();
        primaryStage.setTitle("SnakeVsBlock");
        playGame = new Game();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInPage.fxml"));
		Parent root = loader.load();
		Group HomeGroup = new Group();
		HomeGroup.getChildren().add(root);
		testBlocks.LoadData();
		Scene scene = new Scene(HomeGroup);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
        //playGame.setUpGame(masterSnake, testBlocks, testWall, testMagnet, primaryStage);

        primaryStage.show();
    }

    public void addMusic() {
		String musicFile = "./src/sound.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}

	private static void updateScoreLabel(int score){
	    Game.Score = score;
        Game.gameMenu3.setText(Integer.toString(score));
		try
		{
			serializeScore();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

    public static void increaseScore(int delta){
        Game.Score = Game.Score + delta;
        updateScoreLabel(Game.Score);
    }

    public static int getScore(){
        return Game.Score;
    }


    public static void over() {
        System.out.println("GAME OVER from GAME.java");
        for(int i=0; i<ANIMTimers.size(); i++)
            ANIMTimers.get(i).stop();
        try {
            Thread.sleep(1000);
            Game.prevScore = Game.Score;
            Game.serializeUser();
            Game.serializeLeaderboard();
            Game.isResumable=false;
            //System.exit(1);
			HomeCtrl hm = new HomeCtrl();
			hm.updatePrevBest();
			hm.openHomeScreen(Game.mainStage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e)
		{
			e.printStackTrace();
		}
//        catch (IOException e)
//		{
//			e.printStackTrace();
//		}
        Game.Score = 0;
	}

	public static void serializeSnake(Snake S1) throws IOException {
		ObjectOutputStream out = null;
		try {
			out =new ObjectOutputStream(new FileOutputStream(("snake.txt")));
			out.writeObject(S1.get_length());
		}
		finally	{
			out.close();
		}

	}
	public static Snake deserializeSnake() throws IOException, ClassNotFoundException {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("snake.txt"));
			return Snake.getInstance((Integer) in.readObject(), scene1);
		}
		finally
		{
			in.close();
		}
	}

	public static void serializeLeaderboard() throws IOException {
		ObjectOutputStream out = null;
		try {
			out =new ObjectOutputStream(new FileOutputStream(("Leaderboard.txt")));

			out.writeObject(LeadCtrl.data);
		}
		finally	{
			out.close();
		}

	}
	public static void deserializeLeaderboard() throws IOException, ClassNotFoundException {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("Leaderboard.txt"));
			LeadCtrl.data= (ArrayList<User>) in.readObject();
		}
		finally
		{
			in.close();
		}
	}

	public static void serializeScore() throws IOException {
		ObjectOutputStream out = null;
		try {
			out =new ObjectOutputStream(new FileOutputStream(("Leaderboard.txt")));

			out.writeObject(Game.Score);
		}
		finally	{
			out.close();
		}
		out = null;
		try {
			out =new ObjectOutputStream(new FileOutputStream(("Resumable.txt")));

			out.writeObject(Game.isResumable);
		}
		finally	{
			out.close();
		}

	}
	public static void deserializeScore() throws IOException, ClassNotFoundException {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("Leaderboard.txt"));
			Game.Score = (int) in.readObject();
		}
		finally
		{
			in.close();
		}
		in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("Resumable.txt"));
			Game.isResumable= (boolean) in.readObject();
		}
		finally
		{
			in.close();
		}
	}

	public static void serializeUser() throws IOException {
		ObjectOutputStream out = null;
		try {
			out =new ObjectOutputStream(new FileOutputStream(("UserData.txt")));

			out.writeObject(User.Users);
		}
		finally	{
			out.close();
		}
		out = null;
		try {
			out =new ObjectOutputStream(new FileOutputStream(("UserPass.txt")));

			out.writeObject(User.UserPasswords);
		}
		finally	{
			out.close();
		}

	}
	public static void deserializeUser() throws IOException, ClassNotFoundException {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("UserData.txt"));
			User.Users = (ArrayList<User>) in.readObject();
		}
		finally
		{
			in.close();
		}
		in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("UserPass.txt"));
			User.UserPasswords= (HashMap<String, String>) in.readObject();
		}
		finally
		{
			in.close();
		}
	}
}


