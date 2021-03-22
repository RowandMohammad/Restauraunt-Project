package cs2810;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CheckoutViewController {

	private ViewCustomerInterface parent;
	private Float totalPrice;
	boolean check ;

	@FXML
	private Button backToOrder;

	@FXML
	private ListView<String> OrderList;

	@FXML
	private Button payOrderButton;

	@FXML
	void changeScreenButtonPushed(ActionEvent event) throws IOException {
		Stage stage = (Stage) backToOrder.getScene().getWindow();
		stage.close();
	}

	@FXML
	void changeToPaymentView(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/PaymentView.fxml"));
		Parent root = loader.load();
		PaymentViewController controller = loader.getController();
		Stage window = (Stage) payOrderButton.getScene().getWindow();
		controller.setTotalPrice(totalPrice);
		controller.setParentController(parent);
		window.setScene(new Scene(root));
	}

	public void populateCheckout(ArrayList<Menu_Item> basket, Float price, String time, int prevOrders, Date date,
			String orderID) throws SQLException, URISyntaxException {
		setTotalPrice(price);
		String order = "";
		String completeOrder = "";

		String foodOrdered = "";
		for (int i = 0; i < basket.size(); i++) {
			order = order + basket.get(i).name + "  £" + basket.get(i).price + "\n";
			foodOrdered = foodOrdered + " " + basket.get(i).name + ",";

		}

		completeOrder = time + "\n\n" + "Previous Orders: x" + prevOrders + " + \n" + order + "\n";
		price = BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		OrderList.getItems().add(completeOrder);
		OrderList.getItems().add("Total Price: £" + price + "");;
		orderCheck(orderID); 
		if (check == false) {
			addToDB(date, price, foodOrdered, orderID);

		} else if (check == true) {
			updateDB(price, foodOrdered, orderID);

		}

	}

	private void addToDB(Date date, Float price, String foodOrdered, String orderid)
			throws SQLException, URISyntaxException {
		String foodOrderedinsert = "INSERT INTO orders (orderid, foodordered, totalprice, orderstatus, ordertime) VALUES(?,?,?,?,?)";
		long ordertime = date.getTime();
		Connection dbConnection = DatabaseInitialisation.getConnection();
		PreparedStatement statement = dbConnection.prepareStatement(foodOrderedinsert);
		statement.setString(1, orderid);

		statement.setString(2, foodOrdered);
		statement.setFloat(3, price);
		statement.setString(4, "Placed");
		statement.setTimestamp(5, new Timestamp(ordertime));
		statement.executeUpdate();

	}

	private void updateDB(Float price, String foodOrdered, String orderid) throws SQLException, URISyntaxException {
		Connection dbConnection = DatabaseInitialisation.getConnection();

		String orderUpdate = "UPDATE orders SET foodordered = ?, totalprice = ? WHERE orderid = ?";
		PreparedStatement statement = dbConnection.prepareStatement(orderUpdate);
		statement.setString(1, foodOrdered);
		statement.setFloat(2, price);
		statement.setString(3, orderid);
		statement.executeUpdate();

	}

	private void orderCheck(String orderid) throws SQLException, URISyntaxException {
		System.out.println(orderid);
		String orderChecker = "select exists(select 1 from orders where orderid=" + "'" + orderid + "'" + ")";
		Connection dbConnection = DatabaseInitialisation.getConnection();
		ResultSet results = DatabaseInitialisation.executeSelect(dbConnection, orderChecker);
		String result;
		results.next();
		result = results.getString(1);
		System.out.println(results.getString(1));

		if (result == "t") {
			check = true;
		}else {
			check = false;
		}
		System.out.println(check);


	}

	private void setTotalPrice(Float price) {
		totalPrice = price;
	}

	public void setParentController(ViewCustomerInterface controller) {
		parent = controller;
	}

}
