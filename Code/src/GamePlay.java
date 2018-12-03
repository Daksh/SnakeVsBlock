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

    public int score = 0;
    public int sceneCol=0;
    public ArrayList<AnimationTimer> ANIMTimers = new ArrayList<AnimationTimer>();

    private Menu gameMenu3;
    private Blocks _blocks;
    private Wall _wall;
    private Tokens _token;
    private Stage _mainStage;
    private Snake _snake;


    public GamePlay(){
        _blocks = new Blocks();
        _wall = new Wall(); //White lines
        _token = new Magnet();
    }

    protected void setUpGame (Stage primaryStage) throws IOException {
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
        _mainStage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        //There is a problem as both of the following need each other :\
        _snake = Snake.getInstance(20, scene);
        _blocks = new Blocks(_snake,scene);//needs Snake to know what kind of _blocks to spell
        _snake.setBlocksRef(_blocks);
        Tokens.setBlocks(_blocks);
        Tokens.setSnake(_snake);

        BallTokens.setBlocks(_blocks);
        BallTokens.setSnake(_snake);

        Wall.setSnake(_snake);

        _token.addToken(scene);
        Tokens _tokens2 = new Magnet();
        _tokens2.addToken(scene);

        new Magnet().addToken(scene);

        new TokenBallInher().addToken(scene);
        new TokenBallInher().addToken(scene);
        new TokenBallInher().addToken(scene);
        new TokenBallInher().addToken(scene);

        _wall.addWall(scene);
    }

    private void updateScoreLabel(int score){
        this.score = score;
        this.gameMenu3.setText(Integer.toString(score));
    }

    public void increaseScore(int delta){
        this.score = this.score + delta;
        updateScoreLabel(this.score);
    }

    public int getScore(){
        return this.score;
    }

    public void over() {
        System.out.println("GAME OVER from GAME.java");
        for(int i=0; i<ANIMTimers.size(); i++)
            ANIMTimers.get(i).stop();
        try {
            Thread.sleep(1000);
            Main.prevScore = this.score;

            //System.exit(1);
            HomeCtrl hm = new HomeCtrl();
            hm.openHomeScreen(this._mainStage); // removes elements of GamePlay from screen and adds the HomeScreen elements
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.score = 0;
    }

}
