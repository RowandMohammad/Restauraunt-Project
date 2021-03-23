package cs2810;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ChangeOrderViewController {
  
  ArrayList<Order> pendingOrders;

    @FXML
    private ListView<String> currentOrderItemsView;

    @FXML
    private ListView<String> allMenuItemsView;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteItemButton;

    @FXML
    private Button addToOrderButton;

    @FXML
    void addToOrderPressed(ActionEvent event) {

    }

    @FXML
    void backPressed(ActionEvent event) {
      ((Stage) backButton.getScene().getWindow()).close();
    }

    @FXML
    void deleteItemPressed(ActionEvent event) {

    }
    
    void setInitialData(ArrayList<Order> pendingOrders) {
      this.pendingOrders = pendingOrders;
      populateOrder();
      populateMenuItems();
    }
    
    
    void populateMenuItems() {
      
    }

    
    void populateOrder() {
      
    }

}