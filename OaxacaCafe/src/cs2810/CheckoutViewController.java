package cs2810;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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

	public void populateCheckout(ArrayList<Menu_Item> basket, Float price, String time, int prevOrders) throws SQLException, URISyntaxException {
		setTotalPrice(price);
		String order = "";
		String completeOrder = "";
		String foodOrderedinsert = "INSERT INTO orders (foodordered) VALUES(?)";
		Connection dbConnection = DatabaseInitialisation.getConnection();
		PreparedStatement statement = dbConnection.prepareStatement(foodOrderedinsert);
		String foodOrdered = "";
		for (int i = 0; i < basket.size(); i++) {
			order = order + basket.get(i).name + "  £" + basket.get(i).price + "\n";
			foodOrdered = foodOrdered + " " + basket.get(i).name +",";

		}
		statement.setString(1, foodOrdered);
		statement.executeUpdate();
		completeOrder = time + "\n\n" + "Previous Orders: x" + prevOrders + " + \n" + order + "\n";
		price = BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		OrderList.getItems().add(completeOrder);
		OrderList.getItems().add("Total Price: £" + price + "");

	}

	private void setTotalPrice(Float price) {
		totalPrice = price;
	}

	public void setParentController(ViewCustomerInterface controller) {
		parent = controller;
	}

}
