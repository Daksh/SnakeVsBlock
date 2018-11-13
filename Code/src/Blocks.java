import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.Random;
import static javafx.scene.paint.Color.*;

public class Blocks {
    private static final int WIDTH = 100, HEIGHT = 100, BLOCK_SPEED = 3;
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
        if(weight == 0)
            return null;

        Rectangle Block = new Rectangle(WIDTH,HEIGHT,color);
        Text text = new Text(Integer.toString(weight));

        StackPane stack = new StackPane();
        stack.getChildren().addAll(Block, text);
        stack.setLayoutX(xCoord);

        return stack;
    }
    
    private Group initBlocks(Group rootScene, int x, int y){
        //Random Blocks

        //Length of Snake
        int maxBlockWeight = _snakeRef.get_length(); // of the minimumWeightedBlock of the group

        int[] weight = new int[5];
        for(int i = 0; i<5; i++)
            weight[i] = _random.nextInt(maxBlockWeight*5);
        weight[_random.nextInt(5)] = _random.nextInt(maxBlockWeight);

        StackPane[] stacks = new StackPane[5];
        stacks[0] = makeBlock(YELLOW,weight[0],0);
        stacks[1] = makeBlock(RED,weight[1],100);
        stacks[2] = makeBlock(BLUE,weight[2],200);
        stacks[3] = makeBlock(GREEN,weight[3],300);
        stacks[4] = makeBlock(ORANGE,weight[4],400);

        Group localBlockGroup = new Group();
        for(int i=0; i<5; i++)
            if(stacks[i] != null) localBlockGroup.getChildren().add(stacks[i]);

        localBlockGroup.setLayoutX(x);
        localBlockGroup.setLayoutY(y);

        rootScene.getChildren().add(localBlockGroup);

        return localBlockGroup;
    }

    protected void addBlock(Scene scene) {
        Group rootScene = (Group)scene.getRoot();

        //(0,-500) is the starting point where we spawn the set of Blocks
        _oneBlockGroup = initBlocks(rootScene,0,-800);

        //(0,-150) for the other set of blocks
        _anotherBlockGroup = initBlocks(rootScene,0,-150);


        //To repeat the inner code
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //How many blocks do we wish to spawn - we get from Random
                int rInt1 = _random.nextInt(5);

                if(_oneBlockGroup.getLayoutY() > 750){
                    //Destroy the reference as the group of Blocks has moved out of the screen
                    _oneBlockGroup = initBlocks(rootScene,0,-500);//-800 originally
                } else moveBlockGroup(_oneBlockGroup);

                if(_anotherBlockGroup.getLayoutY() > 750){
                    _anotherBlockGroup = initBlocks(rootScene,0,-500);//-150 originally
                } else moveBlockGroup(_anotherBlockGroup);

            }
        };
        timer.start();
    }

    private void moveBlockGroup(Group blockGroup){
        if (!_collision) {
            blockGroup.setLayoutY(blockGroup.getLayoutY()+BLOCK_SPEED);
        }
        else{
            //There is a _collision!
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
