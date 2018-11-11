import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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

    protected void addToken(Scene scene, Tokens t1) {
        String path = t1.getPath();
        File imagefile= new File(path);
        Image TokenImage = new Image(imagefile.toURI().toString());
        TokenView.setImage(TokenImage);
        TokenView.setFitHeight(30);
        TokenView.setFitWidth(30);

        TokenView.setX(200);
        TokenView.setY(-1000);
        Group tokens= new Group();

        tokens.getChildren().add(TokenView);

        Group tokensgroup= (Group) scene.getRoot();

        tokensgroup.getChildren().add(tokens);
        TranslateTransition transition1 = new TranslateTransition();

        transition1.setNode(tokens);

        transition1.setByY(1700);
        transition1.setDuration(Duration.millis(7000));
        transition1.setCycleCount(1);
        transition1.play();
    }


    protected abstract String getPath();
}
