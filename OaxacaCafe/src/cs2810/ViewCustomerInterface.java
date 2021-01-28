package cs2810;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.net.URL;
import java.util.Arrays;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ViewCustomerInterface extends Application {
  
  public static void main(String[] args) {
    launch(args);
  }




  
  MenuMain main = new MenuMain();
  
  @FXML
  private TabPane tabPane;
  
  @FXML
  private Tab drinksTab;
  
  @FXML
  private Tab sidesTab;

  @FXML
  private Tab mainTab;


	@FXML
	private ListView<ListViewItem> MainListView;

	@FXML
	private ListView<ListViewItem> SidesListView;

	@FXML
	private ListView<ListViewItem> DrinksListView;


  @FXML
  private Button StartButton;
  
  @FXML
  private Button CallingButton;

  @FXML
  private ComboBox<String> filterBoxMain;
  
  @FXML
  private ComboBox<String> filterBoxDrinks;
  
  @FXML
  private ComboBox<String> filterBoxSides;
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/CustomerView.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  
  @FXML
  public void initialize() {
    ObservableList<String> filterMainOpt =
        FXCollections.observableArrayList("Vegetarian", "Non-Vegetarian", "Spicy", "Non-Spicy", "All");
    ObservableList<String> filterSideOpt =
        FXCollections.observableArrayList("Vegetarian", "Non-Vegetarian", "Spicy", "Non-Spicy", "All");
    ObservableList<String> filterDrinkOpt =
        FXCollections.observableArrayList("Vegetarian", "Non-Vegetarian", "Fizzy", "Non-Fizzy", "All");
    
    filterBoxMain.setItems(filterMainOpt);
    filterBoxDrinks.setItems(filterDrinkOpt);
    filterBoxSides.setItems(filterSideOpt);
    setFilteringListener();
  }
  
  
  private void setFilteringListener() {
    tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
      @Override
      public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
        if (newTab.equals(mainTab)) {
          filterBoxMain.setVisible(true);
          filterBoxSides.setVisible(false);
          filterBoxDrinks.setVisible(false);
        }
        if (newTab.equals(sidesTab)) {
          filterBoxSides.setVisible(true);
          filterBoxMain.setVisible(false);
          filterBoxDrinks.setVisible(false);
        }
        if (newTab.equals(drinksTab)) {
          filterBoxDrinks.setVisible(true);
          filterBoxMain.setVisible(false);
          filterBoxSides.setVisible(false);
        }
      }
    });
  }
    /* The block of code adds a listener to the tabs so that when a tab is selected, the filtering box is hidden for the other tabs and
     * only the selected tab has its filtering button visible. Thus 3 filtering buttons are stacked upon on another.
     */
  
  
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
   
  
  public void populateMenu() throws IOException {
    main.initialiseMainItems();
    main.initiliseDrinkItems();
    main.initialiseSideItems(); 
    
    for (int i = 0; i < main.mainItems.size(); i++) {
      MainListView.getItems().add(new ListViewItem("Name:  "+main.mainItems.get(i).name, "Price:   £"+main.mainItems.get(i).price, "Calories:"+main.mainItems.get(i).calories, main.mainItems.get(i).ingredients,main.mainItems.get(i).dietaryRequirements));
    }
    for (int i = 0; i < main.sideItems.size(); i++) {
      SidesListView.getItems().add(new ListViewItem("Name:  "+main.sideItems.get(i).name, "Price:   £"+main.sideItems.get(i).price, "Calories:"+main.sideItems.get(i).calories, main.sideItems.get(i).ingredients,main.sideItems.get(i).dietaryRequirements));
    }
    for (int i = 0; i < main.drinkItems.size(); i++) {
      DrinksListView.getItems().add(new ListViewItem("Name:  "+main.drinkItems.get(i).name, "Price:   £"+main.drinkItems.get(i).price, "Calories:"+main.drinkItems.get(i).calories, main.drinkItems.get(i).ingredients, main.drinkItems.get(i).dietaryRequirements));
    }
  }
   
  
  @FXML
  public void filterChangeMain(ActionEvent event) throws IOException {
    MainListView.getItems().removeAll(MainListView.getItems());
    
    for (int i = 0; i < main.mainItems.size(); i++) {
      String dietary = Arrays.toString(main.mainItems.get(i).dietaryRequirements);
      dietary = dietary.substring(1, dietary.length() - 1);
      String[] split_diet = dietary.split(",");
  
      if (filterBoxMain.getValue() == "Vegetarian") {
        if (split_diet[0].equals("veg")) {
          MainListView.getItems().add(new ListViewItem("Name:  "+main.mainItems.get(i).name, "Price:   £"+main.mainItems.get(i).price, "Calories:"+main.mainItems.get(i).calories, main.mainItems.get(i).ingredients,main.mainItems.get(i).dietaryRequirements));
        }
      }
      else if (filterBoxMain.getValue() == "Non-Vegetarian") {
        if (split_diet[0].equals("non-veg")) {
          MainListView.getItems().add(new ListViewItem("Name:  "+main.mainItems.get(i).name, "Price:   £"+main.mainItems.get(i).price, "Calories:"+main.mainItems.get(i).calories, main.mainItems.get(i).ingredients,main.mainItems.get(i).dietaryRequirements));
        }
      }
      else if (filterBoxMain.getValue() == "Spicy") {
        if (split_diet[1].contains("spicy") && !split_diet[1].contains("non-spicy")) {
          MainListView.getItems().add(new ListViewItem("Name:  "+main.mainItems.get(i).name, "Price:   £"+main.mainItems.get(i).price, "Calories:"+main.mainItems.get(i).calories, main.mainItems.get(i).ingredients,main.mainItems.get(i).dietaryRequirements));
        }
      }
      else if (filterBoxMain.getValue() == "Non-Spicy") {
        if (split_diet[1].contains("non-spicy")) {
          MainListView.getItems().add(new ListViewItem("Name:  "+main.mainItems.get(i).name, "Price:   £"+main.mainItems.get(i).price, "Calories:"+main.mainItems.get(i).calories, main.mainItems.get(i).ingredients,main.mainItems.get(i).dietaryRequirements));
        }
      }
      else if (filterBoxMain.getValue() == "All") {
        MainListView.getItems().add(new ListViewItem("Name:  "+main.mainItems.get(i).name, "Price:   £"+main.mainItems.get(i).price, "Calories:"+main.mainItems.get(i).calories, main.mainItems.get(i).ingredients,main.mainItems.get(i).dietaryRequirements));
      }
    }
  }
  
  
  @FXML
  void filterChangeSides(ActionEvent event) {
    SidesListView.getItems().removeAll(SidesListView.getItems());
    
    for (int i = 0; i < main.sideItems.size(); i++) {
      String dietary = Arrays.toString(main.sideItems.get(i).dietaryRequirements);
      dietary = dietary.substring(1, dietary.length() - 1);
      String[] split_diet = dietary.split(",");
  
      if (filterBoxSides.getValue() == "Vegetarian") {
        if (split_diet[0].equals("veg")) {
          SidesListView.getItems().add(new ListViewItem("Name:  "+main.sideItems.get(i).name, "Price:   £"+main.sideItems.get(i).price, "Calories:"+main.sideItems.get(i).calories, main.sideItems.get(i).ingredients,main.sideItems.get(i).dietaryRequirements));        }
      }
      else if (filterBoxSides.getValue() == "Non-Vegetarian") {
        if (split_diet[0].equals("non-veg")) {
          SidesListView.getItems().add(new ListViewItem("Name:  "+main.sideItems.get(i).name, "Price:   £"+main.sideItems.get(i).price, "Calories:"+main.sideItems.get(i).calories, main.sideItems.get(i).ingredients,main.sideItems.get(i).dietaryRequirements));        }
      }
      else if (filterBoxSides.getValue() == "Spicy") {
        if (split_diet[1].contains("spicy") && !split_diet[1].contains("non-spicy")) {
          SidesListView.getItems().add(new ListViewItem("Name:  "+main.sideItems.get(i).name, "Price:   £"+main.sideItems.get(i).price, "Calories:"+main.sideItems.get(i).calories, main.sideItems.get(i).ingredients,main.sideItems.get(i).dietaryRequirements));        }
      }
      else if (filterBoxSides.getValue() == "Non-Spicy") {
        if (split_diet[1].contains("non-spicy")) {
          SidesListView.getItems().add(new ListViewItem("Name:  "+main.sideItems.get(i).name, "Price:   £"+main.sideItems.get(i).price, "Calories:"+main.sideItems.get(i).calories, main.sideItems.get(i).ingredients,main.sideItems.get(i).dietaryRequirements));        }
      }
      else if (filterBoxSides.getValue() == "All") {
        SidesListView.getItems().add(new ListViewItem("Name:  "+main.sideItems.get(i).name, "Price:   £"+main.sideItems.get(i).price, "Calories:"+main.sideItems.get(i).calories, main.sideItems.get(i).ingredients,main.sideItems.get(i).dietaryRequirements));// SidesListView.getItems().add("--" + main.sideItems.get(i).name + "--\nCalories: " + main.sideItems.get(i).calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.sideItems.get(i).price + "0");
      }
    }
  }
  
  
  @FXML
  void filterChangeDrinks(ActionEvent event) {
    DrinksListView.getItems().removeAll(DrinksListView.getItems());
    
    for (int i = 0; i < main.drinkItems.size(); i++) {
      String dietary = Arrays.toString(main.drinkItems.get(i).dietaryRequirements);
      dietary = dietary.substring(1, dietary.length() - 1);
      String[] split_diet = dietary.split(",");
  
      if (filterBoxDrinks.getValue() == "Vegetarian") {
        if (split_diet[0].equals("veg")) {
          DrinksListView.getItems().add(new ListViewItem("Name:  "+main.drinkItems.get(i).name, "Price:   £"+main.drinkItems.get(i).price, "Calories :"+main.drinkItems.get(i).calories, main.drinkItems.get(i).ingredients, main.drinkItems.get(i).dietaryRequirements));//DrinksListView.getItems().add("--" + main.drinkItems.get(i).name + "--\nCalories: " + main.drinkItems.get(i).calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.drinkItems.get(i).price + "0");
        }
      }
      else if (filterBoxDrinks.getValue() == "Non-Vegetarian") {
        if (split_diet[0].equals("non-veg")) {
          DrinksListView.getItems().add(new ListViewItem("Name:  "+main.drinkItems.get(i).name, "Price:   £"+main.drinkItems.get(i).price, "Calories:"+main.drinkItems.get(i).calories, main.drinkItems.get(i).ingredients, main.drinkItems.get(i).dietaryRequirements));// DrinksListView.getItems().add("--" + main.drinkItems.get(i).name + "--\nCalories: " + main.drinkItems.get(i).calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.drinkItems.get(i).price + "0");
        }
      }
      else if (filterBoxDrinks.getValue() == "Fizzy") {
        if (split_diet[1].contains("fizzy") && !split_diet[1].contains("non-fizzy")) {
          DrinksListView.getItems().add(new ListViewItem("Name:  "+main.drinkItems.get(i).name, "Price:   £"+main.drinkItems.get(i).price, "Calories:"+main.drinkItems.get(i).calories, main.drinkItems.get(i).ingredients, main.drinkItems.get(i).dietaryRequirements));        }
      }
      else if (filterBoxDrinks.getValue() == "Non-Fizzy") {
        if (split_diet[1].contains("non-fizzy")) {
          DrinksListView.getItems().add(new ListViewItem("Name:  "+main.drinkItems.get(i).name, "Price:   £"+main.drinkItems.get(i).price, "Calories:"+main.drinkItems.get(i).calories, main.drinkItems.get(i).ingredients, main.drinkItems.get(i).dietaryRequirements));        }
      }
      else if (filterBoxDrinks.getValue() == "All") {
        DrinksListView.getItems().add(new ListViewItem("Name:  "+main.drinkItems.get(i).name, "Price:   £"+main.drinkItems.get(i).price, "Calories:"+main.drinkItems.get(i).calories, main.drinkItems.get(i).ingredients, main.drinkItems.get(i).dietaryRequirements));      }
    }
  }
}
  