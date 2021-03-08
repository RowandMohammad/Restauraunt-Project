package cs2810;

import java.util.regex.Pattern;
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
      if (isValidName() && isValidCardNo() && isValidExpiry() && isValidCVC() && isNull()) {
        //Create alert box that mentions that the transaction was successful
      }
      else {
        //Create alert box that mentions invalid card details
      }
    }
    
    private boolean isValidCVC() { //Hartik
      // TODO Auto-generated method stub
      String cvc = cvcField.getText();
      if (Pattern.matches("[0-9]{3}", cvc)) { //check if cvc is a number and is only 3 digits
        System.out.println("Test: CVC Works");
        return true;
      }            
      return false;
    }

    private boolean isValidExpiry() { // Hartik
      // TODO Auto-generated method stub
      return true;
    }

    private boolean isValidCardNo() { // Adam
      // TODO Auto-generated method stub
      return true;
    }

    private boolean isValidName() {
      String name = nameField.getText();
      if (Pattern.matches("[a-zA-Z' ']+", name) && name != null) {
        System.out.println("HI");
        return true;
      }               
      return true;
    }

    
    private boolean isNull() {
      return false;
    }
       
    @FXML
    void purchaseOrder(ActionEvent event) {
      checkCardDetails();
    }

}
