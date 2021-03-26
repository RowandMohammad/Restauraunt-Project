package cs2810;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.Date;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.util.Duration;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class WaiterloginController {

	private ViewCustomerInterface parent;
	private MainControl view;
	private String tables = "";

	ArrayList<Order> pendingOrders;
	ArrayList<Order> ordersToCook;
	ArrayList<Order> ordersToDeliver;
	ArrayList<Order> ordersToPay;

	@FXML
	private TextField userAccount;
	@FXML
	private PasswordField userPwd;
	@FXML
	private Button clear;
	@FXML
	private Button login;

	@FXML
	private Button backToOrder;

	private WaiterViewController waiterViewController;

	/**
	 *Testing method to back to the customer interface
	 */
	@FXML
	void changeScreenButtonPushed(ActionEvent event) throws IOException {
		Stage stage = (Stage) backToOrder.getScene().getWindow();
		stage.close();
	}

	void changeScreenLoginCorrect(ActionEvent event, String staff) throws IOException, URISyntaxException, SQLException {
		System.out.println(staff);
		if (staff.equals("Waiter")) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/WaiterView.fxml"));
			Parent root = loader.load();
			WaiterViewController controller = loader.getController();
			WaiterViewController.setIsShowing(true);
			controller.setInitialData(parent, pendingOrders, ordersToDeliver, ordersToCook, ordersToPay, userAccount.getText(), tables);
			parent.setWaiterController(waiterViewController);
			parent.setStaff(new waiterStaff());
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
			closeWaiterViewListener(stage);
		} else if (staff.equals("Kitchen Staff")) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/KitchenView.fxml"));
			Parent root = loader.load();
			KitchenStaffView controller = loader.getController();
			controller.initialiseData(parent, parent.getWaiterController(), ordersToCook, ordersToDeliver, userAccount.getText());
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
			
		} else if (staff.equals("Manager")) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ManagerView.fxml"));
			Parent root = loader.load();
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
	}

	/**
	 *Testing method to clear account and password
	 */
	@FXML
	void clearButton(ActionEvent event) throws IOException {
		userPwd.setText("");
		userAccount.setText("");
	}
	
	/**
	 *Testing method to login
	 *Check if passes object is equal to current object or not
         * @param object to be checked
         * If the user name and password is correct,Enter the new interface
	 */
	@FXML
	void loginButton(ActionEvent event) throws IOException, URISyntaxException, SQLException {
		String usernameQuery = "SELECT * FROM staffinfo where username="+userAccount.getText()+" and password="+userPwd.getText()+"";
		Connection dbConnection = DatabaseInitialisation.getConnection();
		ResultSet results = DatabaseInitialisation.executeSelect(dbConnection, usernameQuery);


		if (results.next()) {
			System.out.println(results.getString(1));
			Alert alert = new Alert(AlertType.INFORMATION);
			if(Integer.parseInt(results.getString(7))==0) {
				//tableNum generator code
				if(tables.equals("")){
					tables=tables+"1";
				}
				else {
					tables = tables+",1";
				}
				//update database tablenum
			}
			alert.setTitle("success");
			alert.setHeaderText(null);
			alert.setContentText("Login successful");
			alert.showAndWait();
			Stage stage = (Stage) login.getScene().getWindow();
			stage.close();
			changeScreenLoginCorrect(event, results.getString(5));

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("error");
			alert.setHeaderText(null);
			alert.setContentText("User name or password error, please try again");
			alert.showAndWait();
			userAccount.clear();
			userPwd.clear();

		}

	}

	public void setInitialData(ViewCustomerInterface parent, ArrayList<Order> pendingOrders,
		ArrayList<Order> ordersToCook, ArrayList<Order> ordersToDeliver, ArrayList<Order> ordersToPay) {
		this.parent = parent;
		this.pendingOrders = pendingOrders;
		this.ordersToCook = ordersToCook;
		this.ordersToDeliver = ordersToDeliver;
		this.ordersToPay = ordersToPay;
	}

	/**
	 * When the waiter closes the WaiterView UI via the top right button it will set the isShowing
	 * boolean condition back to false.
	 * 
	 * @param stage the WaiterView UI to be passed
	 */
	private void closeWaiterViewListener(Stage stage) {
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				WaiterViewController.setIsShowing(false);
			}
		});
	}

	/**
	 * @return the view
	 */
	public MainControl getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(MainControl view) {
		this.view = view;
	}

}
