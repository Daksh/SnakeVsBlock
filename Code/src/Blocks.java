/**
 * Initializes two sets of blocks repeatedly to move from top to bottom of the screen.
 *
 * @author Daksh Shah and Arsh Verma
 */

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.io.File;
import java.io.Serializable;
import java.util.Random;

import static javafx.scene.paint.Color.WHITE;

public class Blocks implements Serializable{
	/**
     * NUM : Max number of blocks that can be spawned in a scene.
     * SLEEPMIL : When the block size is > 5, it sleeps for blockWeight * SLEEPMIL
	 */
    private static final int NUM=5, SLEEPMIL=100;


	File imagefile = new File("./BurstAnimation.gif");
	Image burst = new Image(imagefile.toURI().toString());
	private Scene _scene;
	private Group AnimationGroup = new Group();
	public ImageView AnimationView = new ImageView();
	public static AnimationTimer timer;

	/**
	 * Set of Colors of Google, https://www.color-hex.com/color-palette/67855
	 */
    private static final javafx.scene.paint.Color[] colorPalette = {javafx.scene.paint.Color.web("#4285f4"),
            javafx.scene.paint.Color.web("#ea4335"),
            javafx.scene.paint.Color.web("#fbbc05"),
            javafx.scene.paint.Color.web("#34a853"),
            javafx.scene.paint.Color.web("#673ab7")};

	/**
	 * _oneBlockGroup : First group of blocks that translate to bottom.
	 * _anotherBlockGroup : Second groups of blocks that translate to bottom.
	 * _oneBlockStack : Stack of first group of blocks. Contains information of each block in group.
	 * _anotherBlockStack : Stack of second group of blocks. Contains information of each block in group.
	 * _collision : boolean variable that checks for collision between block and snake
	 * _snakeRef : Stores reference to snake object from the game.
	 */
	private Group _oneBlockGroup, _anotherBlockGroup;
    private StackPane[] _oneBlockStack, _anotherBlockStack;
    private boolean _collision;
    private Snake _snakeRef;
    private Random _random;


	public Blocks() {}
	/**
	 * Initializes block groups amd adds them to scene. Starts their animation
	 * @param snake Reference of snake object from game play
	 * @param scene Reference of game play scene
	 */

	public Blocks(Snake snake, Scene scene){
        _snakeRef = snake;
        _random = new Random();
        _oneBlockStack = new StackPane[NUM];
        _anotherBlockStack = new StackPane[NUM];
        _scene = scene;

        Group rootSceneGroup = (Group)scene.getRoot();

        //(0,-500) is the starting point where we spawn the set of Blocks
        _oneBlockGroup = initBlocks(rootSceneGroup,0,-800, "one");

        //(0,-150) for the other set of blocks
        _anotherBlockGroup = initBlocks(rootSceneGroup,0,-150, "another");

        animateBlocks(rootSceneGroup);
    }

	/**
	 * Makes a stack pane to link the block with its weight and xCoordinate
	 *
	 * @param weight Initial weight of block to be created. Weight  = 0 - No block required in that position.
	 * @param xCoord Horizontal position of block on screen.
	 * @return StackPane of Block
	 */
	private StackPane makeBlock(int weight, int xCoord){
        if(weight == 0)
            return null;

        Rectangle block = new Rectangle(Main.BLOCK_WIDTH, Main.BLOCK_HEIGHT,colorPalette[_random.nextInt(colorPalette.length)]);
        //Setting the height and width of the arc
        block.setArcWidth(30.0);
        block.setArcHeight(20.0);

        Text text = new Text(Integer.toString(weight));
        text.setFont(Font.font(null, FontWeight.LIGHT, 20));

        StackPane stack = new StackPane();
        stack.getChildren().addAll(block, text);
        stack.setLayoutX(xCoord);

        return stack;
    }

	/**
	 * Initializes group of blocks with random weights.
	 * Maximum possible weight has been defined.
	 * @param rootScene All blocks are added to this group which is added to scene.
	 * @param x Initializes the x coordinate of block group ( = 0)
	 * @param y Initalizes the y coordinate of block group. Different for both block groups.
	 * @param from ( = 1 when oneBlockGroup else anotherBlockGroup)
	 * @return Returns a group of blocks
	 */
    private Group initBlocks(Group rootScene, int x, int y,String from){
        //Random Blocks

        //Length of Snake
        int maxBlockWeight = _snakeRef.get_length(); // of the minimumWeightedBlock of the group

        int[] weight = new int[5];
        for(int i = 0; i<5; i++)
            weight[i] = _random.nextInt(maxBlockWeight*3);
        weight[_random.nextInt(5)] = _random.nextInt((int)Math.ceil((double)maxBlockWeight/2));// Divided by 2 because we have 2 blockLayers at 1 pt in time

        StackPane[] stacks = new StackPane[5];
        Group localBlockGroup = new Group();

        for(int i=0; i<5; i++) {
            stacks[i] = makeBlock(weight[i],i*100);
            if (stacks[i] != null) localBlockGroup.getChildren().add(stacks[i]);
        }

        if(from.equals("one")) _oneBlockStack = stacks;
        else _anotherBlockStack = stacks;

        localBlockGroup.setLayoutX(x);
        localBlockGroup.setLayoutY(y);

        rootScene.getChildren().add(localBlockGroup);

        return localBlockGroup;
    }

