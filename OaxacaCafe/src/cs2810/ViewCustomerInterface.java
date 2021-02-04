package cs2810;

import static java.lang.System.err;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ViewCustomerInterface extends Application {

  static ArrayList<User> list = new ArrayList<User>();

  public static void main(String[] args) {
    LoadUser();
    launch(args);
  }

  String select = "";


  MenuMain main = new MenuMain();
  ArrayList<Menu_Item> basketItems = new ArrayList<Menu_Item>();

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
  private ListView<String> BasketView;

  @FXML
  private ComboBox<String> filterBoxMain;

  @FXML
  private ComboBox<String> filterBoxDrinks;

  @FXML
  private ComboBox<String> filterBoxSides;

  @FXML
  private Spinner<Integer> quantitySpinner;// Quantity select
  // Spinner Value Factory
  final int initialValue = 1;
  SpinnerValueFactory<Integer> svf =
      new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, initialValue);

  @FXML
  private Label totalPrice;

  // Button to add items to order
  @FXML
  private Button addItemBtn;

  @FXML
  private Button checkoutBtn;

  @FXML
  private Button StartButton;

  @FXML
  private Button CallingButton;

  // Handles button click to call waiter
  @FXML
  void waiterButtonPressed(ActionEvent event) {
    URL url = this.getClass().getClassLoader().getResource("res/12025.mp3");
    Media media = new Media(url.toExternalForm());
    MediaPlayer mp = new MediaPlayer(media);
    mp.play();
  }

  // Handles starting the menu
  @FXML
  void StartButtonPressed(ActionEvent event) throws IOException {
    StartButton.setDisable(true);
    StartButton.setVisible(false);
    populateMenu();
    quantitySpinner.setValueFactory(svf);

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/CustomerView.fxml"));
    Scene scene = new Scene(root, 800, 800);
    primaryStage.setScene(scene);
    primaryStage.show();

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

  // Initialises the Menu list for Mains, Sides and Drinks
  public void populateMenu() throws IOException {
    main.initialiseMainItems();
    main.initiliseDrinkItems();
    main.initialiseSideItems();

    for (int i = 0; i < main.mainItems.size(); i++) {
      MainListView.getItems()
          .add(new ListViewItem(main.mainItems.get(i).name, "£" + main.mainItems.get(i).price + "0",
              main.mainItems.get(i).calories + "", main.mainItems.get(i).ingredients,
              main.mainItems.get(i).dietaryRequirements));
    }
    for (int i = 0; i < main.sideItems.size(); i++) {
      SidesListView.getItems()
          .add(new ListViewItem(main.sideItems.get(i).name, "£" + main.sideItems.get(i).price + "0",
              main.sideItems.get(i).calories + "", main.sideItems.get(i).ingredients,
              main.sideItems.get(i).dietaryRequirements));
    }
    for (int i = 0; i < main.drinkItems.size(); i++) {
      DrinksListView.getItems()
          .add(new ListViewItem(main.drinkItems.get(i).name,
              "£" + main.drinkItems.get(i).price + "0", main.drinkItems.get(i).calories + "",
              main.drinkItems.get(i).ingredients, main.drinkItems.get(i).dietaryRequirements));
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
          MainListView.getItems()
              .add(new ListViewItem(main.mainItems.get(i).name,
                  "£" + main.mainItems.get(i).price + "0", main.mainItems.get(i).calories + "",
                  main.mainItems.get(i).ingredients, main.mainItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxMain.getValue() == "Non-Vegetarian") {
        if (split_diet[0].equals("non-veg")) {
          MainListView.getItems()
              .add(new ListViewItem(main.mainItems.get(i).name,
                  "£" + main.mainItems.get(i).price + "0", main.mainItems.get(i).calories + "",
                  main.mainItems.get(i).ingredients, main.mainItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxMain.getValue() == "Spicy") {
        if (split_diet[1].contains("spicy") && !split_diet[1].contains("non-spicy")) {
          MainListView.getItems()
              .add(new ListViewItem(main.mainItems.get(i).name,
                  "£" + main.mainItems.get(i).price + "0", main.mainItems.get(i).calories + "",
                  main.mainItems.get(i).ingredients, main.mainItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxMain.getValue() == "Non-Spicy") {
        if (split_diet[1].contains("non-spicy")) {
          MainListView.getItems()
              .add(new ListViewItem(main.mainItems.get(i).name,
                  "£" + main.mainItems.get(i).price + "0", main.mainItems.get(i).calories + "",
                  main.mainItems.get(i).ingredients, main.mainItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxMain.getValue() == "All") {
        MainListView.getItems()
            .add(new ListViewItem(main.mainItems.get(i).name,
                "£" + main.mainItems.get(i).price + "0", main.mainItems.get(i).calories + "",
                main.mainItems.get(i).ingredients, main.mainItems.get(i).dietaryRequirements));
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
          SidesListView.getItems()
              .add(new ListViewItem(main.sideItems.get(i).name,
                  "£" + main.sideItems.get(i).price + "0", main.sideItems.get(i).calories + "",
                  main.sideItems.get(i).ingredients, main.sideItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxSides.getValue() == "Non-Vegetarian") {
        if (split_diet[0].equals("non-veg")) {
          SidesListView.getItems()
              .add(new ListViewItem(main.sideItems.get(i).name,
                  "£" + main.sideItems.get(i).price + "0", main.sideItems.get(i).calories + "",
                  main.sideItems.get(i).ingredients, main.sideItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxSides.getValue() == "Spicy") {
        if (split_diet[1].contains("spicy") && !split_diet[1].contains("non-spicy")) {
          SidesListView.getItems()
              .add(new ListViewItem(main.sideItems.get(i).name,
                  "£" + main.sideItems.get(i).price + "0", main.sideItems.get(i).calories + "",
                  main.sideItems.get(i).ingredients, main.sideItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxSides.getValue() == "Non-Spicy") {
        if (split_diet[1].contains("non-spicy")) {
          SidesListView.getItems()
              .add(new ListViewItem(main.sideItems.get(i).name,
                  "£" + main.sideItems.get(i).price + "0", main.sideItems.get(i).calories + "",
                  main.sideItems.get(i).ingredients, main.sideItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxSides.getValue() == "All") {
        SidesListView.getItems()
            .add(new ListViewItem(main.sideItems.get(i).name,
                "£" + main.sideItems.get(i).price + "0", main.sideItems.get(i).calories + "",
                main.sideItems.get(i).ingredients, main.sideItems.get(i).dietaryRequirements));
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
          DrinksListView.getItems()
              .add(new ListViewItem(main.drinkItems.get(i).name,
                  "£" + main.drinkItems.get(i).price + "0", main.drinkItems.get(i).calories + "",
                  main.drinkItems.get(i).ingredients, main.drinkItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxDrinks.getValue() == "Non-Vegetarian") {
        if (split_diet[0].equals("non-veg")) {
          DrinksListView.getItems()
              .add(new ListViewItem(main.drinkItems.get(i).name,
                  "£" + main.drinkItems.get(i).price + "0", "" + main.drinkItems.get(i).calories,
                  main.drinkItems.get(i).ingredients, main.drinkItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxDrinks.getValue() == "Fizzy") {
        if (split_diet[1].contains("fizzy") && !split_diet[1].contains("non-fizzy")) {
          DrinksListView.getItems()
              .add(new ListViewItem(main.drinkItems.get(i).name,
                  "£" + main.drinkItems.get(i).price + "0", "" + main.drinkItems.get(i).calories,
                  main.drinkItems.get(i).ingredients, main.drinkItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxDrinks.getValue() == "Non-Fizzy") {
        if (split_diet[1].contains("non-fizzy")) {
          DrinksListView.getItems()
              .add(new ListViewItem(main.drinkItems.get(i).name,
                  "£" + main.drinkItems.get(i).price + "0", "" + main.drinkItems.get(i).calories,
                  main.drinkItems.get(i).ingredients, main.drinkItems.get(i).dietaryRequirements));
        }
      } else if (filterBoxDrinks.getValue() == "All") {
        DrinksListView.getItems()
            .add(new ListViewItem(main.drinkItems.get(i).name,
                "£" + main.drinkItems.get(i).price + "0", "" + main.drinkItems.get(i).calories,
                main.drinkItems.get(i).ingredients, main.drinkItems.get(i).dietaryRequirements));
      }
    }
  }

  @FXML
  void changeScreenButtonPushed(ActionEvent event) throws IOException {
    Parent checkoutViewParent = FXMLLoader.load(getClass().getResource("/CheckoutView.fxml"));
    Scene checkoutViewScene = new Scene(checkoutViewParent, 800, 800);
    // This line gets the Stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(checkoutViewScene);
    window.show();

  }

  // Testing method to add to basket
  @FXML
  void handleAddItemButton(ActionEvent event) {
    ListViewItem selected = getSelect();
    String name = selected.getName();
    int x = 0;
    int num = 0;
    for (String each : BasketView.getItems()) {
      if (each.contains(name)) {
        BasketView.getItems().remove(x);
        String[] split = each.split(", ");
        num = Integer.valueOf(split[1]);
        break;
      }
      x++;
    }
    BasketView.getItems().add((name + ", " + (quantitySpinner.getValue() + num)));
    BasketView.setCellFactory(param -> new XCellDelete(this));
    // had cost showing correctly
    if (quantitySpinner.getValue() != 0) {
      Float price = Float.parseFloat(totalPrice.getText().split(" ")[1]);
      price = price + (Float.parseFloat(selected.getPrice().getText().split("£")[1])
          * quantitySpinner.getValue());
      totalPrice.setText("£ " + price + "0");
    }
    quantitySpinner.getValueFactory().setValue(1);

  }


  ListViewItem getSelect() {
    Tab Tab = tabPane.getSelectionModel().getSelectedItem();
    if (Tab.getText().equals("Main Menu")) {
      ListViewItem item = MainListView.getSelectionModel().getSelectedItem();
      addMainItem(item.getName());
      return item;
    } else if (Tab.getText().equals("Sides")) {
      ListViewItem item = SidesListView.getSelectionModel().getSelectedItem();
      addSideItem(item.getName());
      return item;
    } else if (Tab.getText().equals("Drinks")) {
      ListViewItem item = SidesListView.getSelectionModel().getSelectedItem();
      addDrinkItem(item.getName());
      return item;
    }
    return null;
  }

  void addMainItem(String item) {
    for (int x = 0; x < main.mainItems.size(); x++) {
      if (item.equals(main.mainItems.get(x).name)) {
        basketItems.add(main.mainItems.get(x));
      }
    }
  }

  void addSideItem(String item) {
    for (int x = 0; x < main.sideItems.size(); x++) {
      if (item.equals(main.sideItems.get(x).name)) {
        basketItems.add(main.sideItems.get(x));
      }
    }
  }

  void addDrinkItem(String item) {
    for (int x = 0; x < main.drinkItems.size(); x++) {
      if (item.equals(main.drinkItems.get(x).name)) {
        basketItems.add(main.drinkItems.get(x));
      }
    }
  }

  void setTotalPrice(String text) {
    totalPrice.setText("£ " + text);
  }

  Float getTotalPrice() {
    return Float.parseFloat(this.totalPrice.getText().split(" ")[1]);
  }

  void delete(String text) {
    // setTotalPrice((getTotalPrice()
    // - (Float.parseFloat(***InsertMethodToFindPriceOfItemFromBasket***.split(", ")[1])) *
    // Float.parseFloat(text.split(", ")[1])) + "");
    BasketView.getItems().remove(text);
  }

  @FXML
  void WaiterloginButton(ActionEvent event) throws IOException {
    Parent checkoutViewParent = FXMLLoader.load(getClass().getResource("/Waiterlogin.fxml"));
    Scene checkoutViewScene = new Scene(checkoutViewParent, 400, 400);
    // This line gets the Stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(checkoutViewScene);
    window.show();

  }

  public static void LoadUser() {
    try {

      BufferedReader reader = new BufferedReader(new FileReader(new File("user.txt")));
      String line = null;
      while ((line = reader.readLine()) != null) {
        String[] data = line.split("\\s+");
        list.add(new User(data[0], data[1]));

      }
      reader.close();
    } catch (IOException e) {
      err.println("cant not find user.txt");
      e.printStackTrace();
    }
  }

  public static boolean findUser(String account, String pwd) {
    return list.contains(new User(account, pwd));
  }

  public static User findAccount(String account) {
    for (User u : list)
      if (u.getAccount().equalsIgnoreCase(account))
        return u;
    return null;
  }
}
