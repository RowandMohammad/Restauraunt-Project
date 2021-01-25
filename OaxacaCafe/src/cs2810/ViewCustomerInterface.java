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

public class ViewCustomerInterface extends Application {


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
    ObservableList<String> filterOptions =
        FXCollections.observableArrayList("Vegetarian", "Non-Vegetarian", "Spicy", "Non-Spicy", "All");
    filterBox.setItems(filterOptions);
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
              + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
              + main.mainItems[i].price + "0");
    }

    for (int i = 0; i < main.sideItems.length; i++) {
      String ingr = Arrays.toString(main.sideItems[i].ingredients);
      String dietary = Arrays.toString(main.sideItems[i].dietaryRequirements);
      dietary = dietary.substring(1, dietary.length() - 1);
      SidesListView.getItems()
          .add("--" + main.sideItems[i].name + "--\nCalories: " + main.sideItems[i].calories
              + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
              + main.sideItems[i].price + "0");
    }


    for (int i = 0; i < main.drinkItems.length; i++) {
      String ingr = Arrays.toString(main.drinkItems[i].ingredients);
      String dietary = Arrays.toString(main.drinkItems[i].dietaryRequirements);
      dietary = dietary.substring(1, dietary.length() - 1);
      DrinkListView.getItems()
          .add("--" + main.drinkItems[i].name + "--\nCalories: " + main.drinkItems[i].calories
              + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
              + main.drinkItems[i].price + "0");
    }
  }

  
  @FXML
  //This solution works however needs refactoring to remove long method smell and alternative solution rather than catching out of bounds
  public void filterChange(ActionEvent event) throws IOException {
    MainListView.getItems().removeAll(MainListView.getItems());
    SidesListView.getItems().removeAll(SidesListView.getItems());
    DrinkListView.getItems().removeAll(DrinkListView.getItems());
    
    try {
      for (int i = 0; i < main.mainItems.length; i++) {
        String ingr = Arrays.toString(main.mainItems[i].ingredients);
        String dietary = Arrays.toString(main.mainItems[i].dietaryRequirements);
        dietary = dietary.substring(1, dietary.length() - 1);
        String[] split_diet = dietary.split(",");
  
        if (filterBox.getValue() == "Vegetarian") {
          if (split_diet[0].equals("veg")) {
            MainListView.getItems()
                .add("--" + main.mainItems[i].name + "--\nCalories: " + main.mainItems[i].calories
                    + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
                    + main.mainItems[i].price + "0");
            SidesListView.getItems()
            .add("--" + main.sideItems[i].name + "--\nCalories: " + main.sideItems[i].calories
                + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
                + main.sideItems[i].price + "0");
            DrinkListView.getItems()
            .add("--" + main.drinkItems[i].name + "--\nCalories: " + main.drinkItems[i].calories
                + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
                + main.drinkItems[i].price + "0");
          }
        }
        else if (filterBox.getValue() == "Non-Vegetarian") {
          if (split_diet[0].equals("non-veg")) {
            MainListView.getItems()
                .add("--" + main.mainItems[i].name + "--\nCalories: " + main.mainItems[i].calories
                    + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
                    + main.mainItems[i].price + "0");
            SidesListView.getItems()
            .add("--" + main.sideItems[i].name + "--\nCalories: " + main.sideItems[i].calories
                + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
                + main.sideItems[i].price + "0");
            DrinkListView.getItems()
            .add("--" + main.drinkItems[i].name + "--\nCalories: " + main.drinkItems[i].calories
                + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
                + main.drinkItems[i].price + "0");
          }
        }
        else if (filterBox.getValue() == "Spicy") {
          if (split_diet[1].contains("spicy") && !(split_diet[1].contains("non-spicy"))) {
            MainListView.getItems()
                .add("--" + main.mainItems[i].name + "--\nCalories: " + main.mainItems[i].calories
                    + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
                    + main.mainItems[i].price + "0");
            SidesListView.getItems()
            .add("--" + main.sideItems[i].name + "--\nCalories: " + main.sideItems[i].calories
                + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
                + main.sideItems[i].price + "0");
          }
        }
        else if (filterBox.getValue() == "Non-Spicy") {
          if (split_diet[1].contains("non-spicy")) {
            MainListView.getItems()
                .add("--" + main.mainItems[i].name + "--\nCalories: " + main.mainItems[i].calories
                    + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
                    + main.mainItems[i].price + "0");
            SidesListView.getItems()
            .add("--" + main.sideItems[i].name + "--\nCalories: " + main.sideItems[i].calories
                + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
                + main.sideItems[i].price + "0");
          }
        }
        else if (filterBox.getValue() == "All") {
          MainListView.getItems()
          .add("--" + main.mainItems[i].name + "--\nCalories: " + main.mainItems[i].calories
              + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
              + main.mainItems[i].price + "0");
          SidesListView.getItems()
          .add("--" + main.sideItems[i].name + "--\nCalories: " + main.sideItems[i].calories
              + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
              + main.sideItems[i].price + "0");
          DrinkListView.getItems()
          .add("--" + main.drinkItems[i].name + "--\nCalories: " + main.drinkItems[i].calories
              + "\nIngredients: " + ingr + "\nDietary Requirements: " + dietary + "\n�"
              + main.drinkItems[i].price + "0");
        }
      }
    }
    catch (ArrayIndexOutOfBoundsException e) { 
      return;
    }
    /* This catch is needed as the three arrays have different amount of items in each one thus its more convenient to
     * use the length of the largest array being the MainListView and when iterating through the DrinkListView/SidesListView,
     * throw the out of bounds exception when exceeding its limit.
     */
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
