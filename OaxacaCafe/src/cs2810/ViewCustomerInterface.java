package cs2810;

import static java.lang.System.err;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.util.Duration;

/**
 * The controller class for the Customer Interface and its relative functions.
 * 
 * 
 * @author Erikas Vieraitis
 *
 */
public class ViewCustomerInterface {

	private static ArrayList<User> list = new ArrayList<User>();
	private ArrayList<Menu_Item> mainItems;
	private ArrayList<Menu_Item> drinkItems;
	private ArrayList<Menu_Item> sideItems;
	
	private ArrayList<Order> pendingOrders = new ArrayList<Order>();
	private ArrayList<Order> ordersToCook = new ArrayList<Order>();
	private ArrayList<Order> ordersToDeliver = new ArrayList<Order>();
	private ArrayList<Order> ordersToPay = new ArrayList<Order>();

	private ArrayList<Order> currentOrders = new ArrayList<Order>();
	private ArrayList<ArrayList<Order>> allStatusOrders = new ArrayList<ArrayList<Order>>();
	
	String orderID = UUID.randomUUID().toString();

	String select = "";

	String selectedMenu = "";

	MenuMain main = new MenuMain();

	ArrayList<Menu_Item> basketItems;
	// = new ArrayList<Menu_Item>();

	MainControl parent;

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
	SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, initialValue);

	@FXML
	private Label totalPrice;

	private double totalTime;

	// Button to add items to order
	@FXML
	private Button addItemBtn;

	@FXML
	private Button checkoutBtn;

	@FXML
	private Button StartButton;

	@FXML
	private Button CallingButton;

	@FXML
	void StartButtonPressed(ActionEvent event) throws IOException {
		LoadUser();
		StartButton.setDisable(true);
		StartButton.setVisible(false);
		populateMenu();
		quantitySpinner.setValueFactory(svf);
		basketItems = new ArrayList<Menu_Item>();
	}

	@FXML
	private Button logoutbutton;

	@FXML
	private Label orderStatus;

	/**
	 * member variable for obtaining waiter reference acrosss different views
	 */
	private WaiterViewController waiterController;

	@FXML
	private Button payButton;
	
	/**
     * Initalalise the UI by adding the multiple options to the filtering buttons and
     * setting the listener.
     * 
     */
    @FXML
    public void initialize() {
        ObservableList<String> filterMainOpt = FXCollections.observableArrayList("Vegetarian", "Non-Vegetarian",
            "Spicy", "Non-Spicy", "All");
        ObservableList<String> filterSideOpt = FXCollections.observableArrayList("Vegetarian", "Non-Vegetarian",
            "Spicy", "Non-Spicy", "All");
        ObservableList<String> filterDrinkOpt = FXCollections.observableArrayList("Vegetarian", "Non-Vegetarian",
            "Fizzy", "Non-Fizzy", "All");

        filterBoxMain.setItems(filterMainOpt);
        filterBoxDrinks.setItems(filterDrinkOpt);
        filterBoxSides.setItems(filterSideOpt);
        setFilteringListener();
    }

	// Handles button click to call waiter
	@FXML
	void waiterButtonPressed(ActionEvent event) {
		URL url = this.getClass().getClassLoader().getResource("res/12025.mp3");
		Media media = new Media(url.toExternalForm());
		MediaPlayer mp = new MediaPlayer(media);
		mp.play();
		WaiterViewController.assistancePopup();
	}

	/**     
     * The user can pay for the orders they have placed thus far by launching the payment UI, however
     * if the total cost is £0.00 or they haven't placed their order yet an error alert pops up.
     * 
     * @param event the action of the user pressing the pay button
     * @throws IOException return IOException if controller doesn't exist
     * 
     */
	@FXML
	void payTotalCost(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.ERROR, "Press OK to return to main menu", ButtonType.OK);
		alert.setTitle("Payment error");
		System.out.println(totalPrice.getText());

		if (!totalPrice.getText().equals("£ 0.00") && basketItems.isEmpty()) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/PaymentView.fxml"));
			Parent root = loader.load();
			PaymentViewController controller = loader.getController();
			controller.setParentController(this);
			controller.setTotalPrice(getTotalPrice());
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} else if (totalPrice.getText().equals("£ 0.00")) {
			alert.setHeaderText("Total cost of purchase should be above £0.00");
			alert.showAndWait();
		} else if (!basketItems.isEmpty()) {
			alert.setHeaderText("Order needs to be placed first");
			alert.showAndWait();
		}
	}

	
    /**
     * When the user switches tabs, the listener hides the filter buttons that do not correspond
     * to the the selected tab thus only one filtering option is shown at a time.
     * 
     */
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

	
	/**
     * When the user filters out the main dishes, it will be removed from the list view and reinitalised again 
     * with the user's selection.
     * 
     * @param event the action event of selecting any of the filtering options within the main tab
     * @throws IOException
     */
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

	

   /**
    * When the user filters out the side dishes, it will be removed from the list view and reinitalised again 
    * with the user's selection.
    * 
    * @param event the action event of selecting any of the filtering options within the side tab
    * @throws IOException
    */
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

	
    /**
     * When the user filters out the drinks, it will be removed from the list view and reinitalised again 
     * with the user's selection.
     * 
     * @param event the action event of selecting any of the filtering options within the drink tab
     * @throws IOException
     */
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
			} else if (filterBoxDrinks.getValue() == null) {
				DrinksListView.getItems()
						.add(new ListViewItem(drinkItems.get(i).name, "£" + drinkItems.get(i).price + "0",
								"" + drinkItems.get(i).calories, drinkItems.get(i).ingredients,
								drinkItems.get(i).dietaryRequirements));
			}
		}
	}

	public String orderIDGenerator() {
		orderID = UUID.randomUUID().toString();
		return orderID;
	}

	/**
     * @param event the action event of when the user clicks on the checkout button
     * @throws IOException
     * @throws NumberFormatException
     * @throws SQLException
     * @throws URISyntaxException
     * @throws ParseException
     */
	@FXML
	void checkoutButtonPushed(ActionEvent event)
			throws IOException, NumberFormatException, SQLException, URISyntaxException, ParseException {
		setOrderStatus("Placed");
		
		// Gets the current time of when the checkout button is clicked
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, HH:mm:ss");
		String timeOfClick = dateFormat.format(date);

		if (Float.parseFloat(totalPrice.getText().split(" ")[1]) == 0.0) {
			orderIDGenerator();
		}
		// Every item within the basket will have their order date set to when the user clicked checkout
		for (Menu_Item item : basketItems) {
			item.setOrderDate(timeOfClick);
		}
		if (basketItems.size() != 0) {
			Order order = new Order(basketItems, 1, 111, false, "Placed", orderID);
			pendingOrders.add(order);
			currentOrders.add(order);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/CheckoutView.fxml"));
			Parent root = loader.load();
			CheckoutViewController controller = loader.getController();
			controller.setParentController(this);
			int prevOrders = currentOrders.size() - 1;
			controller.populateCheckout(basketItems, Float.parseFloat(totalPrice.getText().split(" ")[1]), timeOfClick,
					prevOrders, orderID, getTotalTime());

			basketItems = new ArrayList<Menu_Item>();
			BasketView.getItems().clear();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
			DateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
			EventHandler<ActionEvent> eventHandler = e -> {
				stage.setTitle(df.format(new Date()));
			};
			Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play();
		} else {
			System.out.println("Basket is empty");
		}
	}

	// Testing method to add to basket
	@SuppressWarnings("deprecation")
	@FXML
	void handleAddItemButton(ActionEvent event) throws URISyntaxException, SQLException {
		setOrderStatus("Not Placed");
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
		BasketView.getItems()
				.add((name + ", " + (quantitySpinner.getValue() + num) + ", " + selected.getPrice().getText()));
		BasketView.setCellFactory(param -> new XCellDelete(this));
		// had cost showing correctly
		if (quantitySpinner.getValue() != 0) {
			Float price = Float.parseFloat(totalPrice.getText().split(" ")[1]);
			price = price
					+ (Float.parseFloat(selected.getPrice().getText().split("£")[1]) * quantitySpinner.getValue());
			price = BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			setTotalPrice(price);
			setTotalTime(getTotalTime() + getTimeToCook(name, quantitySpinner.getValue()));

			updateStock(name, stockReduce(name, quantitySpinner.getValue()));
		}
		quantitySpinner.getValueFactory().setValue(1);

		for (int i = 0; i < basketItems.size(); i++) {
			System.out.println(basketItems.get(i));
		}

	}

	private double getTimeToCook(String name, Integer quantity) throws URISyntaxException, SQLException {
		String getTimeToCook = "Select eta from " + getSelectedMenu() + " where name = '" + name + "'";
		Connection dbConnection = DatabaseInitialisation.getConnection();
		ResultSet results = DatabaseInitialisation.executeSelect(dbConnection, getTimeToCook);
		results.next();
		double time = results.getInt(1) * (quantity/1.5);
		return time;

	}

	private int stockReduce(String name, Integer quantity) throws URISyntaxException, SQLException {
		String getItemStock = "Select stock from " + getSelectedMenu() + " where name = '" + name + "'";
		Connection dbConnection = DatabaseInitialisation.getConnection();
		ResultSet results = DatabaseInitialisation.executeSelect(dbConnection, getItemStock);
		results.next();
		int newStock = results.getInt(1) - quantity;
		System.out.println(newStock);
		return newStock;

	}



	private void updateStock(String name, Integer newStock) throws URISyntaxException, SQLException {
		String getItemStock = "UPDATE " + getSelectedMenu() + " SET stock = ? WHERE name = ?";
		Connection dbConnection = DatabaseInitialisation.getConnection();
		PreparedStatement statement = dbConnection.prepareStatement(getItemStock);
		statement.setInt(1, newStock);
		statement.setString(2, name);
		statement.executeUpdate();

	}

	ListViewItem getSelect() throws URISyntaxException, SQLException {
		Tab Tab = tabPane.getSelectionModel().getSelectedItem();
		if (Tab.getText().equals("Main Menu")) {
			ListViewItem item = MainListView.getSelectionModel().getSelectedItem();
			addMainItem(item.getName());
			setSelectedMenu("mainmenu");
			return item;
		} else if (Tab.getText().equals("Sides")) {
			ListViewItem item = SidesListView.getSelectionModel().getSelectedItem();
			addSideItem(item.getName());
			setSelectedMenu("sidesmenu");
			return item;
		} else if (Tab.getText().equals("Drinks")) {
			ListViewItem item = DrinksListView.getSelectionModel().getSelectedItem();
			addDrinkItem(item.getName());
			setSelectedMenu("drinksmenu");
			return item;
		}
		return null;
	}

	void addMainItem(String item) throws URISyntaxException, SQLException {
		for (int x = 0; x < mainItems.size(); x++) {
			if (item.equals(mainItems.get(x).name)) {
				for (int y = 0; y < quantitySpinner.getValue(); y++) {
					Menu_Item main = mainItems.get(x);
					Menu_Item clonedMain = main.Clone(main);
					basketItems.add(clonedMain);
					System.out.println(mainItems.get(x).name);

				}
			}
		}
	}

	void addSideItem(String item) {
		for (int x = 0; x < sideItems.size(); x++) {
			if (item.equals(sideItems.get(x).name)) {
				for (int y = 0; y < quantitySpinner.getValue(); y++) {
					Menu_Item side = sideItems.get(x);
					Menu_Item clonedSide = side.Clone(side);
					basketItems.add(clonedSide);
				}
			}
		}
	}

	void addDrinkItem(String item) {
		for (int x = 0; x < drinkItems.size(); x++) {
			if (item.equals(drinkItems.get(x).name)) {
				for (int y = 0; y < quantitySpinner.getValue(); y++) {
					Menu_Item drink = drinkItems.get(x);
					Menu_Item clonedDrink = drink.Clone(drink);
					basketItems.add(clonedDrink);
				}
			}
		}
	}



	void setTotalPrice(float price) {
		totalPrice.setText("£ " + price + "0");
	}

	Float getTotalPrice() {
		return Float.parseFloat(this.totalPrice.getText().split("£")[1]);
	}

	void delete(String text) throws URISyntaxException, SQLException {
		System.out.print(text);
		String name = text.split(", ")[0];
		int num = Integer.parseInt(text.split(", ")[1]);
		float price = getTotalPrice();

		for (int x = 0; x < basketItems.size(); x++) {
			while (num > 0) {
				if (basketItems.get(x).name.equals(name)) {
					price = price - (float) basketItems.get(x).price;
					basketItems.remove(x);
					num--;;
				}
			}
		}
		// setTotalPrice((getTotalPrice()
		// - (Float.parseFloat(***InsertMethodToFindPriceOfItemFromBasket***.split(",
		// ")[1])) *
		// Float.parseFloat(text.split(", ")[1])) + "");

		setTotalPrice(price);
		BasketView.getItems().remove(text);
	}

	@FXML
	void refresh(ActionEvent event) throws IOException {
		filterChangeMain(event);
		filterChangeSides(event);
		filterChangeDrinks(event);
	}

	@FXML
	void WaiterloginButton(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/WaiterLogin.fxml"));
		Parent root = loader.load();
		WaiterloginController controller = loader.getController();
		controller.setInitialData(this, pendingOrders, ordersToCook, ordersToDeliver, ordersToPay);
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		DateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		EventHandler<ActionEvent> eventHandler = e -> {
			stage.setTitle(df.format(new Date()));
		};
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}

	public static void LoadUser() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("user.txt")));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split("\\s+");
				list.add(new User(data[0], data[1], data[2]));
			}
			reader.close();
		} catch (IOException e) {
			err.println("cant not find user.txt");
			e.printStackTrace();
		}
	}

	public static boolean findUser(String account, String pwd, String staff) {
		return list.contains(new User(account, pwd, staff));
	}

	public static User findAccount(String account) {
		for (User u : list)
			if (u.getAccount().equalsIgnoreCase(account))
				return u;
		return null;
	}

	public ArrayList<Menu_Item> getBasketItems() {
		return basketItems;
	}

	public void updatePendingOrders(ArrayList<Order> pendingOrders) {
		this.pendingOrders = pendingOrders;
	}

	public void updateOrdersToCook(ArrayList<Order> ordersToCook) {
		this.ordersToCook = ordersToCook;
	}

	public void updateOrdersToDeliver(ArrayList<Order> ordersToDeliver) {
		this.ordersToDeliver = ordersToDeliver;
	}

	public void updateOrdersToPay(ArrayList<Order> ordersToPay) {
		this.ordersToPay = ordersToPay;
	}

	/**
	 * Mutator function for updating order status from other views
	 *
	 * @param status of order
	 */
	public void setOrderStatus(String status) {
		orderStatus.setText(status);
	}

	/**
	 * Accessor for obtaining refrance to waiter view
	 * 
	 * @return waiterViewController object
	 */
	public WaiterViewController getWaiterController() {
		return waiterController;
	}

	/**
	 * Mutator for updating waiter view
	 * 
	 * @param waiterController
	 */
	public void setWaiterController(WaiterViewController waiterController) {
		this.waiterController = waiterController;
	}

	public void setPayedOrders() {
		allStatusOrders.add(pendingOrders);
		allStatusOrders.add(ordersToCook);
		allStatusOrders.add(ordersToDeliver);
		allStatusOrders.add(ordersToPay);
		
		for (int i = 0; i < allStatusOrders.size(); i++) {
			for (int j = 0; j < allStatusOrders.get(i).size(); j++) {
				allStatusOrders.get(i).get(j).payed = true;
			}
		}
		pendingOrders = allStatusOrders.get(0);
		ordersToCook = allStatusOrders.get(1);
		ordersToDeliver = allStatusOrders.get(2);
		ordersToPay.clear();
		allStatusOrders.clear();
		currentOrders.clear();
	}

	public void setParent(MainControl parent) {
		this.parent = parent;
	}

	public void setStaff(waiterStaff waiterStaff) {
//	adds the waiterStaff object to the arraylists in mainControl to test
	}

	/**
	 * @return the totalTime
	 */
	public double getTotalTime() {
		return totalTime;
	}

	/**
	 * @param d the totalTime to set
	 */
	public void setTotalTime(double d) {
		this.totalTime = d;
	}

	public String getSelectedMenu() {
		return selectedMenu;
	}

	public void setSelectedMenu(String selectedMenu) {
		this.selectedMenu = selectedMenu;
	}

}
