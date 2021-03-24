package cs2810;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class KitchenStaffView {

	private ViewCustomerInterface parent;
	private WaiterViewController waiterController;
	ArrayList<Order> ordersToCook;
	ArrayList<Order> ordersToDeliver;

	@FXML
	private ListView<PendingOrderViewItem> ordersToCookView;

	@FXML
	private Button backToOrderingButton;

	@FXML
	private Label UserLabel;

	@FXML
	void backToOrderingPressed(ActionEvent event) {
		((Stage) backToOrderingButton.getScene().getWindow()).close();

	}

	public void initialiseData(ViewCustomerInterface parent, WaiterViewController waiterController,
			ArrayList<Order> ordersToCook, ArrayList<Order> ordersToDeliver, String username)
			throws URISyntaxException, SQLException {
		this.waiterController = waiterController;
		this.ordersToDeliver = ordersToDeliver;
		this.ordersToCook = ordersToCook;
		this.parent = parent;
		populateOrdersToCook();
		String employeeQuery = "SELECT employeename FROM staffinfo WHERE (employeerole = 'Kitchen Staff' AND username = '"
				+ username + "');";
		Connection dbConnection = DatabaseInitialisation.getConnection();
		ResultSet rsName = DatabaseInitialisation.executeSelect(dbConnection, employeeQuery);
		rsName.next();
		UserLabel.setText(rsName.getString("employeeName"));
	}

	private void populateOrdersToCook() {
		int index = 0;
		for (Order order : ordersToCook) {
			PendingOrderViewItem item = new PendingOrderViewItem(this, order.getOrder(), index, order.payed,
					order.status);
			ordersToCookView.getItems().add(item);
			index++;
		}

	}

	public void confirmOrder(int index) throws SQLException, URISyntaxException {
		System.out.println(ordersToCook.get(index).getOrder().get(index));

		ordersToCook.get(index).status = "Food Cooked";
		String cookIDInsert = "UPDATE orders SET cookid = ?, orderstatus = ? WHERE orderid = ?";
		Connection dbConnection = DatabaseInitialisation.getConnection();
		PreparedStatement statement = dbConnection.prepareStatement(cookIDInsert);
		statement.setString(1, UserLabel.getText());
		statement.setString(2, ordersToCook.get(index).status);
		statement.setString(3, ordersToCook.get(index).orderID);
		statement.executeUpdate();
		PendingOrderViewItem item = ordersToCookView.getItems().remove(index);
		ordersToDeliver.add(ordersToCook.get(index));
		Order order = ordersToCook.remove(index);
		updateIndex(ordersToCookView, item.getIndex());
		this.parent.updateOrdersToCook(ordersToCook);
		this.parent.updateOrdersToDeliver(ordersToDeliver);
		ordersToCookView.refresh();
		this.parent.setOrderStatus("Food Cooked");
		

	}

	private void updateIndex(ListView<PendingOrderViewItem> ordersToCookView, int currentIndex) {
		for (int i = 0; i < ordersToCook.size(); i++)
			if (ordersToCookView.getItems().get(i).index > currentIndex)
				ordersToCookView.getItems().get(i).index--;
	}

}
