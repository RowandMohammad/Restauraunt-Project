package cs2810;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;




public class ViewCustomerInterface extends Application{
  
  
  public static void main(String[] args) {
    launch(args);

  }
  
  MenuMain main = new MenuMain();
  

  
  @FXML
  private ListView<String> MainListView;

  @FXML
  private ListView<String> SidesListView;

  @FXML
  private ListView<String> DrinkListView;
  
  @FXML
  private Button StartButton;
  
  @FXML
  private Button CallingButton;
  
  
  @FXML
  void waiterButtonPressed(ActionEvent event) {
    URL url = this.getClass().getClassLoader().getResource("res/12025.mp3");
    Media media = new Media(url.toExternalForm());
    MediaPlayer mp = new MediaPlayer(media);
    mp.play();
  }
  
  @FXML
  void StartButtonPressed(ActionEvent event) throws IOException {
    StartButton.setDisable(true);
    StartButton.setVisible(false);
    populateMenu();
    
  }

  
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/CustomerView.fxml"));
    Scene scene = new Scene(root, 400, 680);
    primaryStage.setScene(scene);
    primaryStage.show();


  }
  
  
  
  public void populateMenu() throws IOException{
    main.initialiseMainItems();
    main.initiliseDrinkItems();
    main.initialiseSideItems(); 
    

    for (int i = 0; i < 8; i++) {
      String ingr = Arrays.toString(main.mainItems.get(i).ingredients);
      MainListView.getItems().add("--"+main.mainItems.get(i).name + "--\nCalories: " + main.mainItems.get(i).calories + "\nIngredients: " + ingr + "\n£" + main.mainItems.get(i).price+"0");
    }
    
    for (int i = 0; i < 7; i++) {
      String ingr = Arrays.toString(main.sideItems.get(i).ingredients);
      SidesListView.getItems().add("--"+main.sideItems.get(i).name + "--\nCalories: " + main.sideItems.get(i).calories + "\nIngredients: " + ingr + "\n£" + main.sideItems.get(i).price+"0");
    }
    
    
    for (int i = 0; i < 6; i++) {
      String ingr = Arrays.toString(main.drinkItems.get(i).ingredients);
      DrinkListView.getItems().add("--"+main.drinkItems.get(i).name + "--\nCalories: " + main.drinkItems.get(i).calories + "\nIngredients: " + ingr + "\n£" + main.drinkItems.get(i).price+"0");
    }

    
    
  }
  

}
