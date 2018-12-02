import javafx.scene.Group;

/**
 * Inherits from Token class. Initializes the balls to be collected during game play
 * in accordance with avatar of snake.
 * TODO: Change the path according to avatar of snake.
 *
 * @author Daksh Shah and Arsh Verma
 */

public class TBall extends Tokens {
    protected  String path = "./../Images/icons8-magnet-96.png";

    public TBall(Group tg){
        super(tg);
    }
    public TBall(){super();}

	/**
	 * @return Path of the token image in directory
	 */
    @Override
    protected String getPath() {
        return this.path;
    }

    @Override
    public void collides(){
        if(_tokenGroup!=null && _snake!=null) {
            System.out.println("TBall COLLISION");
            _tokenGroup.getChildren().clear();
        }
    }
}
