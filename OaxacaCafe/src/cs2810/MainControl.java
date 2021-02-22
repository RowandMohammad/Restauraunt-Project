package cs2810;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MainControl extends Application {
  static ArrayList<kitchenStaff> KS = new ArrayList<kitchenStaff>();
  static ArrayList<waiterStaff> WS = new ArrayList<waiterStaff>();

  public static void main(String[] args) throws URISyntaxException, SQLException {
    StaffInitialization();
    DatabaseInitialisation.main(args);
    launch(args);
  }



  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/CustomerView.fxml"));
    Scene scene = new Scene(root);
    scene.getStylesheets()
        .add(getClass().getClassLoader().getResource("styling/style.css").toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.show();
    primaryStage.setResizable(false);
  }

  static void StaffInitialization() {
    for (int x = 0; x < 10; x++) {
      KS.add(new kitchenStaff());
    }
    for (int y = 0; y < 10; y++) {
      WS.add(new waiterStaff());
    }
  }



}
