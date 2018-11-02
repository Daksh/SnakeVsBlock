import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static javafx.scene.paint.Color.YELLOW;

public class Block {
    protected void addBlock(Scene scene) {
        Rectangle Block= new Rectangle(100,100, YELLOW);
        Block.setX(100);
        Block.setY(0);

        Group block1= (Group) scene.getRoot();

        block1.getChildren().add(Block);

        TranslateTransition transition1 = new TranslateTransition();

        transition1.setNode(Block);

        transition1.setByY(700);
        transition1.setDuration(Duration.millis(5000));
        transition1.play();

    }
}
