package cs2810;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class WaiterloginController {

	@FXML
	private TextField userAccount;
	@FXML
	private PasswordField userPwd;
	@FXML
	private Button clear;
	@FXML
	private Button login;
	
	@FXML
    private Button backToOrder;

	@FXML
	void changeScreenButtonPushed(ActionEvent event) throws IOException {
	  Stage stage = (Stage) backToOrder.getScene().getWindow();
	  stage.close();
	}

	void changeScreenLoginCorrect(ActionEvent event) throws IOException {
	  FXMLLoader loader = new FXMLLoader(getClass().getResource("/WaiterView.fxml"));
      Parent root = loader.load();
      WaiterViewController controller = loader.getController();

      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.show();
	}

	@FXML
	void clearButton(ActionEvent event) throws IOException {
		userPwd.setText("");
		userAccount.setText("");
	}

	@FXML
	void loginButton(ActionEvent event) throws IOException {
		if (ViewCustomerInterface.findUser(userAccount.getText(), userPwd.getText())) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("success");
			alert.setHeaderText(null);
			alert.setContentText("Login successful");
			alert.showAndWait();
			Stage stage = (Stage) login.getScene().getWindow();
		    stage.close();
			changeScreenLoginCorrect(event);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("error");
			alert.setHeaderText(null);
			alert.setContentText("User name or password error, please try again");
			alert.showAndWait();
			userAccount.clear();
			userPwd.clear();

		}

	}

}
