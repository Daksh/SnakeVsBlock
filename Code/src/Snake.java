import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

import static javafx.scene.paint.Color.*;

public class Snake {
    private static final int SNAKEX = 250, RADIUS = 15, STARTY=510, RIGHTBOUND = 240, LEFTBOUND = -240;

    private ArrayList<Circle> SnakeBody = new ArrayList<Circle>();

    private Group _snakeGroup;
    private Circle[] _snakeBody;
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
        if(length==0){
            System.out.println("END GAME!!!");
            //add code here :P
            return;
        }
        _length = length;

        //update the label on snake's head
        StackPane pseudo = getHead(length);
        _snakeHead.getChildren().removeAll();
        _snakeHead.getChildren().addAll(pseudo.getChildren());

        for(int i=0; i<_snakeBody.length; i++){
            if(i<length-1) _snakeBody[i].setVisible(true);
            else _snakeBody[i].setVisible(false);
        }
    }

    private StackPane getHead(int length){
        StackPane x = new StackPane();
        Circle circle = new Circle(RADIUS, GREEN);
        Text text = new Text(Integer.toString(length));
        text.setFill(WHITE);
        text.setFont(Font.font(null, FontWeight.BOLD, RADIUS));

        x.getChildren().addAll(circle, text);
        //-10 (-RADIUS instead) because circle and StackPane have different defns of positioning
        x.setLayoutX(SNAKEX-RADIUS);
        x.setLayoutY(STARTY-RADIUS);
        return x;
    }

    protected void addSnake(Scene scene, Blocks B1) {
        Group rootSceneGroup = (Group) scene.getRoot();
        _snakeGroup = new Group();

        _snakeHead = getHead(get_length());

        _snakeBody = new Circle[9];
        _snakeGroup.getChildren().add(_snakeHead);

        for(int i = 0; i< _snakeBody.length; i++) {
            _snakeBody[i] = new Circle(SNAKEX, STARTY+ 2*RADIUS*(i+1), RADIUS, RED);
            _snakeGroup.getChildren().add(_snakeBody[i]);
        }
        _snakeGroup.setLayoutX(0);
        _snakeGroup.setLayoutY(0);

        for(int i=4; i<9; i++) _snakeBody[i].setVisible(false);

        //Adding the whole snake into the scene's group
        rootSceneGroup.getChildren().add(_snakeGroup);


        //Handling KeyBoard Interrupts
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
                    if (_goRight && _snakeGroup.getLayoutX() < RIGHTBOUND)
                        moveHorizontally(10);
                    if (_goLeft && _snakeGroup.getLayoutX() > LEFTBOUND)
                        moveHorizontally(-10);
                }
            }
        };
        timer.start();
    }

    private void moveHorizontally(int delta){
        //Change this code if we want to make the snake transit smoothly
//        _snakeGroup.setLayoutX(_snakeGroup.getLayoutX() + delta);
        int destination = (int)_snakeHead.getLayoutX()+delta;
        _snakeHead.setLayoutX(destination);

//        System.out.println("Delta, Destination: "+delta+","+destination);

        //THIS SEEMS CORRECT
        destination+=RADIUS;
//        if(delta>0) destination+=RADIUS;//cause of ref problem
//        else destination-=RADIUS;

        for(int i=0; i<_snakeBody.length; i++){
            double thisDelta = (double)(_snakeBody.length-i+1)/20;
            thisDelta = (double)(destination - _snakeBody[i].getCenterX())/20*(double)(_snakeBody.length-i+1);
            thisDelta = 1;
            if(delta<0)
                thisDelta = -1*thisDelta;
            jiggleCircle(_snakeBody[i], thisDelta,destination);
//            _snakeBody[i].setCenterX(_snakeBody[i].getCenterX()+thisDelta);
        }
    }

    private void jiggleCircle(Circle circle, double delta, int dest){
//        circle.setCenterX(dest);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(delta<0 && circle.getCenterX()>dest){//Moving Left
                    circle.setCenterX(circle.getCenterX()+delta);
                    System.out.println(2);
                }
                if(delta>0 && circle.getCenterX()<dest) {//Moving Right
                    circle.setCenterX(circle.getCenterX() + delta);
                    System.out.println(3);
                }
            }
        };
        timer.start();
    }

    private boolean hasCollided (Blocks B1) {
        //-400 because the coordinate boundaries seem different in Snake.java and Blocks.java
        if ((_snakeGroup.getLayoutY()==B1.yCoordinateOfFirstSetOfBlocks()-400) || (_snakeGroup.getLayoutY()==B1.yCoordinateOfSecondSetOfBlocks()-400))
            return true;
        return false;
    }
}
