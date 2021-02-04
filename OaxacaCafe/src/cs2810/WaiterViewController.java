package cs2810;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class WaiterViewController {

    @FXML
    private ListView<?> PendingOrdersView;

    @FXML
    private ListView<?> OrdersToDeliverView;

    @FXML
    private Button BackToOrdering;

    @FXML
    private Label UserLabel;

    @FXML
    void BackToOrderingPressed(ActionEvent event) {
      System.out.println("leg");

    }

}
