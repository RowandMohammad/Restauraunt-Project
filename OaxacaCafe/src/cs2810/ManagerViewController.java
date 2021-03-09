package cs2810;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ManagerViewController {
    private ViewCustomerInterface parent;

    @FXML
    private Button BackToOrdering;

    @FXML
    private Label UserLabel;

    @FXML
    private Button viewEmployee;

    @FXML
    void BackToOrderingPressed(ActionEvent event) {
    	((Stage) BackToOrdering.getScene().getWindow()).close();

    }

    @FXML
    void handleViewEmployee(ActionEvent event) {

    }

}
