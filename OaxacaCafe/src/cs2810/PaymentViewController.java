package cs2810;

import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

public class PaymentViewController {
    private ViewCustomerInterface parent;
 
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
    
    /**
     * This function initialises all the listeners that are used to
     * auto-fill characters in the fields.
     *  
     */
    @FXML
    public void initialize() {
      setCardNoListener();
      setExpiraryListener();
      setCVCListener();
    }
    
    /**
     * Action event method that calls other functions when user presses purchase.
     * 
     * @param event the action event when purchase button is pressed
     */
    @FXML
    void purchaseOrder(ActionEvent event) {
      checkCardDetails();
    }
    
    /**
     * Checks card details via sub methods and outputs the corresponding alert notification
     * notifying whether the user input their details correctly.
     * 
     */
    private void checkCardDetails() {
      if (isValidExpiry() && isValidName() && isValidCardNo() && isValidCVC()) {
        Alert alert = new Alert(AlertType.INFORMATION, "Press OK to return to main menu.", ButtonType.OK);
        alert.setTitle("Transaction complete");
        alert.setHeaderText("Transaction was successfully processed. Thank you!");
        parent.setPayedOrders();
        ButtonType result = alert.showAndWait().orElse(ButtonType.OK);
        if (ButtonType.OK.equals(result)) {
          // Closes the payment UI and resets the total price in the customer UI back to £0.00
          Stage stage = (Stage) payButton.getScene().getWindow();
          stage.close();
          parent.setTotalPrice(0);
        }
      }
      else {
        Alert alert = new Alert(AlertType.ERROR, "Please re-enter your card details.", ButtonType.OK);
        alert.setTitle("Incorrect details");
        alert.setHeaderText("Incorrect card details");
        alert.showAndWait();
      }
    }
    
    /**
     * Used to validate the user's CVC card information via pattern matching.
     * 
     * @return returns true if the user's CVC input is valid otherwise false
     */
    private boolean isValidCVC() {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      String cvc = cvcField.getText();
      
      if(cvc.length() == 3) { //checks CVC Length
        if((Pattern.matches("[0-9]{3}", cvc))) {
          return true;
        }
        else {
          alert.setContentText("Incorrect, has to use numbers and be 3 numbers long, please try again");
          alert.show();
          return false;
        }  
      }
      else {
        alert.setContentText("Incorrect CVC length, it has to be 3 numbers long, please try again");
        alert.show();
        return false;
      }
    }

    /**
     * Used to validate the user's expiry date for their card via pattern matching.
     * 
     * @return returns true if the user's expiry date is valid otherwise false
     */
    private boolean isValidExpiry() {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      String expMonth = expiryField.getText(0,2); // Gets month
      String expYear = expiryField.getText(3,5); // Gets Year
      
      if ((Pattern.matches("[0-9]{2}", expMonth)) && (Pattern.matches("[0-9]{2}", expYear))) { //checks data type of Year and Month
        int intMonth = Integer.parseInt(expMonth); 
        if(intMonth <=12 && intMonth > 0) { //checks if Month is greater than 0 but less than or equal to 12
          return true;
        }
        else {
          alert.setContentText("Expiry month should be between 1-12, please try again");
          alert.show();
          return false;
        }
      }
      else {
        alert.setContentText("Incorrect expiry date, please try again");
        alert.show();
        return false;
      }
    }

    /**
     * Used to check whether the user's card number is valid via pattern matching.
     * 
     * @return returns true if the user's card number is valid otherwise false
     */
    private boolean isValidCardNo() {
      String cardNo = cardNoField.getText();
      if (Pattern.matches("[0-9]{4}+[-]{1}+[0-9]{4}+[-]{1}+[0-9]{4}+[-]{1}+[0-9]{4}", cardNo)) {
        return true;
      }               
      return false;
    }

    /**
     * Used to validate the user's name for their payment card.
     * 
     * @return returns true if the user's name uses letters and not number otherwise false
     */
    private boolean isValidName() {
      String name = nameField.getText();
      if (Pattern.matches("[a-zA-Z' ']+", name) && name != null) {
        return true;
      }               
      return false;
    }
    
    /**
     * Constantly listens to the user's input for the card number and auto-inserts dashes every 4 digts
     * and makes sure not to exceed over 16 digits and 3 dashes.
     * 
     */
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
    
    /**
     * Listens to the user's input for the expiry date by auto-inserting '/' when 2 digts are inserted
     * and makes sure need to exceed over 4 digits and a single '/'.
     * 
     */
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
    
    /**
     * Listens to the user's input for the CVC field by making sure that they are not exceeding 3 digits.
     * 
     */
    private void setCVCListener() { 
      cvcField.setTextFormatter(new TextFormatter<>(change -> {
        String cvc = change.getControlNewText();
        if (cvc.length() > 3) {
          return null;
        }
        return change;
      }));
    }
    
    /**
     * Sets the total price for the purchase using the same total price from the main customer view.
     * 
     * @param price the total price for the user's purchase 
     */
    public void setTotalPrice(Float price) {
      String strPrice = price.toString();
      totalPrice.setText("£" + strPrice);
    }

    /**
     * Sets the instance of the ViewCustomerInterface as the parent to this controller so it can
     * use its data and alter the parent's data when a purchase is made.
     * 
     * @param controller the parent controller that the payment is using information from
     */
    public void setParentController(ViewCustomerInterface controller) {
      parent = controller;
    }
}
