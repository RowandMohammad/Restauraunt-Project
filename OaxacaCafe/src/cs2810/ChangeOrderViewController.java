package cs2810;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ChangeOrderViewController {
  
  ArrayList<Order> pendingOrders;
  ArrayList<Menu_Item> menuItems = new ArrayList<Menu_Item>();
  MenuMain menuItemInit = new MenuMain();
  private ViewCustomerInterface parent;
  private WaiterViewController parent2;
  int index;
  
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
      int index2 = allMenuItemsView.getSelectionModel().getSelectedIndex();
      if (index2 >= 0) {
        pendingOrders.get(index).getOrder().add(menuItems.get(index2));
        currentOrderItemsView.getItems().clear();
        populateOrder(pendingOrders, index);
        parent.updatePendingOrders(pendingOrders);
        parent2.updatePendingOrders(pendingOrders);
    }

      
    }

    @FXML
    void backPressed(ActionEvent event) {
      ((Stage) backButton.getScene().getWindow()).close();
    }

    @FXML
    void deleteItemPressed(ActionEvent event) {
      int index2 = currentOrderItemsView.getSelectionModel().getSelectedIndex();
      if (index2 >= 0) {
        pendingOrders.get(index).getOrder().remove(index2);
        currentOrderItemsView.getItems().clear();
        populateOrder(pendingOrders, index);
        parent.updatePendingOrders(pendingOrders);
        parent2.updatePendingOrders(pendingOrders);
      }

    }
    
    void setInitialData(ViewCustomerInterface parent, WaiterViewController parent2, ArrayList<Order> pendingOrders, int index) throws IOException {
      this.parent = parent;
      this.parent2 = parent2;
      this.pendingOrders = pendingOrders;
      this.index = index;
      populateOrder(pendingOrders, index);
      populateMenuItems();
    }
    
    
    void populateMenuItems() throws IOException {
      menuItems.addAll(menuItemInit.initialiseMainItems());
      menuItems.addAll(menuItemInit.initialiseSideItems());
      menuItems.addAll(menuItemInit.initiliseDrinkItems());
      
      for (int i = 0; i < menuItems.size(); i++) {
        allMenuItemsView.getItems().add(menuItems.get(i).name);
      }
      
    }

    
    void populateOrder(ArrayList<Order> pendingOrders, int index) {
      
      for (int i = 0; i < pendingOrders.get(index).getOrder().size(); i++) {
        currentOrderItemsView.getItems().add(pendingOrders.get(index).getOrder().get(i).name);
      }
      
      
      
    }

}