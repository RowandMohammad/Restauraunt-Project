package cs2810;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhac319
 * 
 * Utility class for handling detail for new Order
 * data members
 * orderDetails: ArrayList<Menu_Item>  ==> List of items selected for order
 */

public class PendingOrderViewItem extends HBox {
    private ArrayList<Menu_Item> orderDetails;
    private Button confirmButton;
    private Label orderDetailLabel;
    private WaiterViewController parentController;
    private KitchenStaffView parentController2;
    int index;
    boolean payed;
    String status;


    /**
     * @author zhac319
     * 
     * No default initialization allowed
     * @param isPending
     * @param index
     * @param arrayList
     * @param kitchenStaffView
     */


    /**
     * @author zhac319
     * 
     * Parametrized constructor for initializing new order view object
     *
     * @param orderDetails: List of items selected for order
     */
    public PendingOrderViewItem(WaiterViewController parentController, ArrayList<Menu_Item> orderDetails,
                                int index, boolean payed, String status) {
        this.parentController = parentController;
        this.index = index;
        this.orderDetails = orderDetails;
        this.payed = payed;
        this.status = status;
        this.setUpView();
        this.setupCallback("waiter");
    }


    public PendingOrderViewItem(KitchenStaffView parentController2, ArrayList<Menu_Item> orderDetails, int index, boolean payed, String status) {
        this.parentController2 = parentController2;
        this.index = index;
        this.orderDetails = orderDetails;
        this.payed = payed;
        this.status = status;
        this.setUpView();
        this.setupCallback("kitchen");

    }

    /**
     * @author zhac319
     * 
     * Utility function for initializing view
     */
    private void setUpView() {
      boolean isFirst = true;
      this.setAlignment(Pos.CENTER_LEFT);
      if(status.equals("Delivered")) {
        this.confirmButton = new Button("Dismiss");
      }
      else {
        this.confirmButton = new Button("Confirm");
      }


      HBox.setMargin(this.confirmButton, new Insets(0, 0, 0, 300));
      this.orderDetailLabel = new Label();
      String orderDetail = "";
      // Iterate through all the orders to pass to the list view, the first iteration will show the order time
      for (Menu_Item menuItem : orderDetails) {
          if (isFirst) {
              orderDetail += "Order Time: " + menuItem.getOrderDate() + "\n" + "Order Item(s):\n" + menuItem.name + "\n";
              isFirst = false;
          }
          else {
            orderDetail += menuItem.name + "\n";
          }
      }
      if(status.equals("Delivered")) {
        orderDetail += "\n\nPayed: " + payed + "\n\n";
      }
      this.orderDetailLabel.setText(orderDetail);
      this.getChildren().addAll(orderDetailLabel, confirmButton);
  }

  /**
   * @author zhac319
   * 
   * Utility function for assigning callback function to button click and confirming order
   *
   * @param staff
   */
  private void setupCallback(String staff) {
      this.confirmButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {

              if(status.equals("Placed")) {
                try {
					parentController.confirmOrder(index);
				} catch (SQLException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
              }
              else if(status.equals("In progress")) {
                try {
					parentController2.confirmOrder(index);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              }
              else if(status.equals("Food Cooked")) {
                parentController.deliverOrder(index);     
              }  
              else if(status.equals("Delivered")) {
                parentController.dismissOrder(index);
              }

          }
      });
  }

    /**
     * @author zhac319
     * 
     * Utility function for updating customer about order confirmation
     */
    @SuppressWarnings("unused")
	private void updateCustomerView() {
        //@TODO implement a strategy to communicate order status to customer
    }

    /**
     * @author zhac319
     * 
     * @return List of all items in order
     */
    public ArrayList<Menu_Item> getOrderDetails() {
        return orderDetails;
    }

    /**
     * @author zhac319
     * 
     * @param orderDetails List of all items in order
     */
    public void setOrderDetails(ArrayList<Menu_Item> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public WaiterViewController getParentController() {
        return parentController;
    }

    public void setParentController(WaiterViewController parentController) {
        this.parentController = parentController;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

 
}
