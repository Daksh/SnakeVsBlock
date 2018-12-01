import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.ArrayList;
import static javafx.scene.paint.Color.*;

/**
 * Created by Daksh Shah and Arsh Verma
 */

/**
 * TODO: maybe implement Singular Design Concept
 */
public class Snake {
    private static final int SNAKEX = 250, RADIUS = 15, STARTY=510, BLOCKHEIGHT=Blocks.HEIGHT;

    private ArrayList<Circle> SnakeBody = new ArrayList<Circle>();

    private Group _snakeGroup;
    private Circle[] _snakeBody;
    private StackPane _snakeHead;
    private int _length;
    private boolean _goLeft, _goRight, _checkedCollision;
    private Scene _scene;
    private Blocks _blocksRef;

    public void setBlocksRef(Blocks blocks){
        _blocksRef = blocks;
    }

    public Snake(int length, Scene scene){
        _length = length;
        _scene = scene;
        _checkedCollision = false;

        addKeyListeners();

        Group rootSceneGroup = (Group) _scene.getRoot();
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

        for(int i=_length; i<9; i++) _snakeBody[i].setVisible(false);

        rootSceneGroup.getChildren().add(_snakeGroup);

        animateSnake();
    }

    public int get_length(){
        return _length;
    }

    public void reduce_length(int delta){
        set_length(get_length()-delta);
    }

    private void set_length(int length){
        if(length==0){//REMOVE?
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

    private void addKeyListeners(){
        _scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT: _goLeft = true; break;
                    case RIGHT: _goRight = true; break;
                }
            }
        });

        _scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT:  _goLeft = false; break;
                    case RIGHT: _goRight = false; break;
                }
            }
        });
    }

    private void animateSnake() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (_checkedCollision==false && _blocksRef!=null && inRegionOfBlocks(_blocksRef)>0) {
                    double d1= _snakeGroup.getLayoutX();
                    _blocksRef.checkCollisionWithSnake(inRegionOfBlocks(_blocksRef),d1);//to confirm the block availab. at that position
                    _checkedCollision = true;
                } else {
                    if(!(_blocksRef!=null && inRegionOfBlocks(_blocksRef)>0)) _checkedCollision = false;
                    if (_blocksRef!=null) _blocksRef.setCollisionWithSnake(false);
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
        //Change this code if we want to make the snake transit smoothly IT DOES NOT WORK! Check jiggle branch
        _snakeGroup.setLayoutX(_snakeGroup.getLayoutX() + delta);
    }

    private int inRegionOfBlocks(Blocks B1) { //just by the y coordinate
        double checkFrom = _snakeGroup.getLayoutY();
        double checkWith = B1.yCoordinateOfFirstSetOfBlocks()-400-BLOCKHEIGHT+((double)RADIUS/2);
        if (checkFrom>checkWith && checkFrom<checkWith+BLOCKHEIGHT)
            return 1;

        checkWith = B1.yCoordinateOfSecondSetOfBlocks()-400-BLOCKHEIGHT+((double)RADIUS/2);
        if(checkFrom>checkWith && checkFrom<checkWith+BLOCKHEIGHT)
            return 2;
        return 0;
    }
}
