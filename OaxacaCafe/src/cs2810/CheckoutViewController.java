package cs2810;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CheckoutViewController extends Application {

  @FXML
  void changeScreenButtonPushed(ActionEvent event) throws IOException {
    Parent menuViewParent = FXMLLoader.load(getClass().getResource("/CustomerView.fxml"));
    Scene menuViewScene = new Scene(menuViewParent, 800, 800);

    // This line gets the Stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(menuViewScene);
    window.show();
  }

  @Override
  public void start(Stage args) throws Exception {
    // TODO Auto-generated method stub

  }
}

