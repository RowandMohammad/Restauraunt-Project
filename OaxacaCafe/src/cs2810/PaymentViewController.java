package cs2810;

import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

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
    
    @FXML
    private Label totalPrice;
    
    @FXML
    public void initialize() {
      setCardNoListener();
      setExpiraryListener();
    }
    
    @FXML
    void purchaseOrder(ActionEvent event) {
      checkCardDetails();
    }

    private void checkCardDetails() {
      if (isValidName() && isValidCardNo() && isValidExpiry() && isValidCVC()) {
        //Create alert box that mentions that the transaction was successful
      }
      else {
        //Create alert box that mentions invalid card details
      }
    }
    
    private boolean isValidCVC() {
      String cvc = cvcField.getText();
      if (Pattern.matches("[0-9]{3}", cvc) && cvc != null ) { //check if cvc is a number and is only 3 digits
        System.out.println("Test: CVC Works");
        return true;
      }            
      return false;
    }

    private boolean isValidExpiry() {
      // TODO Auto-generated method stub
      return false;
    }

    private boolean isValidCardNo() {
      String cardNo = cardNoField.getText();
      if (Pattern.matches("[0-9-]{19}", cardNo) && cardNo != null) {
        return true;
      }               
      return false;
    }

    private boolean isValidName() {
      String name = nameField.getText();
      if (Pattern.matches("[a-zA-Z' ']+", name) && name != null) {
        return true;
      }               
      return false;
    }
    
    private void setCardNoListener() {
      cardNoField.setTextFormatter(new TextFormatter<>(change -> {
        String cardNo = change.getControlNewText();
        if (cardNo.length() > 19) {
          return null;
        }
        if (change.getCaretPosition() == 5 || change.getCaretPosition() == 10 || change.getCaretPosition() == 15) {
          change.setText("-");
        }
        return change;
      }));
    }
    
    private void setExpiraryListener() {
      expiryField.setTextFormatter(new TextFormatter<>(change -> {
        String expiryNo = change.getControlNewText();
        if (expiryNo.length() > 5) {
          return null;
        }
        if (change.getCaretPosition() == 3) {
          change.setText("/");
        }
        return change;
      }));
    }
    
}
