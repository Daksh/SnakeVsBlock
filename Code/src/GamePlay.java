import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.paint.Color.*;


public class GamePlay {
    private static final double LIFEINC = 0.2f;

    public int sceneCol;
    public ArrayList<AnimationTimer> ANIMTimers;

    private int _score;
    private double _life;
    private Menu _gameMenu3;
	private Menu _gameMenu5;
    private Blocks _blocks;
    private Wall _wall;
    private Stage _mainStage;
    private Snake _snake;

    public double getLife(){
        return _life;
    }

    public Snake getSnake(){
        return _snake;
    }

    public GamePlay(Stage primaryStage, int score, double life){
        sceneCol = 0;
        ANIMTimers = new ArrayList<AnimationTimer>();
        _score = score;
        _life = life;
    }

    public void startGame(Stage primaryStage, int snakeLength){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayDisp.fxml"));

        Group ballGroup= new Group();
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Group superGroup = new Group();

        Menu gameMenu = new Menu("		");
        Menu gameMenu2 = new Menu("		");
        //gameMenu2.getItems().add(new MenuItem("Modify settings"));
		Menu gameMenu6 = new Menu ( "			Life: ");
		_gameMenu5 = new Menu (Double.toString(_life));
		Menu gameMenu4 = new Menu ( "		Score: ");
		_gameMenu3 = new Menu (Integer.toString(_score));

        MenuBar Bar = new MenuBar();
        Bar.getMenus().addAll(gameMenu,gameMenu2, gameMenu6, _gameMenu5, gameMenu4, _gameMenu3);
        Bar.setMinWidth(500.0);
		Button	RestartGamebtn = new Button("Restart Game");
		Button	Exitbtn = new Button("Exit");
		Exitbtn.setLayoutX(100);
		RestartGamebtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main newMain = new Main();
				newMain.restartGame(primaryStage);
			}
		});
		Exitbtn.setOnAction(event -> System.exit(1));

        BorderPane layout = new BorderPane();
        layout.setTop(Bar);
        ballGroup.getChildren().add(Bar);
        ballGroup.getChildren().add(RestartGamebtn);
        ballGroup.getChildren().add(Exitbtn);

        superGroup.getChildren().addAll(ballGroup, root);
        Scene scene = new Scene(superGroup, 500,700, BLACK);
        Color[] arr = {BLACK, LIGHTBLUE, LIGHTGREEN, WHITE};
        scene.setFill(arr[sceneCol]);
        _mainStage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        //Generating a snake
        _snake = Snake.getInstance(snakeLength, scene);
        System.out.println("Asked for a snake with length "+snakeLength+"; got one for "+_snake.get_length());

        _blocks = new Blocks(_snake,scene);//needs Snake to know what kind of _blocks to spell
        _snake.setBlocksRef(_blocks);

        _wall = new Wall();
        Wall.setSnake(_snake);
        _wall.addWall(scene);

        //Configuring Tokens and BallTokens Classes
        Tokens.setBlocks(_blocks);
        Tokens.setSnake(_snake);
        BallTokens.setBlocks(_blocks);
        BallTokens.setSnake(_snake);

        //Spawning 3 Tokens (recurrently)
        new Magnet().addToken(scene);
        new Magnet().addToken(scene);
        new Magnet().addToken(scene);

        //Spawning 4 Balls (recurrently)
        new TokenBallInher().addToken(scene);
        new TokenBallInher().addToken(scene);
        new TokenBallInher().addToken(scene);
        new TokenBallInher().addToken(scene);
    }

    public void over() {
        if(reduceLifeByOne()) return;

        System.out.println("GAME OVER from GAME.java");
        for(int i=0; i<ANIMTimers.size(); i++)
            ANIMTimers.get(i).stop();
        try {
            Thread.sleep(1000);
            Main.prevScore = this._score;

            //System.exit(1);
            HomeCtrl hm = new HomeCtrl();
            hm.openHomeScreen(this._mainStage); // removes elements of GamePlay from screen and adds the HomeScreen elements
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this._score = 0;
        Main.removeGameRef();
    }


    private void updateScoreLabel(int score){
        _score = score;
        _gameMenu3.setText(Integer.toString(score));
        DataStore.serialize();
    }

    public void increaseScore(int delta){
        _score = _score + delta;
        updateScoreLabel(_score);
    }

    public int getScore(){
        return this._score;
    }


    private void updateLifeLabel(double life){
        _life = life;
        _gameMenu5.setText(String.format("%.2f", life));
    }

    /**
     * Increases the life by LIFEINC
     */
    public void increaseLife(){
        updateLifeLabel(_life+LIFEINC);
    }

    /**
     * When you run into a block and loose a life
     * @return success boolean
     */
    public boolean reduceLifeByOne(){
        if(_life-1<0)
            return false;

        updateLifeLabel(_life-1);
        return true;
    }
}
