package cs2810;

import java.net.URISyntaxException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MainControl extends Application{
  


	public static void main(String[] args) throws URISyntaxException, SQLException {
		DatabaseInitialisation.main(args);
		launch(args);
	}
		


	@Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/CustomerView.fxml"));
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getClassLoader().getResource("styling/style.css").toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.show();
    primaryStage.setResizable(false);
    

	}



}
