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
    
    //THIS BIT IS FOR ACTUALLY MAKING THE MENU ITEMS APPEAR ON SCREEN. This is only a temporary solution but it works
    
    for (int i = 0; i < 8; i++) {
      String ingr = Arrays.toString(main.mainItems[i].ingredients);
      MainListView.getItems().add("--"+main.mainItems[i].name + "--\nCalories: " + main.mainItems[i].calories + "\nIngredients: " + ingr + "\n£" + main.mainItems[i].price+"0");
    }
    
    for (int i = 0; i < 7; i++) {
      String ingr = Arrays.toString(main.sideItems[i].ingredients);
      SidesListView.getItems().add("--"+main.sideItems[i].name + "--\nCalories: " + main.sideItems[i].calories + "\nIngredients: " + ingr + "\n£" + main.sideItems[i].price+"0");
    }
    
    
    for (int i = 0; i < 6; i++) {
      String ingr = Arrays.toString(main.drinkItems[i].ingredients);
      DrinkListView.getItems().add("--"+main.drinkItems[i].name + "--\nCalories: " + main.drinkItems[i].calories + "\nIngredients: " + ingr + "\n£" + main.drinkItems[i].price+"0");
    }

    
    
  }
  

}
