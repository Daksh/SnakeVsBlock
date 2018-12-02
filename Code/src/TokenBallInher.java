import javafx.scene.Group;
import javafx.scene.Scene;

/**
 * Inherits from Token class. Initializes the balls to be collected during game play
 * in accordance with avatar of snake.
 * TODO: Change the path according to avatar of snake.
 *
 * @author Daksh Shah and Arsh Verma
 */

public class TokenBallInher extends BallTokens {
    private boolean flag=false;

    public TokenBallInher(Group tg){
        super(tg);
    }
    public TokenBallInher(){super();}

	/**
	 * @return Path of the token image in directory
	 */
    @Override
    protected String getPath() {
        return "";
    }

    @Override
    public void collides(){
        if(_tokenGroup!=null && _snake!=null) {
            System.out.println("TBall COLLISION");
            if(!flag)_snake.increase_length(_currentTokenObj._weight);
            flag = true;
            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag=false;
            });
            t.start();

            _tokenGroup.getChildren().clear();
        }
    }
}
