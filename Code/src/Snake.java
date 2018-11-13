import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import static javafx.scene.paint.Color.*;

public class Snake {
    ArrayList<Circle> SnakeBody = new ArrayList<Circle>();
    //int snakePosx, getSnakePosy;
    boolean goLeft, goRight;
    Group snake2;
    protected void addSnake(Scene scene, Blocks B1) {


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
        snake2= new Group();
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
        snake2.setLayoutX(0);
        snake2.setLayoutY(0);

        snakeBody4.setVisible(false);
        snakeBody5.setVisible(false);
        snakeBody6.setVisible(false);
        snakeBody7.setVisible(false);
        snakeBody8.setVisible(false);

        snake1.getChildren().add(snake2);

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
                if (hasCollided(B1)) {
                    double d1=snake2.getLayoutX();
                    B1.setCollisionWithSnake(true);
                    //if ()
                    //System.out.println(d1);
                }
                else {
                    if (goRight && snake2.getLayoutX() < 240) {
                        snake2.setLayoutX(snake2.getLayoutX() + 10);
                    }
                    if (goLeft && snake2.getLayoutX() > -240) {
                        snake2.setLayoutX(snake2.getLayoutX() - 10);
                    }
                }
            }
        };
        timer.start();
    }

    protected boolean hasCollided (Blocks B1) {
        //-400 because the coordinate boundaries seem different in Snake.java and Blocks.java
//        if ((snake2.getLayoutY()==B1.yCoordinateOfFirstSetOfBlocks()-400) || (snake2.getLayoutY()==B1.yCoordinateOfSecondSetOfBlocks()-400))
//            return true;
        return false;
    }
}
