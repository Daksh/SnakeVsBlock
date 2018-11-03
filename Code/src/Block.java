import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static javafx.scene.paint.Color.*;

public class Block {
    protected void addBlock(Scene scene) {
        Rectangle Block1= new Rectangle(100,100, YELLOW);

        Rectangle Block2= new Rectangle(100,100, RED);
        Rectangle Block3= new Rectangle(100,100, BLUE);
        Rectangle Block4= new Rectangle(100,100, GREEN);
        Rectangle Block5= new Rectangle(100,100, ORANGE);
        Block1.setX(0);
        //Block.setY(-800);
        Block2.setX(100);
        //Block2.setY(-800);
        Block3.setX(200);
        //Block3.setY(-800);
        Block4.setX(300);
        //Block4.setY(-800);
        Block5.setX(400);
        //Block5.setY(-800);

        Group block1= new Group();

        block1.getChildren().add(Block1);
        block1.getChildren().add(Block2);
        block1.getChildren().add(Block3);
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
