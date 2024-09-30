/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author khant
 * 
 * Overview : Customer is a mutable class which is subclass of the user class representing a Customer type user of the application
 * A Customer processes a username, password, a Customer Level, and an (bank) Account,
 * The class is responsible for performing customer specific activities in the app including;
 * deposit,withdraw, and purchases which involve error handling.
 * Also includes setting the customer's level and updating user info in the data Base after performing actions , which determines/updates customerLevel State which demonstrates state design pattern
 * The 'role' constant signifies the type of user.
 * 
 *The abstraction function is:
 * AF(c) = a customer c in the banking application where
 * c.username is the customer's username,
 * c.password is the customer's password,
 * c.account is the customer's bank account with a balance 
 * c.customerLevel is the customer's membership level based on their account balance.
 * 
 * The rep invariant is:
 * RI(c) = true if c.account.getBalance() >= 0 && c.customerLevel != null && c.customerLevel.getFee() >= 0
 * 
 */
public class Customer extends User {
    
    private static final String role = "Customer";
    private CustomerLevel customerLevel;
    private final Account account;
    BankDataBase dataBase;
    //Contructor
    //Requires: non-null Strings 'username' and 'password' and a non-negative double for 'initialAmount'
    //Modifies: Creates a new instance of Customer and alters the accountBalance of the Account and sets the level.
    //Effects: Initializes the new instance of Customer and error handles negative initial ammounts
    public Customer(String username, String password, double initialAmount){
        
        super(username,password);
        account = new Account(initialAmount);
        if(account.repOk() == false){
            System.out.println("Invalid intial amount. Initial amount must be greater than 0");
            account.setBalance(0);
        }
        setLevel();
        dataBase = new BankDataBase();
    }
    
    //Modifies: the customerLevel (state)
    //Effects: the customerLevel (state) of the customer is determined based of the current account Balance.
    public void setLevel(){
        if (account.getBalance()<10000){
            customerLevel = new SilverLevel();
        }
        else if (account.getBalance()>=10000 && account.getBalance()<20000){
            customerLevel = new GoldLevel();
        }
        else if (account.getBalance()>= 20000){
            customerLevel = new PlatinumLevel();
        }
    }
    //Requires: a non-negative double 'amount'
    //Modifies: accountBalance of the Account and the possibly customerLevel (state)
    //Effects: Error handles invalid inputs of 'amount', and updates the accountBalance by 'amount', updates the customerLevel (state), and writes it to dataBase info for this Customer.
    public void deposit(double amount){
        if (amount <=0){
            throw new IllegalArgumentException("Invalid deposit amount. Please deposit more than $0.00.");
        }
        account.setBalance(account.getBalance() + amount);
        setLevel();
        
        String userDetails = "Username: " + getUsername() + "\nPassword: " + getPassword() + "\nBalance: "+ getBalance() + 
                "\nLevel: " + customerLevel.getLevel() + "\nRole: "+ getRole();
        dataBase.writeFile(getUsername() + ".txt", userDetails);
        
        
    }
    
    //Requires: A non-negative double 'amount' that is less than the current accountBalance
    //Modifies: accountBalance of the customer 'Account' and possibly the customerLevel (state)
    //Effects: Error handles invalid 'amount' input. Decreases the accountBalance by 'amount', updates the customerLevel (state), and writes changes to dataBase info of the Customer.
    public void withdraw(double amount){
        if (amount <=0){
            throw new IllegalArgumentException("Invalid withdrawal amount. Please withdraw more than $0.00.");
        }
        else if ((account.getBalance() - amount - customerLevel.getFee()) < 0) {
            throw new IllegalStateException("Insufficient funds.");
        }
        else{
            account.setBalance(account.getBalance() - amount);
            setLevel();
            String userDetails = "Username: " + getUsername() + "\nPassword: " + getPassword() +
                    "\nBalance: " + getBalance() + "\nLevel: " + customerLevel.getLevel() + "\nRole: " + getRole();
            dataBase.writeFile(getUsername() + ".txt", userDetails);
        }   
    }
    
    //Effects: returns the current customerLevel as a string
    public String getLevel(){
        return customerLevel.getLevel();
    }
    //Effects: Returns the double 'fee' of the current customerLevel
    public double getFee(){
        return customerLevel.getFee();
    }
    //Effects: Returns the String username of the customer
    public String getUsername(){
        return username;
    }
    //Effects: Returns the String password of the customer
    public String getPassword(){
        return password;
    }
    //Effects: Returns the double balance of the Account of the customer
    protected double getBalance(){
        return account.getBalance();
    }
    //Effects: Returns the String of the constant Role
    public String getRole(){
        return role;
    }

    //Modifies: logInState 
    //Effects: Changes logInState to false
    @Override
    public void logout(){
        super.logout();
    }
    //Requires : a double 'purchaseAmount' has a values of at least 50 that is less than the accountBalance when the purchase fee is accounted for.
    //Modifies: accountBalance of Account and possible customerLevel (state)
    //Effects: Decreases the account Balance of Account by 'purchaseAmount' and purchase fee. Updates the customerLevel and writes changes to dataBase info for the Customer.
    public void purchase(double purchaceAmount){
        
        if(purchaceAmount<50){
            throw new IllegalArgumentException("Invalid purchase value. Please purchase must be at least $50.00");
        }
        else if((account.getBalance() - purchaceAmount - customerLevel.getFee()) < 0){
            throw new IllegalArgumentException("Insufficient funds for the purchase.");
        }
        else{
            account.setBalance(account.getBalance() - purchaceAmount - customerLevel.getFee());
            setLevel();
            String userDetails = "Username: " + getUsername() + "\nPassword: " + getPassword() +
                    "\nBalance: " + getBalance() + "\nLevel: " + customerLevel.getLevel() + "\nUser Type: " + getRole();
            dataBase.writeFile(getUsername() + ".txt", userDetails);
        }  
    }
    
    //Effects: return boolean false if the rep invariant does not hold, else return true.
    public boolean repOk(){
        if (account.getBalance()<0||customerLevel.getLevel()==null ||customerLevel.getFee()<0){
            return false;
        }
        return true;
    }
    //Effects: Returns string representation of the Customer object.
    @Override
    public String toString(){
        return "Username: " + getUsername() +  ", Balance: $" + account.getBalance() +  ", Customer Level: "+ getLevel()+ ", Fee: $"+ getFee();
    }
}
