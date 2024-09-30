/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

import java.io.BufferedReader;
import java.io.IOException;
import javafx.application.Preloader;
import javafx.application.Preloader.ProgressNotification;
import javafx.application.Preloader.StateChangeNotification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 *
 * @author khant
 * 
 *  Overview: This class is the controller for the login page of the banking application.
 * It is responsible for handling the logging in functionality for the users of the banking application's GUI.
 * It interacts with the BankDataBase by obtaining user credentials and authenticating it with the login method of the User class.
 * After a successful authentication, the scene is changed to the manager or customer login depending on which type of user logged in.
 * This class is mutable as it changes the state of the application by changing the scene based on the type of Login
 * 
 * The abstraction function is:
 * AF(c) = A Login Controller C in the banking application where
 * c.usernameBar holds the user-inputted username,
 * c.passwordBar holds the user-inputted password,
 * c.wrongLogIn displays feedback to the user about the login process,
 * c.manager is the singleton instance of Manager used to authenticate the admin login.
 * 
 * The rep invariant is:
 * RI(c) = true if
 * manager != null 
 * dataBase != null
 * usernameBar != null && passwordBar != null 
 * 
 */
public class LoginController {
    Manager manager = Manager.getInstance("admin", "admin");

private static final BankDataBase dataBase = new BankDataBase();
@FXML
    private PasswordField passwordBar;
    @FXML
    private TextField usernameBar;
    @FXML
    private Label wrongLogIn;
    @FXML
    private Button verify;
    
    //Requires a non-null ActionEvent 'e'
    //Modifies: this
    //Effects: If login credentials of manager or customer are correct it changes their respective LogInState and changes the scene to the respected page and initilises user controller.
               //If not gives error message to label.
    @FXML
    public void handleLogin(ActionEvent e) throws IOException {
        wrongLogIn.setText("");
        
        String usernameInputted = usernameBar.getText();
        String passwordInputted = passwordBar.getText();
        
        if (usernameInputted.equals("admin")) {
            Manager manager = Manager.getInstance(usernameInputted, passwordInputted);
            manager.login(usernameInputted, passwordInputted);
            if (manager.getLogInState()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Manager.fxml")); 
                Parent root = loader.load(); // Load the FXML
                
                ManagerController managerController = loader.getController(); 
                managerController.setManager(manager); 
                
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow(); 
                Scene scene = new Scene(root); 
                stage.setScene(scene); 
                stage.setTitle("Welcome"); 
                stage.show(); // And this
            }
            else{
                wrongLogIn.setText("Invalid username or password. Please try again.");  
            }
            
        }
        else {
    Customer temp = getCustomerDetails(usernameInputted);
    if (temp != null) {
        temp.login(usernameInputted, passwordInputted);
        if (temp.getLogInState()) {
            System.out.println("Customer logged in successfully.");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Customer.fxml")); // Ensure the path is correct
                Parent root = loader.load();
                CustomerController controller = loader.getController();
                controller.setCustomer(temp); // Ensure this method is correctly implemented in CustomerController

                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("issue with loading customer page.");
            }
        } 
    } else {
        wrongLogIn.setText("Invalid username or password. Please try again.");
    }
        }
    }


    //Requires: A non-null String 'username'
    //Effects: Obtains customer details of specified username from dataBase and return new Customer if found. else returns null.
    private Customer getCustomerDetails(String username) {
    String customerData = dataBase.readFile(username + ".txt");
    if (!customerData.isEmpty()) {
        String[] dataLines = customerData.split("\n");
        if (dataLines.length >= 3) {
            String fileUsername = dataLines[0].split(":")[1].trim();
            String filePassword = dataLines[1].split(":")[1].trim();
            String balanceStr = dataLines[2].split(":")[1].trim();
            double balance = Double.parseDouble(balanceStr);

            if (fileUsername.equals(username)) {
                return new Customer(username, filePassword, balance);
            }
        }
    }
    return null;
}
    //Effects: If the rep invariant does not hold returns false. Otherwise returns true.
    public boolean repOk() {
    
    if (manager == null || dataBase == null || usernameBar== null || passwordBar == null){
        return false;
    }
    return true;
    }
    
    //Effects: Returns string representation of the LoginController.
    @Override
    public String toString(){
        return "Manager: "+ manager.toString() +" Username Bar: " + usernameBar.getText()+" Password Bar: " 
                + passwordBar.getText()+" Wrong Login Label: " + wrongLogIn.getText();
    }
}
