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
	private ListView<String> PendingOrdersView;

	@FXML
	private ListView<String> OrdersToDeliverView;

	@FXML
	private Button BackToOrdering;
	@FXML
	private Label UserLabel;

	@FXML
	void BackToOrderingPressed(ActionEvent event) throws IOException {
		Stage stage = (Stage) BackToOrdering.getScene().getWindow();
		stage.close();
	}

	public void populatePending(ArrayList<ArrayList<Menu_Item>> pendingOrders) {
		for (int i = 0; i < pendingOrders.size(); i++) {
			System.out.println("Order:");
			String fullOrder = "";

			for (int j = 0; j < pendingOrders.get(i).size(); j++) {
				fullOrder = fullOrder + pendingOrders.get(i).get(j).name + "\n";
			}
			PendingOrdersView.getItems().add(fullOrder);
			PendingOrdersView.setCellFactory(param -> new CancelOrder());

		}
	}

}
