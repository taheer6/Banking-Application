/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author khant
 */

/*
Overview: User is an abstract class which is the parent class for Customer and Manager.
The user is responsible for the verifying the login of a user, logging out a user, and keeping track of is the user is logged in.
User is a mutable class, as logInState can change.

The abstraction function is:
AF(c) = an abstract bank application user A, 
where A's username is c.username, A's password is c.password, 
and A's logInState which is whether they are logged in or not is c.logInState.


The rep invariant is:
RI(c) = true if c.username != null && c.password != null.

*/

public abstract class User {
    
    protected String username;
    protected String password;
    protected boolean logInState = false;
    //constructor
    //Requires: Strings for username and password which are non-null
    //Effects: Intializes the username and password of subclass object.
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Requires: non-null Strings for attemptedUsername and attemptedPassword
    //Modifies:logInState of user
    //Effects: If attemptedUsername and attemptedPassword match the User's set username and password the logInState in changed to true. Else remains false
    public void login(String attemptedUsername, String attemptedPassword) {
        if (attemptedUsername.equals(username) && attemptedPassword.equals(password)) {
            System.out.println("Logging in");
            logInState = true;
        } 
        else {
            System.out.println("Invalid username or password");
            logInState = false;
        }
    }
    //Modifies:logInState of user
    //Effectts: Changes the logInState of User to false and prints logout message.
    public void logout() {
        logInState = false;
        System.out.println("Logging out");
    }
    //Effects: returns the logInState of the user.
    public boolean getLogInState() {
        return logInState;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    //Effects: Returns boolean depending on compliance with rep invariant.
    public boolean repOk(){
        if(username!=null||password!=null){
            return false;
        }
        return true;
    }

    //Effects: returns String representation of the user.
    @Override
    public String toString() {
        return "Username: " + username + ", Password: " + password + (logInState ? ", Logged in" : ", Logged out");
    }
}
