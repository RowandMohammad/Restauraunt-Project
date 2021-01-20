package cs2810;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class ViewCustomerInterface extends Application{

  public static void main(String[] args) {
    launch(args);

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/CustomerView.fxml"));
    Scene scene = new Scene(root, 800, 500);
    primaryStage.setScene(scene);
    primaryStage.show();
    
  }
  
  
  
  @FXML
  private Button TestButton;

  @FXML
  void TestIsPressed(ActionEvent event) {
    System.out.println("Test");
    
  }

}
