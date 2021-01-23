package cs2810;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
  private ComboBox<String> filterBox;
  
  @FXML
  public void initialize() {
    ObservableList<String> filterOptions = FXCollections.observableArrayList("Vegetarian", "Non-Vegetarian", "Spicy", "Mild");
    filterBox.setItems(filterOptions);
  }
  
  @FXML
  void StartButtonPressed(ActionEvent event) throws IOException {
    StartButton.setDisable(true);
    StartButton.setVisible(false);
    populateMenu();
  }
  
  @FXML
  public void filterChange(ActionEvent event) {
    if (filterBox.getValue() == "Vegetarian") {
      
    }
    if (filterBox.getValue() == "Non-Vegetarian") {
      
    }
    if (filterBox.getValue() == "Spicy") {
      
    }
    if (filterBox.getValue() == "Mild") {
      
    }
    
  }
  

  
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/CustomerView.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
                                
    
    URL url = this.getClass().getClassLoader().getResource("res/12025.mp3");
	  System.out.println(url.toExternalForm());
	  Media media = new Media(url.toExternalForm());
	  MediaPlayer mp = new MediaPlayer(media);
    Button b = (Button)root.lookup("#CallingButton");
    System.out.println(b.getText());
    
    b.setOnAction(new EventHandler<ActionEvent>()
    {
    	public void handle(ActionEvent event)
    	{
    		 mp.play();
    	}
    });
  
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
