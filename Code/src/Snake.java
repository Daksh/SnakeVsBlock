import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;

import static javafx.scene.paint.Color.*;

public class Snake {
    ArrayList<Circle> SnakeBody = new ArrayList<Circle>();

    boolean goLeft, goRight;
    protected void addSnake(Scene scene) {


        Circle snakeLabel= new Circle(250,510,10, BLUE);

        Circle snakeHead= new Circle(250,530,10, GREEN);
        Circle snakeBody1= new Circle(250,550,10, RED);
        Circle snakeBody2= new Circle(250,570,10, RED);
        Circle snakeBody3= new Circle(250,590,10, RED);
        Circle snakeBody4= new Circle(250,610,10, RED);
        Circle snakeBody5= new Circle(250,630,10, RED);
        Circle snakeBody6= new Circle(250,650,10, RED);
        Circle snakeBody7= new Circle(250,670,10, RED);
        Circle snakeBody8= new Circle(250,690,10, RED);

        Group snake1= (Group) scene.getRoot();
        Group snake2= new Group();
        snake2.getChildren().add(snakeLabel);
        snake2.getChildren().add(snakeHead);
        snake2.getChildren().add(snakeBody1);
        snake2.getChildren().add(snakeBody2);
        snake2.getChildren().add(snakeBody3);
        snake2.getChildren().add(snakeBody4);
        snake2.getChildren().add(snakeBody5);
        snake2.getChildren().add(snakeBody6);
        snake2.getChildren().add(snakeBody7);
        snake2.getChildren().add(snakeBody8);

        snakeBody4.setVisible(false);
        snakeBody5.setVisible(false);
        snakeBody6.setVisible(false);
        snakeBody7.setVisible(false);
        snakeBody8.setVisible(false);


        snake1.getChildren().add(snake2);

        TranslateTransition transition1 = new TranslateTransition();

        transition1.setNode(snake2);

        //transition1.setByY(-0);
        //transition1.setDuration(Duration.millis(3000));
        //transition1.play();

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

                if (goRight && snakeHead.getTranslateX() <240)
                {
                    transition1.setByX(10);
                    transition1.setDuration(Duration.millis(40));
                    transition1.play();
                }
                if (goLeft && snakeHead.getTranslateX()>-240){
                    transition1.setByX(-10);
                    transition1.setDuration(Duration.millis(40));
                    transition1.play();
                }
            }
        };
        timer.start();

    }
}
