import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import static javafx.scene.paint.Color.YELLOW;


public class Game extends Application {

    static int dx = 1;
    static int dy = 1;

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
        addSnake(scene);
        primaryStage.show();
    }

    private void addSnake(Scene scene) {
        Rectangle Block= new Rectangle(100,100, YELLOW);

        Group snake1= (Group) scene.getRoot();

        snake1.getChildren().add(Block);

                TranslateTransition transition1 = new TranslateTransition();

                transition1.setNode(Block);

                transition1.setByY(-100);
                transition1.setDuration(Duration.millis(3000));
                transition1.play();

            }
    }

