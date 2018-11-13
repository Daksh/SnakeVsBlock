import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

import static javafx.scene.paint.Color.*;

public class Blocks {
    private Group oneBlockGroup, anotherBlockGroup;
    private boolean collision;
    
    private void modifyBlocksRandom(Group blockGroup){

    }
    private Group initBlocks(int x, int y){
        Rectangle Block1= new Rectangle(100,100, YELLOW);
        int ctr1=0; //Counter (Value/Size of the block)
        Text text1 = new Text(Integer.toString(ctr1));
        StackPane stack1 = new StackPane();
        stack1.getChildren().addAll(Block1, text1);
        stack1.setLayoutX(0);

        Rectangle Block2= new Rectangle(100,100, RED);
        int ctr2=0;
        Text text2 = new Text(Integer.toString(ctr2));
        StackPane stack2 = new StackPane();
        stack2.getChildren().addAll(Block2, text2);
        stack2.setLayoutX(100);

        Rectangle Block3= new Rectangle(100,100, BLUE);
        int ctr3=0;
        Text text3 = new Text(Integer.toString(ctr3));
        StackPane stack3 = new StackPane();
        stack3.getChildren().addAll(Block3, text3);
        stack3.setLayoutX(200);

        Rectangle Block4= new Rectangle(100,100, GREEN);
        int ctr4=0;
        Text text4 = new Text(Integer.toString(ctr4));
        StackPane stack4 = new StackPane();
        stack4.getChildren().addAll(Block4, text4);
        stack4.setLayoutX(300);


        Rectangle Block5= new Rectangle(100,100, ORANGE);
        int ctr5=0;
        Text text5 = new Text(Integer.toString(ctr5));
        StackPane stack5 = new StackPane();
        stack5.getChildren().addAll(Block5, text5);
        stack5.setLayoutX(400);

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
        oneBlockGroup = initBlocks(0,-800);

        //(0,-150) for the other set of blocks
        anotherBlockGroup = initBlocks(0,-150);

        Group sceneRoot = (Group)scene.getRoot();
        sceneRoot.getChildren().add(oneBlockGroup);
        sceneRoot.getChildren().add(anotherBlockGroup);

        Random random = new Random();

        //To repeat the inner code
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //How many blocks do we wish to spawn - we get from Random
                int rInt1 = random.nextInt(5);

                moveBlockGroup(oneBlockGroup);
                moveBlockGroup(anotherBlockGroup);



            }
        };
        timer.start();
    }

    private void moveBlockGroup(Group blockGroup){
        int yCoord = (int) blockGroup.getLayoutY();

        if (yCoord > 750) //Basically it would just be 755
            blockGroup.setLayoutY(yCoord-1255);//Moving the blocksSet that goes out of screen, to the TOP where we cannot see
        else{
            if (!collision) {
                blockGroup.setLayoutY(blockGroup.getLayoutY()+5);
//                modifyBlocksRandom();
            }
            else{
                //There is a collision!
            }
        }
    }

    public boolean getIsCollidedRn(){
        return this.collision;
    }
    public void setCollisionWithSnake(Boolean x){
        this.collision = x;
    }

    public int yCoordinateOfFirstSetOfBlocks(){
        return (int) oneBlockGroup.getLayoutY();
    }
    public int yCoordinateOfSecondSetOfBlocks(){
        return (int) anotherBlockGroup.getLayoutY();
    }
}
