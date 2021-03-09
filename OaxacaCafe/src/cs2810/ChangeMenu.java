package cs2810;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class ChangeMenu {


  String select = "";

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
  private ComboBox<String> filterBoxMain;

  @FXML
  private ComboBox<String> filterBoxDrinks;

  @FXML
  private ComboBox<String> filterBoxSides;

  @FXML
  private Button StartButton;

  @FXML
  private Button logoutbutton;
  


  @FXML
  void StartButtonPressed(ActionEvent event) throws IOException {
    StartButton.setDisable(true);
    StartButton.setVisible(false);
    populateMenu();
  }

@FXML
  public void initialize() {
    ObservableList<String> filterMainOpt = FXCollections.observableArrayList("Vegetarian",
        "Non-Vegetarian", "Spicy", "Non-Spicy", "All");
    ObservableList<String> filterSideOpt = FXCollections.observableArrayList("Vegetarian",
        "Non-Vegetarian", "Spicy", "Non-Spicy", "All");
    ObservableList<String> filterDrinkOpt = FXCollections.observableArrayList("Vegetarian",
        "Non-Vegetarian", "Fizzy", "Non-Fizzy", "All");

    filterBoxMain.setItems(filterMainOpt);
    filterBoxDrinks.setItems(filterDrinkOpt);
    filterBoxSides.setItems(filterSideOpt);
    setFilteringListener();
  }

  private void setFilteringListener() {
    tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
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

  ArrayList<Menu_Item> mainItems;
  ArrayList<Menu_Item> drinkItems;
  ArrayList<Menu_Item> sideItems;

  public void populateMenu() throws IOException {
    new MenuMain();
    mainItems = MenuMain.initialiseMainItems();
    new MenuMain();
    drinkItems = MenuMain.initiliseDrinkItems();
    new MenuMain();
    sideItems = MenuMain.initialiseSideItems();

    for (int i = 0; i < mainItems.size(); i++) {
      MainListView.getItems()
          .add(new ListViewItem(mainItems.get(i).name, "£" + mainItems.get(i).price + "0",
              mainItems.get(i).calories + "", mainItems.get(i).ingredients,
              mainItems.get(i).dietaryRequirements));
    }
    for (int i = 0; i < sideItems.size(); i++) {
      SidesListView.getItems()
          .add(new ListViewItem(sideItems.get(i).name, "£" + sideItems.get(i).price + "0",
              sideItems.get(i).calories + "", sideItems.get(i).ingredients,
              sideItems.get(i).dietaryRequirements));
    }
    for (int i = 0; i < drinkItems.size(); i++) {
      DrinksListView.getItems()
          .add(new ListViewItem(drinkItems.get(i).name, "£" + drinkItems.get(i).price + "0",
              drinkItems.get(i).calories + "", drinkItems.get(i).ingredients,
              drinkItems.get(i).dietaryRequirements));
    }
  }

  @FXML
  public void filterChangeMain(ActionEvent event) throws IOException {
    MainListView.getItems().removeAll(MainListView.getItems());

    for (int i = 0; i < mainItems.size(); i++) {
      String dietary = Arrays.toString(mainItems.get(i).dietaryRequirements);
      dietary = dietary.substring(1, dietary.length() - 1);
      String[] split_diet = dietary.split(",");

      if (filterBoxMain.getValue() == "Vegetarian") {
        if (split_diet[0].equals("veg")) {
          MainListView.getItems()
              .add(new ListViewItem(mainItems.get(i).name, "£" + mainItems.get(i).price + "0",
                  mainItems.get(i).calories + "", mainItems.get(i).ingredients,
                  mainItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxMain.getValue() == "Non-Vegetarian") {
        if (split_diet[0].equals("non-veg")) {
          MainListView.getItems()
              .add(new ListViewItem(mainItems.get(i).name, "£" + mainItems.get(i).price + "0",
                  mainItems.get(i).calories + "", mainItems.get(i).ingredients,
                  mainItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxMain.getValue() == "Spicy") {
        if (split_diet[1].contains("spicy") && !split_diet[1].contains("non-spicy")) {
          MainListView.getItems()
              .add(new ListViewItem(mainItems.get(i).name, "£" + mainItems.get(i).price + "0",
                  mainItems.get(i).calories + "", mainItems.get(i).ingredients,
                  mainItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxMain.getValue() == "Non-Spicy") {
        if (split_diet[1].contains("non-spicy")) {
          MainListView.getItems()
              .add(new ListViewItem(mainItems.get(i).name, "£" + mainItems.get(i).price + "0",
                  mainItems.get(i).calories + "", mainItems.get(i).ingredients,
                  mainItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxMain.getValue() == "All") {
        MainListView.getItems()
            .add(new ListViewItem(mainItems.get(i).name, "£" + mainItems.get(i).price + "0",
                mainItems.get(i).calories + "", mainItems.get(i).ingredients,
                mainItems.get(i).dietaryRequirements));
      } else if (filterBoxMain.getValue() == null) {
          MainListView.getItems()
          .add(new ListViewItem(mainItems.get(i).name, "£" + mainItems.get(i).price + "0",
              mainItems.get(i).calories + "", mainItems.get(i).ingredients,
              mainItems.get(i).dietaryRequirements));
      }
    }
  }

  @FXML
  void filterChangeSides(ActionEvent event) {
    SidesListView.getItems().removeAll(SidesListView.getItems());

    for (int i = 0; i < sideItems.size(); i++) {
      String dietary = Arrays.toString(sideItems.get(i).dietaryRequirements);
      dietary = dietary.substring(1, dietary.length() - 1);
      String[] split_diet = dietary.split(",");

      if (filterBoxSides.getValue() == "Vegetarian") {
        if (split_diet[0].equals("veg")) {
          SidesListView.getItems()
              .add(new ListViewItem(sideItems.get(i).name, "£" + sideItems.get(i).price + "0",
                  sideItems.get(i).calories + "", sideItems.get(i).ingredients,
                  sideItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxSides.getValue() == "Non-Vegetarian") {
        if (split_diet[0].equals("non-veg")) {
          SidesListView.getItems()
              .add(new ListViewItem(sideItems.get(i).name, "£" + sideItems.get(i).price + "0",
                  sideItems.get(i).calories + "", sideItems.get(i).ingredients,
                  sideItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxSides.getValue() == "Spicy") {
        if (split_diet[1].contains("spicy") && !split_diet[1].contains("non-spicy")) {
          SidesListView.getItems()
              .add(new ListViewItem(sideItems.get(i).name, "£" + sideItems.get(i).price + "0",
                  sideItems.get(i).calories + "", sideItems.get(i).ingredients,
                  sideItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxSides.getValue() == "Non-Spicy") {
        if (split_diet[1].contains("non-spicy")) {
          SidesListView.getItems()
              .add(new ListViewItem(sideItems.get(i).name, "£" + sideItems.get(i).price + "0",
                  sideItems.get(i).calories + "", sideItems.get(i).ingredients,
                  sideItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxSides.getValue() == "All") {
        SidesListView.getItems()
            .add(new ListViewItem(sideItems.get(i).name, "£" + sideItems.get(i).price + "0",
                sideItems.get(i).calories + "", sideItems.get(i).ingredients,
                sideItems.get(i).dietaryRequirements));
      } else if (filterBoxSides.getValue() == null) {
          SidesListView.getItems()
          .add(new ListViewItem(sideItems.get(i).name, "£" + sideItems.get(i).price + "0",
              sideItems.get(i).calories + "", sideItems.get(i).ingredients,
              sideItems.get(i).dietaryRequirements));
      }
    }
  }

  @FXML
  void filterChangeDrinks(ActionEvent event) {
    DrinksListView.getItems().removeAll(DrinksListView.getItems());

    for (int i = 0; i < drinkItems.size(); i++) {
      String dietary = Arrays.toString(drinkItems.get(i).dietaryRequirements);
      dietary = dietary.substring(1, dietary.length() - 1);
      String[] split_diet = dietary.split(",");

      if (filterBoxDrinks.getValue() == "Vegetarian") {
        if (split_diet[0].equals("veg")) {
          DrinksListView.getItems()
              .add(new ListViewItem(drinkItems.get(i).name, "£" + drinkItems.get(i).price + "0",
                  drinkItems.get(i).calories + "", drinkItems.get(i).ingredients,
                  drinkItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxDrinks.getValue() == "Non-Vegetarian") {
        if (split_diet[0].equals("non-veg")) {
          DrinksListView.getItems()
              .add(new ListViewItem(drinkItems.get(i).name, "£" + drinkItems.get(i).price + "0",
                  "" + drinkItems.get(i).calories, drinkItems.get(i).ingredients,
                  drinkItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxDrinks.getValue() == "Fizzy") {
        if (split_diet[1].contains("fizzy") && !split_diet[1].contains("non-fizzy")) {
          DrinksListView.getItems()
              .add(new ListViewItem(drinkItems.get(i).name, "£" + drinkItems.get(i).price + "0",
                  "" + drinkItems.get(i).calories, drinkItems.get(i).ingredients,
                  drinkItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxDrinks.getValue() == "Non-Fizzy") {
        if (split_diet[1].contains("non-fizzy")) {
          DrinksListView.getItems()
              .add(new ListViewItem(drinkItems.get(i).name, "£" + drinkItems.get(i).price + "0",
                  "" + drinkItems.get(i).calories, drinkItems.get(i).ingredients,
                  drinkItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxDrinks.getValue() == "All") {
        DrinksListView.getItems()
            .add(new ListViewItem(drinkItems.get(i).name, "£" + drinkItems.get(i).price + "0",
                "" + drinkItems.get(i).calories, drinkItems.get(i).ingredients,
                drinkItems.get(i).dietaryRequirements));
      } else if (filterBoxDrinks.getValue()== null) {
          DrinksListView.getItems()
          .add(new ListViewItem(drinkItems.get(i).name, "£" + drinkItems.get(i).price + "0",
              "" + drinkItems.get(i).calories, drinkItems.get(i).ingredients,
              drinkItems.get(i).dietaryRequirements));
      }
    }
  }

  @FXML
  void logout(ActionEvent event) throws IOException {
	  LoginMessage.getMessage().clear();
	  Stage stage = (Stage) logoutbutton.getScene().getWindow();
	  stage.close();

  }

}
