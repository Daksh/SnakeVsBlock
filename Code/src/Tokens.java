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
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.util.Random;

import static javafx.scene.paint.Color.RED;

public abstract class Tokens {

	/**
	 * Token speed defines the speed of downward motion of token
	 */
    public static double TOKEN_SPEED = Blocks.BLOCK_SPEED;
    public static final int MAGNETMUL = 4;

    private ImageView _tokenView = new ImageView();
    protected Tokens _currentTokenObj = null;
    protected static Snake _snake;
    protected static Blocks _blocks;
    protected Group _tokenGroup = new Group();

    private static int _magnetMultiplier = 1;

    public static void setMagnetOnFor(long duration){
        Tokens._magnetMultiplier = Tokens.MAGNETMUL;

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Turning off Magnet Affect");
            Tokens._magnetMultiplier = 1;
        });
        t.start();
    }

    public Tokens(){
        _currentTokenObj = this;
    }

    public Tokens(Group tGroup){this._tokenGroup = tGroup; _currentTokenObj = this;}

    public static void setSnake(Snake snake){
        _snake = snake;
    }
    public static void setBlocks(Blocks block){ _blocks = block;}

	/**
	 * adds a random taken to gameplay. Ramdom integer generated recurrently to determine the token to be spawned.
	 * @param scene Provides scene to which token needs to be added. It is the game play scene.
	 */
	protected void addToken(Scene scene){
        _tokenGroup.getChildren().add(_tokenView);

        Group tokensgroup = (Group)scene.getRoot();
		tokensgroup.getChildren().add(_tokenGroup);

		/**
		 * Animation Timer to translate token along with blocks.
		 * Image of token is set inside imageView.
		 * Token translated towards bottom end while it is inside screen. Moved to top when it violates lower bound.
		 */

		AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Random random = new Random();
                int rInt1 = random.nextInt(5);


                if (_tokenGroup.getLayoutY()>750) {
                    _tokenGroup.getChildren().clear();

                    _currentTokenObj = getToken(rInt1);
                    String path = _currentTokenObj.getPath();
                    File imagefile = new File(path);
                    Image TokenImage = new Image(imagefile.toURI().toString());
                    _tokenView.setImage(TokenImage);
                    _tokenView.setFitHeight(30);
                    _tokenView.setFitWidth(30);

                    _tokenGroup.getChildren().add(_tokenView);
                    _tokenGroup.setLayoutY(-700+random.nextInt(600));//Check @Arsh
                    _tokenGroup.setLayoutX(20+random.nextInt(200));//Bounded from 20 to 220 when the screen is from 0 to 240
                }
                else{
                    _tokenGroup.setLayoutY(_tokenGroup.getLayoutY()+TOKEN_SPEED);
//                    System.out.println(_tokenGroup.getLayoutX()+","+_tokenGroup.getLayoutY());
//                    if(_currentTokenObj !=null && _currentTokenObj.checkCollision()) _currentTokenObj.collides();
                    if(checkCollision()) _currentTokenObj.collides();
                }
            }
        };
		Main.ANIMTimers.add(timer);
        timer.start();
    }


    protected void addTBALL(Scene scene){
        _tokenGroup.getChildren().add(_tokenView);

        Group tokensgroup = (Group)scene.getRoot();
        tokensgroup.getChildren().add(_tokenGroup);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Random random = new Random();
                int rInt1 = random.nextInt(5);

                if (_tokenGroup.getLayoutY()>750) {
                    _tokenGroup.getChildren().clear();

                    _currentTokenObj = getToken(rInt1);

                    int _weight = 1+random.nextInt(5);

                    Circle circle = new Circle(Snake.RADIUS, RED);
                    Text text = new Text(Integer.toString(_weight));
                    text.setFont(Font.font(null, FontWeight.BOLD, Snake.RADIUS));

                    StackPane x = new StackPane();
                    x.getChildren().addAll(circle, text);

                    _tokenGroup.getChildren().add(x);

                    _tokenGroup.setLayoutY(-700+random.nextInt(600));
                    _tokenGroup.setLayoutX(20+random.nextInt(200));//Bounded from 20 to 220 when the screen is from 0 to 240
                }
                else{
                    _tokenGroup.setLayoutY(_tokenGroup.getLayoutY()+TOKEN_SPEED);
//                    System.out.println(_tokenGroup.getLayoutX()+","+_tokenGroup.getLayoutY());
                    if(checkCollision()) _currentTokenObj.collides();
                }
            }
        };
        Main.ANIMTimers.add(timer);
        timer.start();
    }

    public void collides(){
        if(_tokenGroup!=null && _snake!=null) {
            System.out.println("COLLISION");
            _tokenGroup.getChildren().clear();
        }
    }

    protected boolean checkCollision(){
        if(_snake==null || _tokenGroup==null) {
            System.out.println("is null");
            return false;
        }
	    double sx = 235+_snake.getXCoordinate();
	    double sy = 500+_snake.getYCoordinate(); //always 0 it seems
	    double myx = _tokenGroup.getLayoutX();
	    double myy = _tokenGroup.getLayoutY();
//        System.out.println(sx+","+sy+"\t"+myx+","+myy);

        if((Math.abs(sx-myx)<(Snake.RADIUS+15)*_magnetMultiplier) && Math.abs(sy-myy)<(Snake.RADIUS*_magnetMultiplier))
            return true;
        return false;
    }

	/**
	 * Generates a new token based on a randomly generated integer
	 * (range 0 to 4; one for each of the 5 tokens)
	 *
	 * @param a Randomly generated integer which chooses the token to be instantiated.
	 * @return Token object that has to be spawned.
	 */
    protected Tokens getToken(int a) {
        Tokens obj;
        switch (a) {
            case 0:
                obj = new Magnet(_tokenGroup);
                break;
            case 1:
                obj = new Shield(_tokenGroup);
                break;
            case 2:
                obj = new Coin(_tokenGroup);
                break;
            case 3:
                obj = new TBall(_tokenGroup);
                break;
            case 4:
                obj = new DestroyBlocks(_tokenGroup);
                break;
            default:
                obj = null;
        }
        return obj;
    }

	/**
	 * Abstract method implemented in subclasses;
	 *
	 * @return Returns the token image path in file directory.
	 */
    protected abstract String getPath();
}
