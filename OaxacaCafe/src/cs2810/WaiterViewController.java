package cs2810;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

public class WaiterViewController {
  
    private ViewCustomerInterface parent;
  
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

	public void setPendingOrders(ArrayList<ArrayList<Menu_Item>> pendingOrders) {
		this.pendingOrders = pendingOrders;
	}

	@FXML
	void BackToOrderingPressed(ActionEvent event) throws IOException {
		((Stage) BackToOrdering.getScene().getWindow()).close();
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
		        PendingOrderViewItem item = PendingOrdersView.getItems().remove(index);
		        pendingOrders.remove(index);
		        updateIndex(PendingOrdersView, item.getIndex());
		        this.parent.updatePendingOrders(pendingOrders);
		        item.setPending(false);
				PendingOrdersView.refresh();

			} else {
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
		pendingOrders.remove(index);
		updateIndex(PendingOrdersView, item.getIndex());
        this.parent.updatePendingOrders(pendingOrders);
		item.setPending(false);
		OrdersToDeliverView.getItems().add(item);
		PendingOrdersView.refresh();
		OrdersToDeliverView.refresh();
	}

  private void updateIndex(ListView<PendingOrderViewItem> pendingOrdersView, int currentIndex) {
    for (int i = 0; i < pendingOrders.size(); i++) {
      
      if (pendingOrdersView.getItems().get(i).index > currentIndex) {
        pendingOrdersView.getItems().get(i).index --;
      }
      
    }
    
  }

  /**
	 * Utility function for moving order from deliverable state to delivered
	 * 
	 * @param index of order which have to be moved to delivered
	 */
	public void deliverOrder(int index) {
		OrdersToDeliverView.getItems().remove(index);
		OrdersToDeliverView.refresh();
	}
	
	public void setInitialData(ViewCustomerInterface parent, ArrayList<ArrayList<Menu_Item>> pendingOrders){
      this.parent = parent;
      setPendingOrders(pendingOrders);
      populatePending(pendingOrders);
      System.out.println("pendingOrderssize: "+ pendingOrders.size());
  }
}