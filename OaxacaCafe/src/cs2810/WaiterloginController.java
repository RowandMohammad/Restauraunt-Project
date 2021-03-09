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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Date;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.util.Duration;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class WaiterloginController {
  
  private ViewCustomerInterface parent;
  
  ArrayList<Order> pendingOrders;
  ArrayList<Order> ordersToCook;
  ArrayList<Order> ordersToDeliver;
  
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

  private WaiterViewController waiterViewController;

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
      waiterViewController = loader.getController();
      waiterViewController.setInitialData(parent, pendingOrders, ordersToDeliver, ordersToCook);
      parent.setWaiterController(waiterViewController);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.show();
      DateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    EventHandler<ActionEvent> eventHandler = e -> {


       stage.setTitle(df.format(new Date()));


    };
    Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play();
    }
    else if(staff.equals("kitchen")) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/KitchenView.fxml"));
      Parent root = loader.load();
      KitchenStaffView controller = loader.getController();
      controller.initialiseData(parent, parent.getWaiterController(), ordersToCook, ordersToDeliver);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.show();
      DateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    EventHandler<ActionEvent> eventHandler = e -> {
       stage.setTitle(df.format(new Date()));
    };
    Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play();
    }
    else if(staff.equals("manager")) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ManagerView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
      EventHandler<ActionEvent> eventHandler = e -> {
         stage.setTitle(df.format(new Date()));
      };
      Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
      animation.setCycleCount(Timeline.INDEFINITE);
      animation.play();
      }
    
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
      alert.showAndWait();
      Stage stage = (Stage) login.getScene().getWindow();
      stage.close();
      changeScreenLoginCorrect(event, "kitchen");
      
    }
    else if(ViewCustomerInterface.findUser(userAccount.getText(), userPwd.getText(), "manager")) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setHeaderText(null);
        alert.setContentText("Login successful");
        new LoginMessage().getMessage().put("Login", "successful");
        alert.showAndWait();
        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();
        changeScreenLoginCorrect(event, "manager");
        
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
  
  public void setInitialData(ViewCustomerInterface parent, ArrayList<Order> pendingOrders, ArrayList<Order> ordersToCook, ArrayList<Order> ordersToDeliver){
    this.parent = parent;
    this.pendingOrders = pendingOrders;
    this.ordersToCook = ordersToCook;
    this.ordersToDeliver = ordersToDeliver;
  }

}
