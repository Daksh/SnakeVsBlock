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
        Text text1 = new Text("Lorem");
        StackPane stack1 = new StackPane();
        stack1.getChildren().addAll(Block1, text1);
        stack1.setLayoutX(0);

        Rectangle Block2= new Rectangle(100,100, RED);
        Text text2 = new Text("Ipsum");
        StackPane stack2 = new StackPane();
        stack2.getChildren().addAll(Block2, text2);
        stack2.setLayoutX(100);

        Rectangle Block3= new Rectangle(100,100, BLUE);
        Text text3 = new Text("Dolor");
        StackPane stack3 = new StackPane();
        stack3.getChildren().addAll(Block3, text3);
        stack3.setLayoutX(200);

        Rectangle Block4= new Rectangle(100,100, GREEN);
        Text text4 = new Text("Sit");
        StackPane stack4 = new StackPane();
        stack4.getChildren().addAll(Block4, text4);
        stack4.setLayoutX(300);


        Rectangle Block5= new Rectangle(100,100, ORANGE);
        Text text5 = new Text("Amet");
        StackPane stack5 = new StackPane();
        stack5.getChildren().addAll(Block5, text5);
        stack5.setLayoutX(400);

        Group block1= new Group();

        block1.getChildren().add(stack1);
        block1.getChildren().add(stack2);
        block1.getChildren().add(stack3);
        block1.getChildren().add(stack4);
        block1.getChildren().add(stack5);
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

    protected void addMenu(Scene scene) {
        Rectangle Block1= new Rectangle(50,30, GREY);
        Text text1 = new Text("Restart");
        StackPane stack1 = new StackPane();
        stack1.getChildren().addAll(Block1, text1);
        stack1.setLayoutX(0);


        Rectangle Block2= new Rectangle(50,30, GREY);
        Text text2 = new Text("Exit");
        StackPane stack2 = new StackPane();
        stack2.getChildren().addAll(Block2, text2);
        stack2.setLayoutX(49);

        Rectangle Block3= new Rectangle(353,30, GREY);
        Text text3 = new Text("");
        StackPane stack3 = new StackPane();
        stack3.getChildren().addAll(Block3, text3);
        stack3.setLayoutX(98);

        Rectangle Block4= new Rectangle(50,30, GREY);
        Text text4 = new Text("0");
        StackPane stack4 = new StackPane();
        stack4.getChildren().addAll(Block4, text4);
        stack4.setLayoutX(450);

        Group block1= new Group();

        block1.getChildren().add(stack1);
        block1.getChildren().add(stack2);
        block1.getChildren().add(stack3);
        block1.getChildren().add(stack4);
        block1.setLayoutY(0);

        Group blocks= (Group) scene.getRoot();
        blocks.getChildren().add(block1);

    }
}
