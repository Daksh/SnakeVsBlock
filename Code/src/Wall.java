/**
 *
 * Initializes walls that serve as obstructions during game play.
 * Restricts snake movement to a section of the screen
 *
 * @author Daksh Shah & Arsh Verma
 */

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static javafx.scene.paint.Color.WHITE;

public class Wall {
    protected void addWall(Scene scene) {
        Rectangle Wall1= new Rectangle(1,200, WHITE);

        Rectangle Wall2= new Rectangle(1,200, WHITE);
        Wall1.setX(200);
        Wall2.setX(400);

        Group walls= new Group();

        walls.getChildren().add(Wall1);
        walls.getChildren().add(Wall2);
        walls.setLayoutY(-700);

        Group wallgroup= (Group) scene.getRoot();

        wallgroup.getChildren().add(walls);
        TranslateTransition transition1 = new TranslateTransition();

        transition1.setNode(walls);

        transition1.setByY(1700);
        transition1.setDuration(Duration.millis(7000));
        transition1.setCycleCount(Animation.INDEFINITE);
        transition1.play();

        //wallgroup.setLayoutX();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (wallgroup.getLayoutY()>750) {
                    wallgroup.setLayoutY(-500);
                }
                else{
                    wallgroup.setLayoutY(wallgroup.getLayoutY()+5);
                }
            }
        };
        timer.start();

    }
}
