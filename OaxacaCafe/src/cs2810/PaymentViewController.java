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
      boolean test = isValidCVC();
      System.out.println();
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
      
      return false;
    }

    private boolean isValidExpiry() { // Hartik
      // TODO Auto-generated method stub
      return false;
    }

    private boolean isValidCardNo() { // Adam
      // TODO Auto-generated method stub
      return false;
    }

    private boolean isValidName() {
      String name = nameField.getText();
      if (Pattern.matches("[a-zA-Z' ']+", name) && name != null) {
        System.out.println("HI");
        return true;
      }               
      return false;
    }

    
    private boolean isNull() {
      return false;
    }
       
    @FXML
    void purchaseOrder(ActionEvent event) {
      checkCardDetails();
    }

}
