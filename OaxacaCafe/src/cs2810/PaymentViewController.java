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
        System.out.println("Card details valid");
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
      //month Test
      String expMonth = expiryField.getText(0,2); //gets month
      String expYear = expiryField.getText(3,5); //gets Year
      
      if ((Pattern.matches("[0-9]{2}", expMonth)) && (Pattern.matches("[0-9]{2}", expYear))) { //checks data type of Year and Month
        int intMonth = Integer.parseInt(expMonth);
        
        if(intMonth <=12 && intMonth > 0) { //checks if Month is greater than 0 but less than or equal to 12
          return true;
        }
        else {
          alert.setContentText("Expiry month should be between 1-12, Please Try Again");
          alert.show();
          return false;
        }
      }else {
        alert.setContentText("Incorrect expiry date, Please Try Again");
        alert.show();
        return false;
      }
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
