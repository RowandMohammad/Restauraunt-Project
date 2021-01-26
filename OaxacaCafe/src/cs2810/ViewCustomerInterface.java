package cs2810;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
  private ListView<String> MainListView;

  @FXML
  private ListView<String> SidesListView;

  @FXML
  private ListView<String> DrinksListView;

  @FXML
  private Button StartButton;

  @FXML
  private ComboBox<String> filterBoxMain;
  
  @FXML
  private ComboBox<String> filterBoxDrinks;
  
  @FXML
  private ComboBox<String> filterBoxSides;

  @FXML
  public void initialize() {
    ObservableList<String> filterMainOpt =
        FXCollections.observableArrayList("Vegetarian", "Non-Vegetarian", "Spicy", "Non-Spicy", "All");
    ObservableList<String> filterSideOpt =
        FXCollections.observableArrayList("Vegetarian", "Non-Vegetarian", "Spicy", "Non-Spicy", "All");
    ObservableList<String> filterDrinkOpt =
        FXCollections.observableArrayList("Vegetarian", "Non-Vegetarian", "All");
    
    filterBoxMain.setItems(filterMainOpt);
    filterBoxDrinks.setItems(filterDrinkOpt);
    filterBoxSides.setItems(filterSideOpt);
    
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

    // THIS BIT IS FOR ACTUALLY MAKING THE MENU ITEMS APPEAR ON SCREEN. This is only a temporary
    // solution but it works

    for (int i = 0; i < main.mainItems.length; i++) {
      String ingr = Arrays.toString(main.mainItems[i].ingredients);
      String dietary = Arrays.toString(main.mainItems[i].dietaryRequirements);
      dietary = dietary.substring(1, dietary.length() - 1);
      MainListView.getItems()
          .add("--" + main.mainItems[i].name + "--\nCalories: " + main.mainItems[i].calories
              + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£"
              + main.mainItems[i].price + "0");
    }

    for (int i = 0; i < main.sideItems.length; i++) {
      String ingr = Arrays.toString(main.sideItems[i].ingredients);
      String dietary = Arrays.toString(main.sideItems[i].dietaryRequirements);
      dietary = dietary.substring(1, dietary.length() - 1);
      SidesListView.getItems()
          .add("--" + main.sideItems[i].name + "--\nCalories: " + main.sideItems[i].calories
              + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£"
              + main.sideItems[i].price + "0");
    }


    for (int i = 0; i < main.drinkItems.length; i++) {
      String ingr = Arrays.toString(main.drinkItems[i].ingredients);
      String dietary = Arrays.toString(main.drinkItems[i].dietaryRequirements);
      dietary = dietary.substring(1, dietary.length() - 1);
      DrinksListView.getItems()
          .add("--" + main.drinkItems[i].name + "--\nCalories: " + main.drinkItems[i].calories
              + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£"
              + main.drinkItems[i].price + "0");
    }
  }
  
  
  @FXML
  public void filterChangeMain(ActionEvent event) throws IOException {
    MainListView.getItems().removeAll(MainListView.getItems());
    
    for (int i = 0; i < main.mainItems.length; i++) {
      String ingr = Arrays.toString(main.mainItems[i].ingredients);
      String dietary = Arrays.toString(main.mainItems[i].dietaryRequirements);
      dietary = dietary.substring(1, dietary.length() - 1);
      String[] split_diet = dietary.split(",");
  
      if (filterBoxMain.getValue() == "Vegetarian") {
        if (split_diet[0].equals("veg")) {
          MainListView.getItems().add("--" + main.mainItems[i].name + "--\nCalories: " + main.mainItems[i].calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.mainItems[i].price + "0");
        }
      }
      else if (filterBoxMain.getValue() == "Non-Vegetarian") {
        if (split_diet[0].equals("non-veg")) {
          MainListView.getItems().add("--" + main.mainItems[i].name + "--\nCalories: " + main.mainItems[i].calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.mainItems[i].price + "0");
        }
      }
      else if (filterBoxMain.getValue() == "Spicy") {
        if (split_diet[1].contains("spicy") && !(split_diet[1].contains("non-spicy"))) {
          MainListView.getItems().add("--" + main.mainItems[i].name + "--\nCalories: " + main.mainItems[i].calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.mainItems[i].price + "0");
        }
      }
      else if (filterBoxMain.getValue() == "Non-Spicy") {
        if (split_diet[1].contains("non-spicy")) {
          MainListView.getItems().add("--" + main.mainItems[i].name + "--\nCalories: " + main.mainItems[i].calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.mainItems[i].price + "0");
        }
      }
      else if (filterBoxMain.getValue() == "All") {
        MainListView.getItems().add("--" + main.mainItems[i].name + "--\nCalories: " + main.mainItems[i].calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.mainItems[i].price + "0");
      }
    }
  }
  
  
  @FXML
  void filterChangeSides(ActionEvent event) {
    SidesListView.getItems().removeAll(SidesListView.getItems());
    
    for (int i = 0; i < main.sideItems.length; i++) {
      String ingr = Arrays.toString(main.sideItems[i].ingredients);
      String dietary = Arrays.toString(main.sideItems[i].dietaryRequirements);
      dietary = dietary.substring(1, dietary.length() - 1);
      String[] split_diet = dietary.split(",");
  
      if (filterBoxSides.getValue() == "Vegetarian") {
        if (split_diet[0].equals("veg")) {
          SidesListView.getItems().add("--" + main.sideItems[i].name + "--\nCalories: " + main.sideItems[i].calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.sideItems[i].price + "0");
        }
      }
      else if (filterBoxSides.getValue() == "Non-Vegetarian") {
        if (split_diet[0].equals("non-veg")) {
          SidesListView.getItems().add("--" + main.sideItems[i].name + "--\nCalories: " + main.sideItems[i].calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.sideItems[i].price + "0");
        }
      }
      else if (filterBoxSides.getValue() == "Spicy") {
        if (split_diet[1].contains("spicy") && !(split_diet[1].contains("non-spicy"))) {
          SidesListView.getItems().add("--" + main.sideItems[i].name + "--\nCalories: " + main.sideItems[i].calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.sideItems[i].price + "0");
        }
      }
      else if (filterBoxSides.getValue() == "Non-Spicy") {
        if (split_diet[1].contains("non-spicy")) {
          SidesListView.getItems().add("--" + main.sideItems[i].name + "--\nCalories: " + main.sideItems[i].calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.sideItems[i].price + "0");
        }
      }
      else if (filterBoxSides.getValue() == "All") {
        SidesListView.getItems().add("--" + main.sideItems[i].name + "--\nCalories: " + main.sideItems[i].calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.sideItems[i].price + "0");
      }
    }
  }
  
  @FXML
  void filterChangeDrinks(ActionEvent event) {
    DrinksListView.getItems().removeAll(DrinksListView.getItems());
    
    for (int i = 0; i < main.drinkItems.length; i++) {
      String ingr = Arrays.toString(main.drinkItems[i].ingredients);
      String dietary = Arrays.toString(main.drinkItems[i].dietaryRequirements);
      dietary = dietary.substring(1, dietary.length() - 1);
      String[] split_diet = dietary.split(",");
  
      if (filterBoxDrinks.getValue() == "Vegetarian") {
        if (split_diet[0].equals("veg")) {
          DrinksListView.getItems().add("--" + main.drinkItems[i].name + "--\nCalories: " + main.drinkItems[i].calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.drinkItems[i].price + "0");
        }
      }
      else if (filterBoxDrinks.getValue() == "Non-Vegetarian") {
        if (split_diet[0].equals("non-veg")) {
          DrinksListView.getItems().add("--" + main.drinkItems[i].name + "--\nCalories: " + main.drinkItems[i].calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.drinkItems[i].price + "0");
        }
      }
      else if (filterBoxDrinks.getValue() == "All") {
        DrinksListView.getItems().add("--" + main.drinkItems[i].name + "--\nCalories: " + main.drinkItems[i].calories + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n£" + main.drinkItems[i].price + "0");
      }
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
    Button b = (Button) root.lookup("#CallingButton");
    System.out.println(b.getText());
    
    b.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        mp.play();
      }
    });
  }


}
