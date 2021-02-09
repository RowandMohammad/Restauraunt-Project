package cs2810;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

/**
 * Utility class for handling detail for new Order
 * data members
 * 		orderDetails: ArrayList<Menu_Item>  ==> List of items selected for order
 */
public class PendingOrderViewItem extends HBox {
    private ArrayList<Menu_Item> orderDetails;
    private Button confirmButton;
    private Label orderDetailLabel;

    /**
     * No default initialization allowed
     */
    private PendingOrderViewItem(){

    }

    /**
     * Parametrized constructor for initializing new order view object
     * @param orderDetails:  List of items selected for order
     */
    public PendingOrderViewItem(ArrayList<Menu_Item> orderDetails) {
        this.orderDetails = orderDetails;
        this.setUpView();
        this.setupCallback();
    }

    /**
     * Utility function for initializing view
     */
    private void setUpView() {
        this.setAlignment(Pos.CENTER);
        this.confirmButton = new Button("Confirm");
        this.orderDetailLabel = new Label();
        String orderDetail = "";
        for (Menu_Item menuItem : orderDetails) {
            orderDetail+= menuItem.name;
        }
        this.orderDetailLabel.setText(orderDetail);
        this.getChildren().addAll(orderDetailLabel, confirmButton);
    }

    /**
     * Utility function for assigning callback function to button click and confirming order
     */
    private void setupCallback() {
        this.confirmButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //@TODO implement a strategy to Confirm order
            }
        });
    }

    /**
     * Utility function for updating customer about order confirmation
     */
    private void updateCustomerView(){
        //@TODO implement a strategy to communicate order status to customer
    }

    /**
     *
     * @return List of all items in order
     */
    public ArrayList<Menu_Item> getOrderDetails() {
        return orderDetails;
    }

    /**
     *
     * @param orderDetails List of all items in order
     */
    public void setOrderDetails(ArrayList<Menu_Item> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
