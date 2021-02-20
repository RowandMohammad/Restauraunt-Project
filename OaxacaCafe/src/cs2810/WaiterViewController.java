package cs2810;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class WaiterViewController {
    ArrayList<ArrayList<Menu_Item>> pendingOrders;

    @FXML
    private ListView<PendingOrderViewItem> PendingOrdersView;

    @FXML
    private ListView<PendingOrderViewItem> OrdersToDeliverView;

    @FXML
    private Button BackToOrdering;
    @FXML
    private Label UserLabel;
    @FXML
    private Button CancelOrder;

    private ViewCustomerInterface parent;

    public void setPendingOrders(ArrayList<ArrayList<Menu_Item>> pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    @FXML
    void BackToOrderingPressed(ActionEvent event) throws IOException {
        ((Stage) BackToOrdering.getScene().getWindow()).close();
    }

    public void setInitialData(ViewCustomerInterface parent, ArrayList<ArrayList<Menu_Item>> pendingOrders) {
        this.parent = parent;
        this.populatePending(pendingOrders);
    }

    public void populatePending(ArrayList<ArrayList<Menu_Item>> pendingOrders) {
        int index = 0;
        for (ArrayList<Menu_Item> order : pendingOrders) {
            PendingOrderViewItem item = new PendingOrderViewItem(this, order, index, true);
            PendingOrdersView.getItems().add(item);
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

    void cancelConfirmation(int index) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setContentText("Are you sure you want to cancel this order?");
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(yesButton, cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == yesButton) {
                PendingOrdersView.getItems().remove(index);
                PendingOrdersView.refresh();
                updateOrderStatus("Canceled");
            }
        });
    }

    /**
     * Utility function for moving order from pending state to deliverable state
     *
     * @param index of order which have to be moved to deliverable state
     */
    public void confirmOrder(int index) {
        PendingOrderViewItem item = PendingOrdersView.getItems().remove(index);
        item.setPending(false);
        OrdersToDeliverView.getItems().add(item);
        PendingOrdersView.refresh();
        OrdersToDeliverView.refresh();
        updateOrderStatus("In process");
    }

    /**
     * Utility function for moving order from deliverable state to delivered
     *
     * @param index of order which have to be moved to delivered
     */
    public void deliverOrder(int index) {
        OrdersToDeliverView.getItems().remove(index);
        OrdersToDeliverView.refresh();
        updateOrderStatus("Delivered");
    }

    private void updateOrderStatus(String status) {
        this.parent.setOrderStatus(status);
    }

}