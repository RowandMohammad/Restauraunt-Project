package cs2810;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * The controller class and GUI for Changing a pending Order
 * 
 * @author Erikas Vieraitis
 * 
 */
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

    
    
    
    /**
     * @author Erikas Vieraitis
     * 
     * FXML method which is called when the add to order button is pressed
     * 
     * @param event
     */
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

    
    
    /**
     * @author Erikas Vieraitis
     * 
     * FXML method which is called when the back button is pressed
     * 
     * @param event
     */
    @FXML
    void backPressed(ActionEvent event) {
      ((Stage) backButton.getScene().getWindow()).close();
    }

    
    
    /**
     * @author Erikas Vieraitis
     * 
     * FXML method which is called when the delete item button is pressed
     * 
     * @param event
     */
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
    
    
    
    /**
     * @author Erikas Vieraitis
     * 
     * Utility function for initialising all of the data for the change order view
     * 
     * @param parent holds the ViewCustomerInterface class parent
     * @param parent2 holds the WaiterViewController class parent
     * @param pendingOrders holds an array list containing the pending orders 
     * @param index holds the int position of the current selected order to change
     * @throws IOException
     */
    void setInitialData(ViewCustomerInterface parent, WaiterViewController parent2, ArrayList<Order> pendingOrders, int index) throws IOException {
      this.parent = parent;
      this.parent2 = parent2;
      this.pendingOrders = pendingOrders;
      this.index = index;
      populateOrder(pendingOrders, index);
      populateMenuItems();
    }
    
    
    /**
     * @author Erikas Vieraitis
     * 
     * Utility class used to populate all the menu items into the view
     * 
     * @throws IOException
     */
    @SuppressWarnings("static-access")
	void populateMenuItems() throws IOException {
      menuItems.addAll(menuItemInit.initialiseMainItems());
      menuItems.addAll(menuItemInit.initialiseSideItems());
      menuItems.addAll(menuItemInit.initiliseDrinkItems());
      
      for (int i = 0; i < menuItems.size(); i++) {
        allMenuItemsView.getItems().add(menuItems.get(i).name);
      }
    }

    
    /**
     * @author Erikas Vieraitis
     * 
     * Utility class used to populate the items in the current selected order
     * 
     * @param pendingOrders holds the array list containing all pending order items
     * @param index holds the int position of the current selected order
     */
    void populateOrder(ArrayList<Order> pendingOrders, int index) {
      for (int i = 0; i < pendingOrders.get(index).getOrder().size(); i++) {
        currentOrderItemsView.getItems().add(pendingOrders.get(index).getOrder().get(i).name);
      }
    }

}