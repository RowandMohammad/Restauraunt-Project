package cs2810;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PaymentViewController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField cardNoField;

    @FXML
    private TextField expiryField;

    @FXML
    private TextField cvcField;

    @FXML
    private Button payButton;
    
    private void checkCardDetails() {
      if (isValidName() && isValidCardNo() && isValidExpiry() && isValidCVC()) {
        //Create alert box that mentions that the transaction was successful
      }
      else {
        //Create alert box that mentions invalid card details
      }
    }
    
    private boolean isValidCVC() {
      // TODO Auto-generated method stub
      return false;
    }

    private boolean isValidExpiry() {
      // TODO Auto-generated method stub
      return false;
    }

    private boolean isValidCardNo() {
      // TODO Auto-generated method stub
      return false;
    }

    private boolean isValidName() {
      // TODO Auto-generated method stub
      return false;
    }

    @FXML
    void purchaseOrder(ActionEvent event) {
      checkCardDetails();
    }

}
