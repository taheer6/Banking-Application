/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package coe528.project;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author khant
 * 
 * Overview: This class is the Main class of the banking application and it's entry point which uses Java FX to create the GUI. It extends the application JavaFX library.
 * This class is responsible for is for setting up the initial stage and scene for the application, displaying the login page as designed in the corresponding FXML file. 
 * This setup initializes the GUI that users will interact with.
 * 
 * The abstraction function is:
 * AF(c) = a Main class of the banking application for initializes login scene
 *
 * 
 * The rep invariant is:
 * RI(c) = true 
 * 
 */
public class Main extends Application {
    
    //Requies: non-null Stage
    //Modifies: stage
    //Effects: Initializes the graphic user interface by setting the scene login.fmxl to the stage
    @Override
    public void start(Stage stage) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("AAB Banking Application");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    //Effects:Launches the JavaFX application.
    public static void main(String[] args) {
        launch(args);
    }
    
    //Effects: If the rep invariant does not hold returns false. Otherwise returns true.
    public boolean repOk() {
    return true;
    }
    
    //Effects: Returns the String representation of the Main class.
    @Override
    public String toString() {
        return "Main Class for the AAB Banking Application";
    }

    
}
