package cs2810;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class CheckoutViewController {
  
  private Float totalPrice;

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
  void changeToPaymentView(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/PaymentView.fxml"));
    Parent root = loader.load();
    PaymentViewController controller = loader.getController();
    Stage window = (Stage) payOrderButton.getScene().getWindow();
    controller.setTotalPrice(totalPrice);
    window.setScene(new Scene(root));
  }

  public void populateCheckout(ArrayList<Menu_Item> basket, Float price, String time) {
    setTotalPrice(price);
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
  
  private void setTotalPrice(Float price) {
    totalPrice = price;
  }

}

