import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

import static javafx.scene.paint.Color.*;

public class Block {
    Group block1;
    boolean collision;
    protected void addBlock(Scene scene) {
        int ctr1=0;
        Rectangle Block1= new Rectangle(100,100, YELLOW);
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

        block1= new Group();

        block1.getChildren().add(stack1);
        block1.getChildren().add(stack2);
        block1.getChildren().add(stack3);
        block1.getChildren().add(stack4);
        block1.getChildren().add(stack5);
        block1.setLayoutY(-800);

        Group blocks= (Group) scene.getRoot();

        blocks.getChildren().add(block1);

        block1.setLayoutX(0);
        block1.setLayoutY(-500);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Random random = new Random();
                int rInt1 = random.nextInt(5);
                //System.out.println(rInt1);
                if (block1.getLayoutY()>750) {
                    block1.setLayoutY(-500);
                }
                else{
                    if (!collision) {
                        block1.setLayoutY(block1.getLayoutY()+5);
                    }

                }
            }
        };
        timer.start();
    }

}
