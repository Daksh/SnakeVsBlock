/**
 * Serves as controller class for Leaderboard FXML file.
 *
 * @author Daksh Shah and Arsh Verma
 */

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class LeadCtrl implements Initializable {
    public Button homebtn;
    @ FXML public TableView TableView;
    @ FXML public TableColumn SNo;
	@FXML public TableColumn Name;
	@FXML public TableColumn Date;
	@FXML public TableColumn Score;

	public ObservableList<User> data = FXCollections.observableArrayList(new User("Arsh", 34),new User("Daksh", 37));
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SNo.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(data.size())));
		//SNo.setCellValueFactory(new PropertyValueFactory<User, Integer>("S.No."));
		Name.setCellValueFactory(new PropertyValueFactory<User, String>("Name"));
		Date.setCellValueFactory(new PropertyValueFactory<User, LocalDate>("Date"));
		Score.setCellValueFactory(new PropertyValueFactory<User, Integer>("Score"));
		Score.setSortType(TableColumn.SortType.DESCENDING);
		TableView.getItems().setAll(data);

	}

	/**
	 * Loads Home Screen elements on stage when Home Button is clicked by user.
	 * @throws IOException
	 */
	@FXML
    public void handleHomeBtnClick() throws IOException {
        Stage stage = (Stage) homebtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));

        stage.setScene(new Scene(root));
    }
}

