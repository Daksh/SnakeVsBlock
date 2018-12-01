/**
 *
 * Initializes walls that serve as obstructions during game play.
 * Restricts snake movement to a section of the screen
 *
 * @author Daksh Shah & Arsh Verma
 */

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import java.util.Random;
import static javafx.scene.paint.Color.WHITE;

public class Wall {
	public static double WALL_SPEED = 3;
	Random random = new Random();
	Rectangle Wall1, Wall2;

	protected void addWall(Scene scene) {
    	int r1 = random.nextInt(300)+250;
		int r2 = random.nextInt(300)+250;
        Wall1= new Rectangle(2,r1, WHITE);
        Wall2= new Rectangle(2,r2, WHITE);
		int r3 = random.nextInt(3)+1;
		int r4 = random.nextInt(3)+1;
        Wall1.setX(99*r3);
        Wall2.setX(99*r4);

        Group walls= new Group();

        walls.getChildren().add(Wall1);
        walls.getChildren().add(Wall2);
        walls.setLayoutY(-700);

        Group wallgroup = (Group) scene.getRoot();
        wallgroup.getChildren().add(walls);

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {

				if (walls.getLayoutY()>750) {
					int r1 = random.nextInt(300)+250;
					int r2 = random.nextInt(300)+250;
					Wall1= new Rectangle(2,r1, WHITE);
					Wall2= new Rectangle(2,r2, WHITE);
					int r3 = random.nextInt(3)+1;
					int r4 = random.nextInt(3)+1;
					Wall1.setX(99*r3);
					Wall2.setX(99*r4);
					walls.getChildren().clear();
					walls.getChildren().add(Wall1);
					walls.getChildren().add(Wall2);
					walls.setLayoutY(-700);
				}
				else{
					walls.setLayoutY(walls.getLayoutY()+WALL_SPEED);
				}
			}
		};
		timer.start();
    }
}
