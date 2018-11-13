import javafx.application.Application;
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

import java.util.Random;

public class Game extends Application {
    private Snake masterSnake = new Snake();
    private Block testBlock = new Block();
    private Wall testWall = new Wall(); //White lines
    private Magnet testMagnet = new Magnet();
    private BorderPane layout;
    private Random random = new Random();
    private int rInt = random.nextInt(5);


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SnakeVsBlock");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayDisp.fxml"));
        Group ballGroup= new Group();
        Parent root = loader.load();
        Scene scene = new Scene(ballGroup, 500,700, Color.BLACK);
        Menu gameMenu = new Menu("Game");
        gameMenu.getItems().add(new MenuItem("Restart Game"));
        gameMenu.getItems().add(new MenuItem("Exit Game"));
        Menu gameMenu2 = new Menu("Settings");
        gameMenu2.getItems().add(new MenuItem("Modify settings"));

        MenuBar Bar = new MenuBar();
        Bar.getMenus().addAll(gameMenu,gameMenu2);

        BorderPane layout = new BorderPane();
        layout.setTop(Bar);
        ballGroup.getChildren().add(Bar);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        testBlock.addBlock(scene);
        masterSnake.addSnake(scene, testBlock);
        //testWall.addWall(scene);
        //Tokens Tokenobj = getToken(rInt);
        //System.out.println(rInt);
        testMagnet.addToken(scene);
        //testBlock.addMenu(scene);

        primaryStage.show();
    }

}


