import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;

import static javafx.scene.paint.Color.GREEN;

public class Snake {
    ArrayList<Circle> SnakeBody = new ArrayList<Circle>();

    boolean goLeft, goRight;
    protected void addSnake(Scene scene) {
        Circle snakeHead= new Circle(250,500,8, GREEN);

        Group snake1= (Group) scene.getRoot();

        snake1.getChildren().add(snakeHead);

        TranslateTransition transition1 = new TranslateTransition();

        transition1.setNode(snakeHead);

        transition1.setByY(-0);
        transition1.setDuration(Duration.millis(3000));
        transition1.play();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT: goLeft = true; break;
                    case RIGHT: goRight  = true; break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT:  goLeft  = false; break;
                    case RIGHT: goRight  = false; break;
                }
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (goRight)
                {
                    transition1.setByX(10);
                    transition1.setDuration(Duration.millis(40));
                    transition1.play();
                }
                if (goLeft){
                    transition1.setByX(-10);
                    transition1.setDuration(Duration.millis(40));
                    transition1.play();
                    //System.out.println("Left");
                }
            }
        };
        timer.start();

    }
}
