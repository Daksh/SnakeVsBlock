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
	public static double WALL_SPEED = Blocks.BLOCK_SPEED;
	Random random = new Random();
	Rectangle Wall1, Wall2;
    private Group _wallGroup = new Group();
    private static Snake _snake;

    public static void setSnake(Snake snake){
        _snake = snake;
    }

	public void addWall(Scene scene) {
    	int r1 = random.nextInt(300)+250;
		int r2 = random.nextInt(300)+250;
        Wall1 = new Rectangle(2,r1, WHITE);
        Wall2 = new Rectangle(2,r2, WHITE);
		int r3 = random.nextInt(3)+1;
		int r4 = random.nextInt(3)+1;
        Wall1.setX(99*r3);
        Wall2.setX(99*r4);

        _wallGroup.getChildren().add(Wall1);
        _wallGroup.getChildren().add(Wall2);
        _wallGroup.setLayoutY(-700);

        Group rootGroup = (Group) scene.getRoot();
        rootGroup.getChildren().add(_wallGroup);

        AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (_wallGroup.getLayoutY()>750) {
					int r1 = random.nextInt(300)+250;
					int r2 = random.nextInt(300)+250;
					Wall1 = new Rectangle(2,r1, WHITE);
					Wall2 = new Rectangle(2,r2, WHITE);
					int r3 = random.nextInt(3)+1;
					int r4 = random.nextInt(3)+1;
					Wall1.setX(99*r3);
					Wall2.setX(99*r4);

					_wallGroup.getChildren().clear();
					_wallGroup.getChildren().add(Wall1);
					_wallGroup.getChildren().add(Wall2);
					_wallGroup.setLayoutY(-700);
				}
				else{
					_wallGroup.setLayoutY(_wallGroup.getLayoutY()+WALL_SPEED);
                    if(checkCollision()) collides();
				}
			}
		};
		timer.start();
    }

    public void collides(){
        if(_wallGroup!=null && _snake!=null) {
            System.out.println("COLLISION");
            _wallGroup.getChildren().clear();
        }
    }

    private boolean checkCollision(){
        if(_snake==null || _wallGroup==null) {
            System.out.println("is null");
            return false;
        }
        double sx = 235+_snake.getXCoordinate();
        double sy = 500+_snake.getYCoordinate(); //always 0 it seems
        double myx = _wallGroup.getLayoutX();
        double myy = _wallGroup.getLayoutY();
//        System.out.println(sx+","+sy+"\t"+myx+","+myy);

        if((Math.abs(sx-myx)<Snake.RADIUS) && Math.abs(sy-myy)<Snake.RADIUS)
            return true;
        return false;
    }
}
