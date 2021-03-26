package cs2810;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class CheckoutViewController {

	private ViewCustomerInterface parent;
	private Float totalPrice;

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

	@SuppressWarnings("deprecation")
	public void populateCheckout(ArrayList<Menu_Item> basket, Float price, String time, int prevOrders, String orderID, int totalTime)
			throws SQLException, URISyntaxException, ParseException {
		setTotalPrice(price);
		String order = "";
		String completeOrder = "";
		Date date = Calendar.getInstance().getTime();

		String foodOrdered = "";
		for (int i = 0; i < basket.size(); i++) {
			order = order + basket.get(i).name + "  £" + basket.get(i).price + "\n";
			foodOrdered = foodOrdered + " " + basket.get(i).name + ",";

		}

		completeOrder = time + "\n\n" + "Previous Orders: x" + prevOrders + " + \n" + order + "\n";
		price = BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		OrderList.getItems().add(completeOrder);
		OrderList.getItems().add("Total Price: £" + price + "");
		;
		orderCheck(orderID);
		if (orderCheck(orderID) == false) {
			addToDB(date, price, foodOrdered, orderID, calcETA(totalTime));

		} else if (orderCheck(orderID) == true) {
			updateDB(price, foodOrdered, orderID, calcETA(totalTime));

		}

	}

	private void addToDB(Date date, Float price, String foodOrdered, String orderid, String eta)
			throws SQLException, URISyntaxException {
		String foodOrderedinsert = "INSERT INTO orders (orderid, foodordered, totalprice, orderstatus, eta) VALUES(?,?,?,?,?)";
		Connection dbConnection = DatabaseInitialisation.getConnection();
		PreparedStatement statement = dbConnection.prepareStatement(foodOrderedinsert);
		statement.setString(1, orderid);
		statement.setString(2, foodOrdered);
		statement.setFloat(3, price);
		statement.setString(4, "Placed");
		statement.setString(5, eta);
		statement.executeUpdate();
		dbConnection.close();

	}

	private void updateDB(Float price, String foodOrdered, String orderid, String eta) throws SQLException, URISyntaxException {
		String getCurrentFood = "Select foodordered from orders where orderid = '"+ orderid+"'";
		Connection dbConnection = DatabaseInitialisation.getConnection();
		ResultSet results = DatabaseInitialisation.executeSelect(dbConnection, getCurrentFood);
		results.next();
		String result = results.getString(1);
		foodOrdered = foodOrdered + result;
		String orderUpdate = "UPDATE orders SET foodordered = ?, totalprice = ?, eta = ? WHERE orderid = ?";
		PreparedStatement statement = dbConnection.prepareStatement(orderUpdate);
		statement.setString(1, foodOrdered);
		statement.setFloat(2, price);
		statement.setString(3, eta);
		statement.setString(4, orderid);
		
		statement.executeUpdate();
		dbConnection.close();

	}
	
	private String calcETA(int totalTime) throws ParseException {
		 Date time = new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		 Date timeNow = formatter.parse(formatter.format(time));
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(timeNow);
		 cal.add(Calendar.MINUTE, totalTime);
		 String newTime = formatter.format(cal.getTime());
		 return newTime;
	}

	private boolean orderCheck(String orderid) throws SQLException, URISyntaxException {
		boolean check = false;
		System.out.println(orderid);
		String orderChecker = "select exists(select 1 from orders where orderid=" + "'" + orderid + "'" + ")";
		Connection dbConnection = DatabaseInitialisation.getConnection();
		ResultSet results = DatabaseInitialisation.executeSelect(dbConnection, orderChecker);
		String result;
		results.next();
		result = results.getString(1);
		System.out.println(results.getString(1));

		if (result.equals("t") ) {
			check = true;
		} else {
			check = false;
		}
		System.out.println(check);
		return check;

	}

	private void setTotalPrice(Float price) {
		totalPrice = price;
	}

	public void setParentController(ViewCustomerInterface controller) {
		parent = controller;
	}

}
