import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Game extends Application {

    Stage window;
    BorderPane layout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window= primaryStage;
        window.setTitle("Snake vs Block: Main Menu");


        Menu Menu1= new Menu("Options");

        Menu1.getItems().add(new MenuItem("New Game"));
        Menu1.getItems().add(new MenuItem("Restart Game"));
        Menu1.getItems().add(new MenuItem("Resume Game"));

        layout = new BorderPane();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Scene scene = new Scene(layout, 400, 300);
        //window.setScene(scene);
        //window.show();
    }
}
