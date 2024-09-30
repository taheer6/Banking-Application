/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author khant
 * 
 * Overview: Overview: This class is the controller for the Customer page of the banking application and is in charge of managing the user interface of the customer interactions in the application.
 * The customer page is set to a specific customer depending on the login credentials
 * It is responsible for banking operations such as deposits, withdrawals, and purchases, and updates the user interface to reflect these actions. 
 * It also handles logging out of the customer page and going back to the login scene.
 * It interacts with the Customer class to perform these processes by handling user actions from the GUI. 
 * This class itself is mutable as it changes the labels of the customer details.
 * 
 * The Abstraction function is:
 * AF(c) = A Manager Controller C in the banking application where
 * c.customer is the customer instance currently interacting with the banking application, where the customer's username, balance, and membership level are displayed through the UI.
 * c.customerLabel, c.balanceLabel, and c.levelLabel are UI elements that reflect the state of c.customer in the banking application's user interface.
 * 
 * The Rep invariant is:
 * RI(c) = true if customer!=null
 * 
 */
public class CustomerController {
    
    private Customer customer;
    
    @FXML
    private Label customerLabel;
    
    @FXML
    private Label balanceLabel;
    @FXML
    private Label levelLabel;
    
    @FXML
    private Button customerLogout;
    
    @FXML
    private TextField depositField;
    
    @FXML
    private Label wrongDeposit;
    
    @FXML
    private Button depositButton;
    
    @FXML
    private TextField withdrawField;
    
    @FXML
    private Label wrongWithdraw;
    
    @FXML
    private Button withdrawButton;
    @FXML
    private TextField purchaseField;
    
    @FXML
    private Label purchaseLabel;
    
    @FXML
    private Button purchaseButton;
    
    //Requires: non-null object of Customer 'customer'
    //Modifies: this
    //effects: sets the specific customer object to the controller, sets username to dashboard label, and updates user information.
    public void setCustomer(Customer customer) {
        this.customer = customer;
        customerLabel.setText(customer.getUsername());
        
        userInfoRefresh();
    }
    
    //Requires: non-null ActionEvent 'event'
    //Modifies: this
    //Effects: Switches the scene to login scene 'login.fxml' and logs out Customer object
    public void customerSwitchLogin(ActionEvent event) throws IOException {

        System.out.println("out");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent loginView = loader.load();


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();


        Scene scene = new Scene(loginView);
        stage.setScene(scene);
        stage.show();
        customer.logout();
    }
    
    //Requires: A non-null ActionEvent 'event' a non-null numeric String for deposit amount from textField
    //Modifies: this
    //Effects: Error handles for valid amount and adds the amount from textfield to the account balance, and updates user info.
    public void customerDeposit(ActionEvent event) {
    double depositAmount;
    
    wrongDeposit.setText(" ");
    try {
        depositAmount = Double.parseDouble(depositField.getText());
    } catch (NumberFormatException e) {
        wrongDeposit.setText("Invalid deposit amount. Please enter a numeric value."); 
        return;
    }
    
    try {
        customer.deposit(depositAmount);
    } catch (IllegalArgumentException e) {
        wrongDeposit.setText(e.getMessage()); 
        return;
    }
    
    
    userInfoRefresh();
    
    
    depositField.clear();
}
    
    //Requires: a non-null ActionEvent 'event' and a non-null numeric String for the amount inputed throught textField
    //Modifies: This
    //Effects: Error handldle for valid inputs ammount and decreases the customer's Account balance (making withdrawl) by the specified amount by user in textField.
    public void handleWithdraw(ActionEvent event) {
    double withdrawAmount;

    wrongWithdraw.setText(""); 
    try {
        withdrawAmount = Double.parseDouble(withdrawField.getText());
    } catch (NumberFormatException e) {
        wrongWithdraw.setText("Invalid withdrawal amount. Please enter a numeric value.");
        return;
    }

    try {
        customer.withdraw(withdrawAmount);
    } catch (IllegalArgumentException e) {
        wrongWithdraw.setText(e.getMessage());
        return;
    } catch (IllegalStateException e) {
        wrongWithdraw.setText(e.getMessage());
        return;
    }

    userInfoRefresh();

    withdrawField.clear();
}
   //Requires: A non-null ActionEvent 'event' and a non-null numeric String inputted by user in textField
    //Modifies: This
    //Effects: Error handles for valid purchase inputs and makes a purchase transaction with specified amount from textField and updates info.
   public void handlePurchase(ActionEvent event) {
    double purchaseAmount;
    try {
        purchaseAmount = Double.parseDouble(purchaseField.getText());
    } catch (NumberFormatException e) {
        purchaseLabel.setText("Invalid purchase amount. Please enter a numeric value.");
        return;
    }

    try {
        customer.purchase(purchaseAmount);
        userInfoRefresh();

        purchaseField.clear();
        purchaseLabel.setText("");
    } catch (IllegalArgumentException e) {
        purchaseLabel.setText(e.getMessage());
    } 
}
   //Modifies: balanceLabel and levelLabel
   //Effects: Updates the balanceLabel and levelLabel for a certain customer.
   private void userInfoRefresh(){
       balanceLabel.setText(String.format("$%.2f", customer.getBalance()));
       levelLabel.setText(customer.getLevel());
       
   }
   //Effects: If the rep invariant does not hold returns false. Otherwise returns true.
   public boolean repOk(){
       if(customer==null){
           return false;
       }
       return true;
   }
   //Effects: Returns string representation of the CustomerController.
   @Override
   public String toString(){
       return "Customer username: " + customerLabel.getText() + "Customer balance: " + balanceLabel.getText() + "Customer level: " + levelLabel.getText() ;
   }

}
