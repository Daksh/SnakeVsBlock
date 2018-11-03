import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static javafx.scene.paint.Color.*;

public class Block {
    protected void addBlock(Scene scene) {
        Rectangle Block1= new Rectangle(100,100, YELLOW);
        Block1.setX(0);
        Text text1 = new Text("Lorem");
        StackPane stack1 = new StackPane();
        stack1.getChildren().addAll(Block1, text1);

        Rectangle Block2= new Rectangle(100,100, RED);
        Block2.setX(100);
        Text text2 = new Text("Lorem");
        StackPane stack2 = new StackPane();
        stack2.getChildren().addAll(Block2, text2);

        Rectangle Block3= new Rectangle(100,100, BLUE);
        Block3.setX(200);
        Text text3 = new Text("Ipsum");
        StackPane stack3 = new StackPane();
        stack3.getChildren().addAll(Block3, text3);


        Rectangle Block4= new Rectangle(100,100, GREEN);
        Block4.setX(300);
        Text text4 = new Text("Dolor");
        StackPane stack4 = new StackPane();
        stack4.getChildren().addAll(Block4, text4);


        Rectangle Block5= new Rectangle(100,100, ORANGE);
        Block5.setX(400);
        Text text5 = new Text("Sit");
        StackPane stack5 = new StackPane();
        stack5.getChildren().addAll(Block5, text5);


        Group block1= new Group();

        block1.getChildren().add(stack1);
        block1.getChildren().add(stack2);
        block1.getChildren().add(stack3);
        block1.getChildren().add(Block4);
        block1.getChildren().add(Block5);
        block1.setLayoutY(-800);




        Group blocks= (Group) scene.getRoot();

        blocks.getChildren().add(block1);
        TranslateTransition transition1 = new TranslateTransition();

        transition1.setNode(block1);

        transition1.setByY(1700);
        transition1.setDuration(Duration.millis(7000));
        transition1.setCycleCount(Animation.INDEFINITE);
        transition1.play();


    }
}
