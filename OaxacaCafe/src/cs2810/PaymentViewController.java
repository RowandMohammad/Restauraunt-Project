package cs2810;

import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class PaymentViewController {
  Alert alert = new Alert(Alert.AlertType.ERROR);
  
  
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
      if (isValidExpiry() && isValidName() && isValidCardNo() && isValidCVC()) {
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
      //month Test
      System.out.println("function is working");
      String expMonth = expiryField.getText(0,2);
      String expYear = expiryField.getText(3,5);
      if ((Pattern.matches("[0-9]+", expMonth)) && (Pattern.matches("[0-9]+", expYear))) { //checks data type of Year and Month
        int intMonth = Integer.parseInt(expMonth);
        
        if(intMonth<=12) {
          return true;
        }
      }else {
        alert.setContentText("Incorrect Expiry Date , Please Try Again");
        alert.show();
        return false;
      }
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
    
    private void setCardNoListener() { // Card Number Auto-Fill of "-"
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
    
    private void setExpiraryListener() { //Expire Auto Fill of "/"
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
