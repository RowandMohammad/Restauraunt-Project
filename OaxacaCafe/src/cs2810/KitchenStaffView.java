package cs2810;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class KitchenStaffView {

    @FXML
    private ListView<String> ordersToCookView;

    @FXML
    private Button backToOrderingButton;

    @FXML
    void backToOrderingPressed(ActionEvent event) {
      ((Stage) backToOrderingButton.getScene().getWindow()).close();

    }

}
