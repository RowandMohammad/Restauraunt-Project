package cs2810;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Date;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



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
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/CustomerView.fxml"));
	Parent root = loader.load();
	ViewCustomerInterface controller = loader.getController();
    controller.setParent(this);
    Scene scene = new Scene(root);
    
    scene.getStylesheets()
        .add(getClass().getClassLoader().getResource("styling/style.css").toExternalForm());
    
    DateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    
    EventHandler<ActionEvent> eventHandler = e -> {

       primaryStage.setTitle(df.format(new Date()));


    };
    Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play();
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
