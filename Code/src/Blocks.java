import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.Random;
import static javafx.scene.paint.Color.*;

public class Blocks {
    private static final int WIDTH = 100, HEIGHT = 100;
    private Group _oneBlockGroup, _anotherBlockGroup;
    private boolean _collision;
    private Snake _snakeRef;
    private Random _random;

    public Blocks(Snake snake){
        _snakeRef = snake;
        _random = new Random();
    }

    private StackPane makeBlock(javafx.scene.paint.Color color, int weight, int xCoord){
        //if weight = 0 we do not want a block there
//        if(weight == 0)
//            return null;

        Rectangle Block = new Rectangle(WIDTH,HEIGHT,color);
        Text text = new Text(Integer.toString(weight));

        StackPane stack = new StackPane();
        stack.getChildren().addAll(Block, text);
        stack.setLayoutX(xCoord);

        return stack;
    }
    
    private Group initBlocks(int x, int y){
        //Random Blocks

        //Length of Snake
        int maxBlockWeight = _snakeRef.get_length(); // of the minimumWeightedBlock of the group

        int[] weight = new int[5];
        for(int i = 0; i<5; i++)
            weight[i] = _random.nextInt(maxBlockWeight*5);
        weight[_random.nextInt(5)] = _random.nextInt(maxBlockWeight);

        StackPane stack1 = makeBlock(YELLOW,weight[0],0);
        StackPane stack2 = makeBlock(RED,weight[1],100);
        StackPane stack3 = makeBlock(BLUE,weight[2],200);
        StackPane stack4 = makeBlock(GREEN,weight[3],300);
        StackPane stack5 = makeBlock(ORANGE,weight[4],400);

        Group localBlockGroup = new Group();
        localBlockGroup.getChildren().add(stack1);
        localBlockGroup.getChildren().add(stack2);
        localBlockGroup.getChildren().add(stack3);
        localBlockGroup.getChildren().add(stack4);
        localBlockGroup.getChildren().add(stack5);

        localBlockGroup.setLayoutX(x);
        localBlockGroup.setLayoutY(y);

        return localBlockGroup;
    }

    protected void addBlock(Scene scene) {
        //(0,-500) is the starting point where we spawn the set of Blocks
        _oneBlockGroup = initBlocks(0,-800);

        //(0,-150) for the other set of blocks
        _anotherBlockGroup = initBlocks(0,-150);

        Group sceneRoot = (Group)scene.getRoot();
        sceneRoot.getChildren().add(_oneBlockGroup);
        sceneRoot.getChildren().add(_anotherBlockGroup);

        //To repeat the inner code
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //How many blocks do we wish to spawn - we get from Random
                int rInt1 = _random.nextInt(5);

                moveBlockGroup(_oneBlockGroup);
                moveBlockGroup(_anotherBlockGroup);



            }
        };
        timer.start();
    }

    private void moveBlockGroup(Group blockGroup){
        int yCoord = (int) blockGroup.getLayoutY();

        if (yCoord > 750) //Basically it would just be 755
            blockGroup.setLayoutY(yCoord-1255);//Moving the blocksSet that goes out of screen, to the TOP where we cannot see
        else{
            if (!_collision) {
                blockGroup.setLayoutY(blockGroup.getLayoutY()+5);
            }
            else{
                //There is a _collision!
            }
        }
    }

    public boolean getIsCollidedRn(){
        return this._collision;
    }
    public void setCollisionWithSnake(Boolean x){
        this._collision = x;
    }

    public int yCoordinateOfFirstSetOfBlocks(){
        return (int) _oneBlockGroup.getLayoutY();
    }
    public int yCoordinateOfSecondSetOfBlocks(){
        return (int) _anotherBlockGroup.getLayoutY();
    }
}
