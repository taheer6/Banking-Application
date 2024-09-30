/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

import java.io.IOException;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author khant
 * 
 * Overview: This class is the controller for the Manager page of the banking application.
 * It is responsible for handling the user interface for the adding and deleting of customers, as well as the logging out from the manager page.
 * It interacts with the Manger class to perform these processes by handling user actions from the GUI. 
 * This class itself is immutable as it does not change however its involved in making changes to the application and it customer dataBase.
 * 
 * The Abstraction Function is:
 * AF(c) = A Manager Controller C in the banking application where
 * c.manager is the manager instance driving the operations of the controller
 * 
 * The Rep Invariant is:
 * RI(c) = true if manger != null
 * 
 */
public class ManagerController {
    
    private static Manager manager;

    @FXML
    private TextField customerUsername;
    @FXML
    private TextField customerPassword;
    @FXML
    private Button logout;
    
    @FXML
    private Button add;
    @FXML
    private Button deleteButton;
    
    @FXML
    private TextField deleteField;
    
    @FXML
    private Label customerAdded;
    
    @FXML
    private Label deleteLabel;
    //Requires: a non-null Manager object 'manager'
    //Effects: Assigns the manager instance to the Manager Controller.
    public void setManager(Manager manager) {
        ManagerController.manager = manager;
    }
    
    //Requires: a non-null ActionEvent 'event'
    //Modifies: this
    //Effects: Switches the scene to login scene 'login.fxml' and logs out manager object
    public void switchlogin(ActionEvent event) throws IOException {

        System.out.println("out");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent loginView = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(loginView);
        stage.setScene(scene);
        stage.show();
        manager.logout();
    }
    
    //Requires: A non-null ActionEvent 'event', non-null String 'customerUsername', and non-null String 'customerPassword' from textFields.
    //Modifies: this
    //Effects: Creates new customer with specified username and password and adds it to dataBase through manager class method.
    public void handleAddCustomer(ActionEvent event) {
        String username = customerUsername.getText();
        String password = customerPassword.getText();
        Customer newCustomer = new Customer(username, password, 100); // Assuming a default balance
        manager.addCustomer(newCustomer);
        customerUsername.clear();
        customerPassword.clear();
        customerAdded.setText("Customer Added.");
        System.out.println("Added customer: " + newCustomer.getUsername());
        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished(e -> customerAdded.setText(""));
        delay.play();

    }
    
    @FXML
    //Requires: A non-null ActionEvent 'event and non-null String 'usernameToDelete'
    //Modifies: this
    //Effects: Attemps to find a customer based of the specified username and removes it from the dataBase using the manager class method. Message on success of deletion is displayed.
    public void handleDeleteCustomer(ActionEvent event) {
        String usernameToDelete = deleteField.getText();
        if(usernameToDelete != null && !usernameToDelete.isEmpty()) {
            
        if (manager.deleteCustomer(usernameToDelete)) {
            deleteLabel.setText("Customer Deleted.");
        } 
        else {
            deleteLabel.setText("Customer not found.");
        }
        deleteField.clear();
        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished(e -> deleteLabel.setText(""));
        delay.play();
        } 
    }
    //Effects: If the rep invariant does not hold returns false. Otherwise returns true.
    public boolean repOk(){
        if (manager==null){
            return false;
        }
        return true;
    }
    //Effects: Returns string representation of the ManagerController.
    @Override
    public String toString(){
        return "Manager instance: " + manager.toString(); 
    }
    
}
