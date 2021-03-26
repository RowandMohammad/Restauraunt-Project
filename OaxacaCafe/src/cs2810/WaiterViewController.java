package cs2810;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The controller class for the Waiter Interface and its relative functions.
 * 
 * @author Erikas Vieraitis
 *
 */
public class WaiterViewController {

	private ViewCustomerInterface parent;
	private static boolean isShowing;

	ArrayList<Order> pendingOrders;
	ArrayList<Order> ordersToCook;
	ArrayList<Order> ordersToDeliver;
	ArrayList<Order> ordersToPay;
	String tables;

	@FXML
	private ListView<PendingOrderViewItem> PendingOrdersView;

	@FXML
	private ListView<PendingOrderViewItem> OrdersToDeliverView;

	@FXML
	private ListView<PendingOrderViewItem> LeftToPayView;

	@FXML
	private Button BackToOrdering;

	@FXML
	private Label UserLabel;

	@FXML
	private Button CancelOrder;
	

	@FXML
    private Button changeOrderButton;




	/**
	 * @author Erikas Vieraitis
	 * 
	 * FXML function for when BackToOrdering button is pressed
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void BackToOrderingPressed(ActionEvent event) throws IOException {
		((Stage) BackToOrdering.getScene().getWindow()).close();
		isShowing = false;
	}

	public static void assistancePopup() {
		if (isShowing) {
			Alert alert = new Alert(AlertType.NONE, "Customer on table X is calling for assistance", ButtonType.OK);
			alert.showAndWait();
		}
	}

	/**
	 * @author Erikas Vieraitis
	 * 
	 * Function used to populate the Pending Order View for the waiter
	 * 
	 * @param pendingOrders holds an array containing the values of the current pending customer orders
	 */
	public void populatePending(ArrayList<Order> pendingOrders) {
	  PendingOrdersView.getItems().clear();
		int index = 0;
		for (Order order : pendingOrders) {
			PendingOrderViewItem item = new PendingOrderViewItem(this, order.getOrder(), index, order.payed,
					order.status);
			PendingOrdersView.getItems().add(item);
			index++;
		}
	}

	
	/**
	 * @author Erikas Vieraitis
	 * 
	 * Function used to populate the Orders ready for delivery
	 * 
	 * @param ordersToDeliver holds an array with the values of the orders ready for delivery
	 */
	public void populateOrdersToDeliver(ArrayList<Order> ordersToDeliver) {
		int index = 0;
		for (Order order : ordersToDeliver) {
			PendingOrderViewItem item = new PendingOrderViewItem(this, order.getOrder(), index, order.payed,
					order.status);
			OrdersToDeliverView.getItems().add(item);
			index++;
		}
	}

	/**
	 * @author Erikas Vieraitis
	 * 
	 * Function used to populate the Orders that have not been payed for yet
	 * 
	 * @param ordersToPay holds an array with the values of the orders that have not been payed for
     */
	 
	public void populateLeftToPay(ArrayList<Order> ordersToPay) {
		int index = 0;
		for (Order order : ordersToPay) {
			PendingOrderViewItem item = new PendingOrderViewItem(this, order.getOrder(), index, order.payed,
					order.status);
			LeftToPayView.getItems().add(item);
			index++;
		}
	}


