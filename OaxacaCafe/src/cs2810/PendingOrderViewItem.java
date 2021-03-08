package cs2810;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


import java.util.ArrayList;

/**
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
    private boolean isPending;

    /**
     * No default initialization allowed
     * @param isPending
     * @param index
     * @param arrayList
     * @param kitchenStaffView
     */


    /**
     * Parametrized constructor for initializing new order view object
     *
     * @param orderDetails: List of items selected for order
     */
    public PendingOrderViewItem(WaiterViewController parentController, ArrayList<Menu_Item> orderDetails,
                                int index, boolean isPending) {
        this.parentController = parentController;
        this.index = index;
        this.isPending = isPending;
        this.orderDetails = orderDetails;
        this.setUpView();
        this.setupCallback("waiter");
    }


    public PendingOrderViewItem(KitchenStaffView parentController2, ArrayList<Menu_Item> orderDetails, int index, boolean isPending) {
        this.parentController2 = parentController2;
        this.index = index;
        this.isPending = isPending;
        this.orderDetails = orderDetails;
        this.setUpView();
        this.setupCallback("kitchen");

    }

    /**
     * Utility function for initializing view
     */
    private void setUpView() {
        boolean isFirst = true;
        this.setAlignment(Pos.CENTER_LEFT);
        this.confirmButton = new Button("Confirm");
        HBox.setMargin(this.confirmButton, new Insets(0, 0, 0, 300));
        this.orderDetailLabel = new Label();
        String orderDetail = "";

        for (Menu_Item menuItem : orderDetails) {
            if (isFirst) {
                orderDetail += "Order Time: " + menuItem.getPurchaseDate() + "\n" + "Order Item(s):\n" + menuItem.name + "\n";
                isFirst = false;
            } else {
                orderDetail += menuItem.name + "\n";
            }
        }
        this.orderDetailLabel.setText(orderDetail);
        this.getChildren().addAll(orderDetailLabel, confirmButton);
    }

    /**
     * Utility function for assigning callback function to button click and confirming order
     *
     * @param staff
     */
    private void setupCallback(String staff) {
        this.confirmButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isPending) {
                    if (staff.equals("waiter")) {
                        parentController.confirmOrder(index);
                    } else if (staff.equals("kitchen")) {
                        parentController2.confirmOrder(index);
                        isPending = false;
                    }
                } else {
                    parentController.deliverOrder(index);
                }

            }
        });
    }

    /**
     * Utility function for updating customer about order confirmation
     */
    private void updateCustomerView() {
        //@TODO implement a strategy to communicate order status to customer
    }

    /**
     * @return List of all items in order
     */
    public ArrayList<Menu_Item> getOrderDetails() {
        return orderDetails;
    }

    /**
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

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }
}
