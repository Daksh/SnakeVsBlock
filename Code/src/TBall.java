import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.util.Random;

import static javafx.scene.paint.Color.RED;

/**
 * Inherits from Token class. Initializes the balls to be collected during game play
 * in accordance with avatar of snake.
 * TODO: Change the path according to avatar of snake.
 *
 * @author Daksh Shah and Arsh Verma
 */

public class TBall extends Tokens {
//    protected  String path = "./../Images/icons8-mega_ball.png";
    private int _weight;

    public TBall(Group tg){
        super(tg);
    }
    public TBall(){super();}

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
            _tokenGroup.getChildren().clear();
        }
    }

    @Override
    protected void addToken(Scene scene){
        this.addTBALL(scene);
    }
}
