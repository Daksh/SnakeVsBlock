/**
 * Inherits from Token class. Initializes the balls to be collected during game play
 * in accordance with avatar of snake.
 * TODO: Change the path according to avatar of snake.
 *
 * @author Daksh Shah and Arsh Verma
 */

public class TBall extends Tokens {
    protected  String path = "./../Images/icons8-magnet-96.png";
    @Override
    protected String getPath() {
        return this.path;
    }
}
