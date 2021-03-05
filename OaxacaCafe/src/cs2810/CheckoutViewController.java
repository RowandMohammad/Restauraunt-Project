package cs2810;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class CheckoutViewController {

  @FXML
  private Button backToOrder;

  @FXML
  private ListView<String> OrderList;
  
  @FXML
  private Button payOrderButton;

  @FXML
  void changeScreenButtonPushed(ActionEvent event) throws IOException {
    Stage stage = (Stage) backToOrder.getScene().getWindow();
    stage.close();
  }
  
  @FXML
  void changeToPaymentView(ActionEvent event) {
    //Method to switch to payment view
  }

  public void populateCheckout(ArrayList<Menu_Item> basket, Float price, String time) {
    String order = "";
    String completeOrder = "";
    for (int i = 0; i < basket.size(); i++) {
      order = order + basket.get(i).name + "  £" + basket.get(i).price + "\n";
    }
    completeOrder = time + "\n" + order;
    price = BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    OrderList.getItems().add(completeOrder);
    OrderList.getItems().add("Total Price: £" + price + "");

  }

}