	/**
	 * Repeats code inside animation timer to start second group animation
	 * @param rootSceneGroup Passes the group where blocks need to be initialized,
	 */
	private void animateBlocks(Group rootSceneGroup) {
        //To repeat the inner code
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //How many blocks do we wish to spawn - we get from Random
                int rInt1 = _random.nextInt(5);
                if(_oneBlockGroup.getLayoutY() > 750){
                    //Destroy the reference as the group of Blocks has moved out of the screen
                    _oneBlockGroup = initBlocks(rootSceneGroup,0,-500,"one");//-800 originally
                } else moveBlockGroup(_oneBlockGroup);

                if(_anotherBlockGroup.getLayoutY() > 750){
                    _anotherBlockGroup = initBlocks(rootSceneGroup,0,-500,"another");//-150 originally
                } else moveBlockGroup(_anotherBlockGroup);
//                System.out.println("_oneBlockGroup's y: "+_oneBlockGroup.getLayoutY());
//                System.out.println("_anotherBlockGroup's y: "+_anotherBlockGroup.getLayoutY());

            }
        };
        Main._gamePlay.ANIMTimers.add(timer);
        timer.start();
    }

	/**
	 * Translates group of blocks from top to bottom of screen.
	 * TODO : Check block speed/5. can cause trouble if block speed is increased.
	 * @param blockGroup Group of blocks that are being translated
	 */
	private void moveBlockGroup(Group blockGroup){
        if (!_collision)
            blockGroup.setLayoutY(blockGroup.getLayoutY()+Main.SPEED);
//        else{
//            //There is a _collision!
//            blockGroup.setLayoutY(blockGroup.getLayoutY()+BLOCK_SPEED/5);
//        }
    }

	/**
	 * Check for collision
	 * @return boolean value of collision status
	 */
	public boolean getIsCollidedRn(){
        return this._collision;
    }

	/**
	 * Checks for collision between block and snake
	 * @param blockNumber block inside group (1 to 5) with which collision occurs.
	 * @param x x coordinate of snake
	 */
	public void checkCollisionWithSnake(int blockNumber, double x){
        int pos=-1;
        if(x < -150) pos = 0;
        else if(x < -50) pos = 1;
        else if(x < 50) pos = 2;
        else if(x < 150) pos = 3;
        else pos = 4;

        if(blockNumber==1){
            //oneBlock collision
            if(_oneBlockStack[pos]!=null) setCollisionWithSnake(_oneBlockStack[pos],"one",pos);
            else setCollisionWithSnake(false);
        }else{
            //anotherBlock collision
            if(_anotherBlockStack[pos]!=null) setCollisionWithSnake(_anotherBlockStack[pos],"another",pos);
            else setCollisionWithSnake(false);
        }
    }

    public void destroyAllBlocksInScreen(){
	    int totalWeight = 0;

	    if(checkBlockSetInScreen(_oneBlockGroup)){
	        for(int i=0; i<NUM; i++){
                if(_oneBlockStack[i]!=null) {
                    if(!_oneBlockStack[i].getChildren().isEmpty()) totalWeight += Integer.parseInt(((Text)_oneBlockStack[i].getChildren().get(1)).getText());
                    _oneBlockStack[i].getChildren().clear();
                }
            }
        }

        if(checkBlockSetInScreen(_anotherBlockGroup)){
            for(int i=0; i<NUM; i++){
                if(_anotherBlockStack[i]!=null) {
                    if(!_anotherBlockStack[i].getChildren().isEmpty()) totalWeight += Integer.parseInt(((Text)_anotherBlockStack[i].getChildren().get(1)).getText());
                    _anotherBlockStack[i].getChildren().clear();
                }
            }
        }

        Main._gamePlay.increaseScore(totalWeight);
	}

    private boolean checkBlockSetInScreen(Group blocksGroup){
	    return blocksGroup.getLayoutY()>-100 && blocksGroup.getLayoutY()<650;
    }

	/**
	 * Handles collision between block and snake.
	 * @param stack Reference to Stack of blocks
	 * @param by Checks th group of blocks which collied with snake ( one - _oenBlockGroup, else - anotherBlockGroup)
	 * @param pos position ( block number inside stack with which collision occurs. )
	 */
	public void setCollisionWithSnake(StackPane stack, String by, int pos){
        if(stack.getChildren().isEmpty())
            return;

		String weightString = ((Text)stack.getChildren().get(1)).getText();

        int weight = Integer.parseInt(weightString);
        int snakeLen = _snakeRef.get_length();

        if(weight>=snakeLen && !Snake.getShieldStatus()) {
			//timer.stop();
			Main._gamePlay.over();
		}

        else if(weight<=5 || Snake.getShieldStatus()) {
            _snakeRef.reduce_length(weight);
//			AnimationView.setImage(burst);
//			AnimationView.setFitHeight(98);
//			AnimationView.setFitWidth(98);
//			AnimationView.setX(_snakeRef.getXCoordinate());
//			AnimationView.setY(_snakeRef.getYCoordinate());
//			AnimationGroup.getChildren().add(AnimationView);

            if(by.equals("one")) {
            	_oneBlockStack[pos].getChildren().clear();
				_oneBlockGroup.getChildren().add(AnimationGroup);
			}
            else{
                _anotherBlockStack[pos].getChildren().clear();

				_anotherBlockGroup.getChildren().add(AnimationGroup);
			}
            System.out.println("Removed all elements from stack");
            this._collision = false;

        } else{
            while(weight>1){
                _snakeRef.reduce_length(1);
                weight-=1;

                try {
                    Thread.sleep(SLEEPMIL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            _snakeRef.reduce_length(weight);
            if(by.equals("one")) _oneBlockStack[pos].getChildren().clear();
            else _anotherBlockStack[pos].getChildren().clear();
            this._collision = false;

        }
		Paint fill = _scene.getFill();
		_scene.setFill(WHITE);
		Thread t = new Thread(() -> {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			_scene.setFill(fill);
		});
		t.start();

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

	public void explosion (double x, double y){
		
	}
}
