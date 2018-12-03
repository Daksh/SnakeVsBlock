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

import java.io.Serializable;
import java.util.Random;
import static javafx.scene.paint.Color.WHITE;

public final class Wall implements Serializable {
	private static Random random = new Random();
    private static Snake _snake;

	private Rectangle _wallRect;
	private int _wallHeight;
    private Group _wallGroup = new Group();

    public static void setSnake(Snake snake){
        _snake = snake;
    }

	public void addWall(Scene scene) {
    	_wallHeight = random.nextInt(300)+150;
        _wallRect = new Rectangle(2,_wallHeight, WHITE);

		int r3 = random.nextInt(3)+1;

		_wallGroup.getChildren().clear();
        _wallGroup.getChildren().add(_wallRect);

        _wallGroup.setLayoutY(-700);
        _wallGroup.setLayoutX(99*r3);

        Group rootGroup = (Group) scene.getRoot();
        rootGroup.getChildren().add(_wallGroup);

        AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (_wallGroup.getLayoutY()>750) {
					_wallHeight = random.nextInt(300)+150;
					_wallRect = new Rectangle(2,_wallHeight, WHITE);

					int r3 = random.nextInt(3)+1;

					_wallGroup.getChildren().clear();
					_wallGroup.getChildren().add(_wallRect);

					_wallGroup.setLayoutY(-1*_wallHeight-random.nextInt(300));
//                    _wallGroup.setLayoutY(0);
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    _wallGroup.setLayoutX(99*r3);
				}
				else{
					_wallGroup.setLayoutY(_wallGroup.getLayoutY()+Main.SPEED);
                    if(checkCollision()) collides();
				}
			}
		};
        Main._gamePlay.ANIMTimers.add(timer);
		timer.start();
    }

    public void collides(){
        if(_wallGroup!=null && _snake!=null) {
            System.out.println("WALL COLLISION");
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            _wallGroup.getChildren().clear();
        }
    }

    private boolean checkCollision(){
        if(_snake==null || _wallGroup==null) {
            System.out.println("is null");
            return false;
        }
        double sx = 250+_snake.getXCoordinate();
        double sy = _snake.getYCoordinate(); //always 0 it seems
        double myx = _wallGroup.getLayoutX();
//        double myy = _wallGroup.getLayoutY()-_wallHeight;
        double myy = _wallGroup.getLayoutY();//-_wallHeight+((double)Main.SNAKE_RADIUS/2);
//        System.out.println("Snake: ("+sx+","+sy+")\tWall: ("+myx+","+myy+")\tWall Height: "+_wallHeight+"\t"+_wallRect.getY());

        if((Math.abs(sx-myx)<Main.SNAKE_RADIUS) && myy>500-_wallHeight && myy<500)
            return true;
        return false;
    }
}
