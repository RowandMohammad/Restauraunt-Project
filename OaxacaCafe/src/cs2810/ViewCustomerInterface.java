package cs2810;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
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
	private ListView<String> BasketView;

	@FXML
	private Spinner<Integer> quantitySpinner;// Quantity select
	// Spinner Value Factory
	final int initialValue = 1;
	SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, initialValue);

	@FXML
	private TextField totalPrice;

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

	// Initialises the Menu list for Mains, Sides and Drinks
	public void populateMenu() throws IOException {
		main.initialiseMainItems();
		main.initiliseDrinkItems();
		main.initialiseSideItems();

		for (int i = 0; i < 8; i++) {
			String ingr = Arrays.toString(main.mainItems.get(i).ingredients);
			MainListView.getItems().add("--" + main.mainItems.get(i).name + "\n£" + main.mainItems.get(i).price + "0");
		}

		for (int i = 0; i < 7; i++) {
			String ingr = Arrays.toString(main.sideItems.get(i).ingredients);
			SidesListView.getItems().add("--" + main.sideItems.get(i).name + "\n£" + main.sideItems.get(i).price + "0");
		}

		for (int i = 0; i < 6; i++) {
			String ingr = Arrays.toString(main.drinkItems.get(i).ingredients);
			DrinkListView.getItems()
					.add("--" + main.drinkItems.get(i).name + "\n£" + main.drinkItems.get(i).price + "0");
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
		BasketView.getItems().add(String.valueOf(quantitySpinner.getValue()));

	}

}
