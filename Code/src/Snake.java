import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;

import static javafx.scene.paint.Color.*;

public class Snake {
    private static final int SNAKEX = 250;

    private ArrayList<Circle> SnakeBody = new ArrayList<Circle>();

    private Group _snakeGroup;
    private StackPane _snakeHead;
    private int _length;
    private boolean _goLeft, _goRight;

    public Snake(){
        _length = 5;
    }

    public int get_length(){
        return _length;
    }

    public void set_length(int length){
        _length = length;

        //update the label on snake's head
        _snakeHead = getHead(length);
    }

    private StackPane getHead(int length){
        StackPane x = new StackPane();
        Circle circle = new Circle(10, GREEN);
        Text text = new Text(Integer.toString(length));
        x.getChildren().addAll(circle, text);
        x.setLayoutX(SNAKEX);
        x.setLayoutY(510);
        return x;
    }

    protected void addSnake(Scene scene, Blocks B1) {
        Group rootSceneGroup = (Group) scene.getRoot();
        _snakeGroup = new Group();

        _snakeHead = getHead(get_length());

        Circle[] snakeBody = new Circle[9];
        _snakeGroup.getChildren().add(_snakeHead);

        for(int i=0; i<snakeBody.length; i++) {
            snakeBody[i] = new Circle(SNAKEX, 530 + 20 * i, 10, RED);
            _snakeGroup.getChildren().add(snakeBody[i]);
        }
        _snakeGroup.setLayoutX(0);
        _snakeGroup.setLayoutY(0);

        snakeBody[4].setVisible(false);
        snakeBody[5].setVisible(false);
        snakeBody[6].setVisible(false);
        snakeBody[7].setVisible(false);
        snakeBody[8].setVisible(false);

        rootSceneGroup.getChildren().add(_snakeGroup);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT: _goLeft = true; break;
                    case RIGHT: _goRight = true; break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT:  _goLeft = false; break;
                    case RIGHT: _goRight = false; break;
                }
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (hasCollided(B1)) {
                    double d1= _snakeGroup.getLayoutX();
                    B1.setCollisionWithSnake(true);

                }
                else {
                    if (_goRight && _snakeGroup.getLayoutX() < 240)
                        moveHorizontally(10);
                    if (_goLeft && _snakeGroup.getLayoutX() > -240)
                        moveHorizontally(-10);
                }
            }
        };
        timer.start();
    }

    private void moveHorizontally(int delta){
        //Change this code if we want to make the snake transit smoothly
        _snakeGroup.setLayoutX(_snakeGroup.getLayoutX() + delta);
    }

    private boolean hasCollided (Blocks B1) {
        //-400 because the coordinate boundaries seem different in Snake.java and Blocks.java
        if ((_snakeGroup.getLayoutY()==B1.yCoordinateOfFirstSetOfBlocks()-400) || (_snakeGroup.getLayoutY()==B1.yCoordinateOfSecondSetOfBlocks()-400))
            return true;
        return false;
    }
}
