import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.paint.Color.*;

public class GamePlay {




    public static int score = 0;
    public static int sceneCol=0;
    public static ArrayList<AnimationTimer> ANIMTimers = new ArrayList<AnimationTimer>();

    private static Menu gameMenu3;



    private Blocks _blocks;
    private Wall _wall;
    private Tokens _tokens;

    public GamePlay(){
        _blocks = new Blocks();
        _wall = new Wall(); //White lines
        _tokens = new Magnet();
    }

    protected void setUpGame (Stage primaryStage) throws IOException {
        //Blocks testBlocks, Wall testWall, Tokens testMagnet
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayDisp.fxml"));
        Group ballGroup= new Group();
        Parent root = loader.load();
        Group superGroup = new Group();

        Menu gameMenu = new Menu("Main");
        gameMenu.getItems().add(new MenuItem("Restart Main"));
        gameMenu.getItems().add(new MenuItem("Exit Main"));
        Menu gameMenu2 = new Menu("Settings");
        gameMenu2.getItems().add(new MenuItem("Modify settings"));
        Menu gameMenu4 = new Menu ( "								score: ");
        gameMenu3 = new Menu (Integer.toString(score));

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
        testBlocks = new Blocks(masterSnake,scene);//needs Snake to know what kind of _blocks to spell
        masterSnake.setBlocksRef(testBlocks);
        Tokens.setBlocks(testBlocks);
        Tokens.setSnake(masterSnake);

        BallTokens.setBlocks(testBlocks);
        BallTokens.setSnake(masterSnake);

        Wall.setSnake(masterSnake);

        testMagnet.addToken(scene);
        Tokens testMagnet2 = new Magnet();
        testMagnet2.addToken(scene);

        new Magnet().addToken(scene);

        new TokenBallInher().addToken(scene);
        new TokenBallInher().addToken(scene);
        new TokenBallInher().addToken(scene);
        new TokenBallInher().addToken(scene);

        testWall.addWall(scene);
    }

    private static void updateScoreLabel(int score){
        Main.score = score;
        Main.gameMenu3.setText(Integer.toString(score));
        try {
            serializeScore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void increaseScore(int delta){
        Main.score = Main.score + delta;
        updateScoreLabel(Main.score);
    }

    public static int getScore(){
        return Main.score;
    }

    public static void over() {
        System.out.println("GAME OVER from GAME.java");
        for(int i=0; i<ANIMTimers.size(); i++)
            ANIMTimers.get(i).stop();
        try {
            Thread.sleep(1000);
            Main.prevScore = Main.score;
            Main.serializeUser();
            Main.serializeLeaderboard();

            //System.exit(1);
            HomeCtrl hm = new HomeCtrl();
            hm.updatePrevBest();
            hm.openHomeScreen(Main.mainStage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.score = 0;
    }

}
