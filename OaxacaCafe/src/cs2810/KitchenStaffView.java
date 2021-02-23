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
    
    
    public void confirmOrder(int index) {
      PendingOrderViewItem item = ordersToCookView.getItems().remove(index);
      ordersToDeliver.add(ordersToCook.get(index));
      ordersToCook.remove(index);
      updateIndex(ordersToCookView, item.getIndex());
      this.parent.updateOrdersToCook(ordersToCook);
      this.parent.updateOrdersToDeliver(ordersToDeliver);
      ordersToCookView.refresh();
  }
    
    private void updateIndex(ListView<PendingOrderViewItem> ordersToCookView, int currentIndex) {
      for (int i = 0; i < ordersToCook.size(); i++) {
        
        if (ordersToCookView.getItems().get(i).index > currentIndex) {
          ordersToCookView.getItems().get(i).index --;
        }
        
      }
      
    }

}
