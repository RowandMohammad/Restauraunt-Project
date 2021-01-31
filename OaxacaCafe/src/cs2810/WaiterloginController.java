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
	  void changeScreenButtonPushed(ActionEvent event) throws IOException {
	    Parent menuViewParent = FXMLLoader.load(getClass().getResource("/CustomerView.fxml"));
	    Scene menuViewScene = new Scene(menuViewParent, 800, 800);
	    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    window.setScene(menuViewScene);
	    window.show();
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
			     MyWindow window = new MyWindow();
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


	
