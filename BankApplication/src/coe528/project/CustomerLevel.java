/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author khant
 * 
 * Overview: The CustomerLevel class is as an abstract parent class to represent the customer's membership level, following the state design pattern. 
 * This class provides a foundation for the specific membership levels (silver,gold,platinum).
 * The class is considered mutable as the properties fee and level can change depending on the customer's account balance.
 * 
 *The abstraction function is:
 * AF(c) = a customer level L where,
 * c.fee is the purchase fee depending on customer level L
 * c.level is the title of the customer level L
 * 
 * The rep invariant is:
 * RI(c) = true if c.level!=null && c.fee>=0
 * 
 */
public abstract class CustomerLevel {
    
    protected double fee;
    protected String level;
    //Effects: Returns string reprsentation of the customerLevel
    public abstract String getLevel();
    //Effects: Returns the double represent the purchase 'fee' 
    public abstract double getFee();
    //Effects: return boolean false if the rep invariant does not hold, else return true.
    public boolean repOk(){
        if(getLevel()==null||getFee()<0){
            return false;
        }
        return true;
    }
    //Effects: Returns String representation of the CustomerLevel.
    @Override
    public String toString(){
        return "Level: " + getLevel()+ ", Fee: $"+ getFee();
    }
}
