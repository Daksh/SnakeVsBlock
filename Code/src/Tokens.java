/**
 * Abstract class that Randomly chooses one token to be initialized in game play window.
 * Token moves from top to bottom of screen.
 *
 * @author Daksh Shah and Arsh Verma
 */

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Random;

public abstract class Tokens {
//    String path1;
//    Tokens tokenObj;
//    public Tokens() {
//
//        path1 = tokenObj.getPath();
//    }
    public static double TOKEN_SPEED = 3;
    ImageView TokenView= new ImageView();

    protected void addToken(Scene scene) {

        Group tokens= new Group();

        tokens.getChildren().add(TokenView);

        Group tokensgroup= (Group) scene.getRoot();

        tokensgroup.getChildren().add(tokens);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Random random = new Random();
                int rInt1 = random.nextInt(5);
                if (tokens.getLayoutY()>750) {
                    //System.out.println("hello");
                    Tokens t1= getToken(rInt1);
                    String path = t1.getPath();
                    File imagefile= new File(path);
                    Image TokenImage = new Image(imagefile.toURI().toString());
                    TokenView.setImage(TokenImage);
                    TokenView.setFitHeight(30);
                    TokenView.setFitWidth(30);
                    tokens.setLayoutY(-700);
                    tokens.setLayoutX(random.nextInt(440)-220);//Bounded from 20 to 220 when the screen is from 0 to 240
                }
                else{
                    tokens.setLayoutY(tokens.getLayoutY()+TOKEN_SPEED);
                }
            }
        };
        timer.start();
    }

	/**
	 * Generates a new token based on a randomly generated integer
	 * (range 0 to 4; one for each of the 5 tokens)
	 *
	 * @param a Randomly generated integer which chooses the token to be instantiated.
	 * @return Token object that has to be spawned.
	 */
	protected static Tokens getToken(int a) {
        Tokens obj;
        switch (a) {
            case 0:
                obj = new Magnet();
                break;
            case 1:
                obj = new Shield();
                break;
            case 2:
                obj = new Coin();
                break;
            case 3:
                obj = new TBall();
                break;
            case 4:
                obj = new DestroyBlocks();
                break;
            default:
                obj = null;
        }
        return obj;
    }

	/**
	 * Abstract method implemented in subclasses;
	 *
	 * @return Returns the token path in file directory.
	 */
    protected abstract String getPath();
}
