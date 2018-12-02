import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginCtrl {
	public Button exitbtn;
	public Button guestbtn;
	public Button loginbtn;
	public Button NewUserbtn;
	public TextField pass;
	public TextField username;

	@FXML
	public void Exit() throws IOException {
		System.exit(1);
	}

	@FXML
	public void NewGuest() throws IOException
	{
		User Guest = new User("GuestUser", 0);
		User.Users.add(Guest);
		Stage stage = (Stage) guestbtn.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		stage.setScene(new Scene(root));
	}

	@FXML
	public void NewSignUp() throws IOException
	{
		User Guest = new User(username.getText(), 0);
		User.Users.add(Guest);
		User.UserPasswords.put(username.getText(), pass.getText());
		Stage stage = (Stage) NewUserbtn.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));

		stage.setScene(new Scene(root));
	}

	@FXML
	public void SignIn() throws IOException
	{
		User Guest = new User("GuestUser", 0);
		User.Users.add(Guest);
		Stage stage = (Stage) loginbtn.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));

		stage.setScene(new Scene(root));
	}

}
