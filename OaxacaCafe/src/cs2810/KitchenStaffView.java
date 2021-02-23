package cs2810;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class KitchenStaffView {
  
  private ViewCustomerInterface parent;
  ArrayList<Order> ordersToCook;
  ArrayList<Order> ordersToDeliver;

    @FXML
    private ListView<PendingOrderViewItem> ordersToCookView;

    @FXML
    private Button backToOrderingButton;
    

    @FXML
    void backToOrderingPressed(ActionEvent event) {
      ((Stage) backToOrderingButton.getScene().getWindow()).close();

    }

    public void initialiseData(ViewCustomerInterface parent, ArrayList<Order> ordersToCook, ArrayList<Order> ordersToDeliver) {
      this.ordersToDeliver = ordersToDeliver;
      this.ordersToCook = ordersToCook;
      this.parent = parent;
      populateOrdersToCook();
    }

    private void populateOrdersToCook() {
      int index = 0;
      for (Order order : ordersToCook) {
          PendingOrderViewItem item = new PendingOrderViewItem(this, order.getOrder(), index, true);
          ordersToCookView.getItems().add(item);
          index++;
      }
      
    }

}
