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

/**
 * The controller class for the Kitchen Staff Interface and its relative functions.
 * 
 * @author Erikas Vieraitis
 *
 */
public class KitchenStaffView {

	private ViewCustomerInterface parent;
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


	/**
	 * @author Erikas Vieraitis
	 * 
	 * Utility function for initialising all of the data for the kitchen view
	 * 
	 * @param parent holds the ViewCustomerInterface class parent
	 * @param waiterController holds the WaiterViewController class parent
	 * @param ordersToCook holds an Array containing all orders that need to be cooked
	 * @param ordersToDeliver holds an Array containing all orders that have been cooked
	 * @param username holds a String value containing the login user name
	 * @throws URISyntaxException
	 * @throws SQLException
	 */
	public void initialiseData(ViewCustomerInterface parent, WaiterViewController waiterController,
			ArrayList<Order> ordersToCook, ArrayList<Order> ordersToDeliver, String username)
			throws URISyntaxException, SQLException {
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

	
	
	/**
	 * @author Erikas Vieraitis
	 * 
	 * Function used to populate the orders to cook view for the kitchen
	 * 
	 */
	private void populateOrdersToCook() {
		int index = 0;
		for (Order order : ordersToCook) {
			PendingOrderViewItem item = new PendingOrderViewItem(this, order.getOrder(), index, order.payed,
					order.status);
			ordersToCookView.getItems().add(item);
			index++;
		}

	}

	/**
	 * @author Erikas Vieraitis
	 * 
	 * Function used for confirming an order when the confirm button is pressed in the kitchen view
	 * 
	 * @param index
	 * @throws SQLException
	 * @throws URISyntaxException
	 */
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
		ordersToCook.remove(index);
		updateIndex(ordersToCookView, item.getIndex());
		this.parent.updateOrdersToCook(ordersToCook);
		this.parent.updateOrdersToDeliver(ordersToDeliver);
		ordersToCookView.refresh();
		this.parent.setOrderStatus("Food Cooked");
		

	}
	
	
	/**
     * @author Erikas Vieraitis
     * 
     * Utility function for updating a current order items index in a view
     * 
     * @param ordersToCookView holds an array of order items 
     * @param currentIndex holds the int value of the current order object being manipulated
     */
	private void updateIndex(ListView<PendingOrderViewItem> ordersToCookView, int currentIndex) {
		for (int i = 0; i < ordersToCook.size(); i++)
			if (ordersToCookView.getItems().get(i).index > currentIndex)
				ordersToCookView.getItems().get(i).index--;
	}

}
