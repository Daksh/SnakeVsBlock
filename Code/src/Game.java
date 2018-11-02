import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Game extends Application {
    Snake masterSnake = new Snake();
    Block testBlock = new Block();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SnakeVsBlock");
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Group ballGroup= new Group();
        //Parent root = loader.load();
        Scene scene = new Scene(ballGroup, 500,700, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        testBlock.addBlock(scene);
        masterSnake.addSnake(scene);
        primaryStage.show();
    }



    }


