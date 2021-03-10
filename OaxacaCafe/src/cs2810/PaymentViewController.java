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
        //Create alert box that mentions the transaction was successful and returns to customer view
        System.out.println("Card details are correct");
      }
      else {
        //Create alert box that mentions invalid card details
      }
    }
    
    private boolean isValidCVC() {
      //TODO
      return true;
    }

    private boolean isValidExpiry() {
      String expiry = expiryField.getText();
      if (Pattern.matches("[0-9]{2}+[/]{1}+[0-9]{2}", expiry) && expiry != null ) { //check if expiry is a number and is only 3 digits
        return true;
      }            
      return false;
    }

    private boolean isValidCardNo() {
      String cardNo = cardNoField.getText();
      if (Pattern.matches("[0-9]{4}+[-]{1}+[0-9]{4}+[-]{1}+[0-9]{4}+[-]{1}+[0-9]{4}", cardNo) && cardNo != null) {
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
    
    public void setTotalPrice(Float price) {
      String strPrice = price.toString();
      totalPrice.setText("£" + strPrice);
    }
}
