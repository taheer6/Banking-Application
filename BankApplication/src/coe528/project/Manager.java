/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khant
 * 
 * Overview: The Manager class is a subclass of the abstract User class which represents the manager type user of the banking application.
 * The Manager class utilizes the Singleton design pattern to ensure that only one instance of Manager exists. This is done through a private constructor and the getInstance() method for initilization.
 * The manager is responsible for admin tasks including; adding customers, obtaining, and deleting customer accounts, as well as interacting with the BankDataBase to apply these changes.
 * This class is mutable as the list of customers and the database records can change over time.
 * 
 * The abstraction function is:
 * AF(c) = a Manager M in the banking application where
 * customerList is List of customers of M
 * c.Role managers user type in the banking application
 * c.instance is the single instance M
 * c.dataBase is the the BankDataBase that the manager M has access to
 * 
 * The rep invariant is:
 * RI(c) = true if c.customerList!= null && c.dataBase!=null
 * 
 */
public class Manager extends User {
    
    private final List<Customer> customerList = new ArrayList<>();
    private static Manager instance = null;
    private final BankDataBase dataBase = new BankDataBase();
    private static final String Role = "Manager";
    
    
    //constructor
    //Requires: non-null Strings 'username' and 'password'
    //Modifies: this
    //Effects: creates the single instance of Manager class, initializing the username, password, writes it to the dataBase, and retrieves all customerInfo.
    private Manager(String username, String password) {
        super(username, password);
        dataBase.writeFile("admin.txt", "Username: " + username + "\nPassword: " + password + "\nRole: " + Role);

        List<String> customerFiles = dataBase.getAllFiles();
        for (String filename : customerFiles) {
            String customerData = dataBase.readFile(filename);
            String[] data = customerData.split("\n");
            if (data.length >= 3) {
                String usernameFromFile = data[0].split(": ")[1];
                String passwordFromFile = data[1].split(": ")[1];
                double balance = Double.parseDouble(data[2].split(": ")[1]);
                Customer customer = new Customer(usernameFromFile, passwordFromFile, balance);
                customerList.add(customer);
            } else {
                System.err.println("Incomplete data in: " + filename);
            }
        }
    }

    
    //Requirs: non-null Strings 'username' and 'password'
    //Effects: Creates a single instance for the Manager class with the inputs
    public static Manager getInstance(String username, String password) {
        if (instance == null) {
            instance = new Manager(username, password);
        }   
        return instance;
    }
    
    //Requires: non-null Customer
    //Modifies: 'customerList'
    //Effects: Adds a new customer to list and writes it to the dataBase.
    public void addCustomer(Customer customer) {
        customerList.add(customer);
            String userDetails = "Username: " + customer.getUsername() +
                         "\nPassword: " + customer.getPassword() +
                         "\nBalance: " + customer.getBalance() +
                         "\nLevel: " + customer.getLevel() +
                         "\nRole: " + customer.getRole(); 
                             
        dataBase.writeFile(customer.getUsername() + ".txt", userDetails);
    }
    //Requires: non-null String 'customerUsername' representing username of a customer.
    //Effects: Returns the object of Customer with a same username as the inputed username. Otherwise returns null.
    public Customer getCustomer(String customerUsername) {
        for (Customer customer : customerList) { 
            if (customer.getUsername().equals(customerUsername)) {
                return customer;
            }
        }
        return null;
    }
    //Requires: non-null String 'customerUsername'
    //Modifies: customerList
    //Effects: Removes the instance of Custoemr with the inputted username from the customerList if exists and returns true. Else return false.
    public boolean deleteCustomer(String customerUsername) {
        Customer toRemove = null;
        for (Customer customer : customerList) { 
            if (customer.getUsername().equals(customerUsername)) {
                toRemove = customer;
                break;
            }
        }
        if (toRemove != null) {
            customerList.remove(toRemove);
            dataBase.deleteFile(customerUsername + ".txt");
            return true;
        }
        else{return false;}
    }
    //Effects: Returns all files
    public List<String> getAllFiles() {
        return new ArrayList<>();
    }
    //Effects: If the rep invariant does not hold returns false. Otherwise returns true.
    public boolean repOk(){
        if(customerList==null||dataBase==null){
            return false;
        }
        return true;
    }
    
    //Effects: Returns String represenation of the Manager.
    @Override
    public String toString(){
        return "Username: " +username+", Role: "+Role+", Managing "+ customerList.size()+ " customers";
    }
}
