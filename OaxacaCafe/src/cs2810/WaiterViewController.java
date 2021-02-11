package cs2810;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class WaiterViewController {

    @FXML
    private ListView<PendingOrderViewItem> PendingOrdersView;

    @FXML
    private ListView<PendingOrderViewItem> OrdersToDeliverView;

    @FXML
    private Button BackToOrdering;
    @FXML
    private Label UserLabel;

    private

    @FXML
    void BackToOrderingPressed(ActionEvent event) throws IOException {
      ((Stage) BackToOrdering.getScene().getWindow()).close();
	}
    
    public void populatePending(ArrayList<ArrayList<Menu_Item>> pendingOrders) {
        int index = 0;
        for (ArrayList<Menu_Item> order : pendingOrders) {
            PendingOrderViewItem item = new PendingOrderViewItem(this, order, index, true);
            PendingOrdersView.getItems().add(item);
            index ++;
        }
    }

    /**
     * Utility function for moving order from pending state to deliverable state
     * @param index of order which have to be moved to deliverable state
     */
    public void confirmOrder(int index) {
        PendingOrderViewItem item = PendingOrdersView.getItems().remove(index);
        item.setPending(false);
        OrdersToDeliverView.getItems().add(item);
        PendingOrdersView.refresh();
        OrdersToDeliverView.refresh();
    }

    /**
     * Utility function for moving order from deliverable state to delivered
     * @param index of order which have to be moved to delivered
     */
    public void deliverOrder(int index) {
        OrdersToDeliverView.getItems().remove(index);
        OrdersToDeliverView.refresh();
    }
}