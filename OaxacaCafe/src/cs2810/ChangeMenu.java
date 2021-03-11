package cs2810;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
  private ListView<ListPane> MainListView;

  @FXML
  private ListView<ListPane> SidesListView;

  @FXML
  private ListView<ListPane> DrinksListView;


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
          .add(new ListPane(mainItems.get(i).name, "£" + mainItems.get(i).price + "0",
             mainItems.get(i).ingredients,
              mainItems.get(i).dietaryRequirements));
    }
    for (int i = 0; i < sideItems.size(); i++) {
      SidesListView.getItems()
          .add(new ListPane(sideItems.get(i).name, "£" + sideItems.get(i).price + "0",
              sideItems.get(i).ingredients,
              sideItems.get(i).dietaryRequirements));
    }
    for (int i = 0; i < drinkItems.size(); i++) {
      DrinksListView.getItems()
          .add(new ListPane(drinkItems.get(i).name, "£" + drinkItems.get(i).price + "0",
               drinkItems.get(i).ingredients,
              drinkItems.get(i).dietaryRequirements));
    }
  }

 

  @FXML
  void logout(ActionEvent event) throws IOException {
	  LoginMessage.getMessage().clear();
	  Stage stage = (Stage) logoutbutton.getScene().getWindow();
	  stage.close();

  }

}
