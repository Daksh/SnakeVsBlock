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
                    tokens.setLayoutY(tokens.getLayoutY()+5);
                }
            }
        };
        timer.start();
    }

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


    protected abstract String getPath();
}
