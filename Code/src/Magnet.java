import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.File;

import static javafx.scene.paint.Color.WHITE;

public class Magnet {
    ImageView MagView= new ImageView();
    protected void addMagnet(Scene scene) {
        File imagefile= new File("./../Images/icons8-magnet-96.png");
        Image magnetImage = new Image(imagefile.toURI().toString());
        MagView.setImage(magnetImage);
        MagView.setFitHeight(30);
        MagView.setFitWidth(30);

        MagView.setX(200);
        MagView.setY(-1000);
        Group magnets= new Group();

        magnets.getChildren().add(MagView);

        Group magnetsgroup= (Group) scene.getRoot();

        magnetsgroup.getChildren().add(magnets);
        TranslateTransition transition1 = new TranslateTransition();

        transition1.setNode(magnets);

        transition1.setByY(1700);
        transition1.setDuration(Duration.millis(7000));
        transition1.setCycleCount(1);
        transition1.play();


    }


}