	@FXML
	void handleCancelOrder(ActionEvent event) {
		int index = PendingOrdersView.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			cancelConfirmation(index);
		}

	}
	
	/**
	 * @author Erikas Vieraitis
	 * 
	 * FXML function for when the Change Order button is pressed
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
    void handleChangeOrder(ActionEvent event) throws IOException {
        int index = PendingOrdersView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            changeOrder(index);
        }
        

    }
	
	/**
	 * @author Erikas Vieraitis
	 * 
	 * Function for changing a pending customer order which opens up a separate view
	 * 
	 * @param index holds the int value of the current item index selected
	 * @throws IOException
	 */
	void changeOrder(int index) throws IOException {
	  FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChangeOrderView.fxml"));
      Parent root = loader.load();
      ChangeOrderViewController controller = loader.getController();
      controller.setInitialData(parent, this, pendingOrders, index);
      
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
	

	void cancelConfirmation(int index) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Cancel");
		alert.setContentText("Are you sure you want to cancel this order?");
		ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
		ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(yesButton, cancelButton);
		alert.showAndWait().ifPresent(type -> {
			if (type == yesButton) {
				PendingOrderViewItem item = PendingOrdersView.getItems().remove(index);
				pendingOrders.remove(index);
				updateIndex(PendingOrdersView, pendingOrders, item.getIndex());
				this.parent.updatePendingOrders(pendingOrders);
				PendingOrdersView.refresh();
				updateOrderStatus("Canceled");
			} else {
			}
		});
	}

	/**
	 * @author zhac319
	 * 
	 * Utility function for moving order from pending state to deliverable state
	 *
	 * @param index of order which have to be moved to deliverable state
	 * @throws URISyntaxException 
	 * @throws SQLException 
	 */
	public void confirmOrder(int index) throws SQLException, URISyntaxException {

		pendingOrders.get(index).status = "In progress";
		addToDB(pendingOrders.get(index).orderID, pendingOrders.get(index).status);
		PendingOrdersView.getItems().remove(index);
		ordersToCook.add(pendingOrders.get(index));
		pendingOrders.remove(index);
		updateIndex(PendingOrdersView, pendingOrders, index);
		this.parent.updatePendingOrders(pendingOrders);
		this.parent.updateOrdersToCook(ordersToCook);
		updateOrderStatus("In progress");
		PendingOrdersView.refresh();
		OrdersToDeliverView.refresh();
		
	}
	
	public void addToDB(String index, String status) throws SQLException, URISyntaxException {
		System.out.println(index);
		String waiterIDInsert = "UPDATE orders SET waiterid = ?, orderstatus = ? WHERE orderid = ?";
		Connection dbConnection = DatabaseInitialisation.getConnection();
		PreparedStatement statement = dbConnection.prepareStatement(waiterIDInsert);
		statement.setString(1, UserLabel.getText());
		statement.setString(2, status);
		statement.setString(3, index);
		statement.executeUpdate();
		
	}

	/**
	 * @author Erikas Vieraitis
	 * 
	 * Utility function for updating a current order items index in a view
	 * 
	 * @param View holds the list of items on the current GUI view 
	 * @param Orders holds an array of order items 
	 * @param currentIndex holds the int value of the current order object being manipulated
	 */
	private void updateIndex(ListView<PendingOrderViewItem> View, ArrayList<Order> Orders, int currentIndex) {
		for (int i = 0; i < Orders.size(); i++) {
			if (View.getItems().get(i).index > currentIndex) {
				View.getItems().get(i).index--;
			}
		}
	}

	/**
	 * @author zhac319
	 * 
	 * Utility function for moving order from deliverable state to delivered
	 *
	 * @param index of order which have to be moved to delivered
	 * @throws URISyntaxException 
	 * @throws SQLException 
	 */
	public void deliverOrder(int index) throws SQLException, URISyntaxException {
		ordersToDeliver.get(index).status = "Delivered";
		addToDB(ordersToDeliver.get(index).orderID, ordersToDeliver.get(index).status);
		if (ordersToDeliver.get(index).payed == false) {
			ordersToPay.add(ordersToDeliver.get(index));
			this.parent.updateOrdersToPay(ordersToPay);
			PendingOrderViewItem item = new PendingOrderViewItem(this, ordersToPay.get(index).getOrder(), index,
					ordersToPay.get(index).payed, ordersToPay.get(index).status);
			LeftToPayView.getItems().add(item);
			updateIndex(LeftToPayView, ordersToPay, index);
		}

		ordersToDeliver.remove(index);
		OrdersToDeliverView.getItems().remove(index);
		updateIndex(OrdersToDeliverView, ordersToDeliver, index);
		OrdersToDeliverView.refresh();
		updateOrderStatus("Delivered");
	}

	/**
	 * @author Erikas Vieraitis
	 * 
	 * Utility function for dismissing an Order that hasn't been payed for
	 * 
	 * @param index holds the int value of the current order object being dismissed
	 */
	public void dismissOrder(int index) {
		ordersToPay.remove(index);
		this.parent.updateOrdersToPay(ordersToPay);
		LeftToPayView.getItems().remove(index);
		updateIndex(LeftToPayView, ordersToPay, index);
		LeftToPayView.refresh();

	}

	/**
	 * @author Erikas Vieraitis
	 * 
	 * Function for updating the status of the pending order
	 * 
	 * @param status holds the String status value of an order
	 */
	private void updateOrderStatus(String status) {
		parent.setOrderStatus(status);
	}

	
	
	/**
	 * @author Erikas Vieraitis
	 * 
	 * Utility function for initialising all of the data for the waiter view
	 * 
	 * @param parent holds the ViewCustomerInterface class parent
	 * @param pendingOrders holds an Array containing all pending orders
	 * @param ordersToDeliver holds an Array containing all orders that have been cooked
	 * @param ordersToCook holds an Array containing all orders that need to be cooked
	 * @param ordersToPay holds an Array containing all orders that haven't been payed for
	 * @param username holds a String value containing the login user name
	 * @param tables holds the table number values
	 * @throws URISyntaxException
	 * @throws SQLException
	 */
	public void setInitialData(ViewCustomerInterface parent, ArrayList<Order> pendingOrders,
			ArrayList<Order> ordersToDeliver, ArrayList<Order> ordersToCook, ArrayList<Order> ordersToPay, String username, String tables) throws URISyntaxException, SQLException {
		this.parent = parent;
		this.pendingOrders = pendingOrders;
		this.ordersToDeliver = ordersToDeliver;
		this.ordersToCook = ordersToCook;
		this.ordersToPay = ordersToPay;
		this.tables = tables;
		populatePending(pendingOrders);
		populateOrdersToDeliver(ordersToDeliver);
		populateLeftToPay(ordersToPay);
		String employeeQuery = "SELECT employeename FROM staffinfo WHERE (employeerole = 'Waiter' AND username = '"+username+"');";
		Connection dbConnection = DatabaseInitialisation.getConnection();
		ResultSet rsName = DatabaseInitialisation.executeSelect(dbConnection, employeeQuery);
		rsName.next();
		UserLabel.setText(rsName.getString("employeeName"));
		System.out.print(this.tables);
	}


	public static void setIsShowing(boolean bool) {
		isShowing = bool;
	}

	
	/**
	 * @author Erikas Vieraitis
	 * 
	 * Function which is called to add an Order to the Orders To Deliver tab when an order is cooked
	 * 
	 * @param order holds the pending order view item
	 */
	public void addOrderToDeliver(PendingOrderViewItem order) {
		OrdersToDeliverView.getItems().add(order);
		OrdersToDeliverView.refresh();
	}
	
	/**
	 * @author Erikas Vieraitis
	 * 
	 * Function which is called to update the pending orders tab
	 * 
	 * @param pendingOrders holds an array list containing all orders that are pending
	 */
	public void updatePendingOrders(ArrayList<Order> pendingOrders) {
	  this.pendingOrders = pendingOrders;
	  populatePending(pendingOrders);
	  
	}
}
