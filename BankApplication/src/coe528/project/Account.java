/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author khant
 * 
 * Overview: The Account class represents the bank Account of a Customer user of the banking application.
 * The class encapsulates the account balance and the balance is obtained through getBalance() for the use of
 * customers perform financial transactions such as deposits, withdrawals, and purchases. 
 * The class is mutable as the balance changes based of customer transactions. The balance of the account is also used for error handling of customer 
 * transactions and for setting the customer Level (membership State).
 * 
 *The abstraction function is:
 * AF(c) = an Account A in the banking application where
 * c.accountBalance is the balance of the customer bank Account A

 * The rep invariant is:
 * RI(c) = true if accountBalance>=0
 * 
 * 
 */
public class Account {
    
    private double accountBalance;
    
    //constructor
    //Requires: non-negative double 'initialAmount'
    //Effects: intializes the acountBalance
    public Account(double initialAmount){
        this.accountBalance = initialAmount;       
    }
    
    //Effects: Returns double of the current account balance
    public double getBalance(){
        return accountBalance;
    }
    
    //Requires: a non negative double 'balanceChange'
    //Modifies: accountBalance
    //Effects: Sets the accoutBalance to the value inputed based of customer transactions
    public void setBalance(double balanceChange){
        this.accountBalance = balanceChange;
        
    }
    //Effects: return boolean false if the rep invariant does not hold, else return true.
    public boolean repOk(){
        if(getBalance()<0){
            return false;
        }
        return true;
    }   
    
    //Effects: Returns String representation of the Account.
    @Override
    public String toString(){
        return "Account Balance: $" + getBalance();
    }
    
}
