package cs2810;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

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
    void BackToOrderingPressed(ActionEvent event) throws IOException {
		Parent menuViewParent = FXMLLoader.load(getClass().getResource("/CustomerView.fxml"));
		Scene menuViewScene = new Scene(menuViewParent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(menuViewScene);
		window.show();
	}

}
