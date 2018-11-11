
public class Magnet extends Tokens{
    protected  String path = "./../Images/icons8-magnet-96.png";
    /*ImageView MagView= new ImageView();
    protected void addMagnet(Scene scene) {
        File imagefile= new File(path);
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
    }*/


    @Override
    protected String getPath() {
        return this.path;
    }
}
