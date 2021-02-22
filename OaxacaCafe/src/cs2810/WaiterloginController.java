package cs2810;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WaiterloginController {
  
  private ViewCustomerInterface parent;
  
  ArrayList<Order> pendingOrders;
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

  void changeScreenLoginCorrect(ActionEvent event, String staff) throws IOException {
    System.out.println(staff);
    if (staff.equals("waiter")) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/WaiterView.fxml"));
      Parent root = loader.load();
      WaiterViewController controller = loader.getController();
      controller.setInitialData(parent, pendingOrders);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.show();
    }
    else if(staff.equals("kitchen")) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/KitchenView.fxml"));
      Parent root = loader.load();
      KitchenStaffView controller = loader.getController();
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.show();
    }
    
  }

  public void setPendingOrders(ArrayList<Order> pendingOrders) {
    this.pendingOrders = pendingOrders;
  }

  @FXML
  void clearButton(ActionEvent event) throws IOException {
    userPwd.setText("");
    userAccount.setText("");
  }

  @FXML
  void loginButton(ActionEvent event) throws IOException {
    if (ViewCustomerInterface.findUser(userAccount.getText(), userPwd.getText(), "waiter")) {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("success");
      alert.setHeaderText(null);
      alert.setContentText("Login successful");
      new LoginMessage().getMessage().put("Login", "successful");
      alert.showAndWait();
      Stage stage = (Stage) login.getScene().getWindow();
      stage.close();
      changeScreenLoginCorrect(event, "waiter");
    }
    else if(ViewCustomerInterface.findUser(userAccount.getText(), userPwd.getText(), "kitchen")) {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("success");
      alert.setHeaderText(null);
      alert.setContentText("Login successful");
      new LoginMessage().getMessage().put("Login", "successful");
      alert.showAndWait();
      Stage stage = (Stage) login.getScene().getWindow();
      stage.close();
      changeScreenLoginCorrect(event, "kitchen");
      
    }
    else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("error");
      alert.setHeaderText(null);
      alert.setContentText("User name or password error, please try again");
      alert.showAndWait();
      userAccount.clear();
      userPwd.clear();

    }

  }
  
  public void setInitialData(ViewCustomerInterface parent, ArrayList<Order> pendingOrders){
    this.parent = parent;
    setPendingOrders(pendingOrders);
  }

}
