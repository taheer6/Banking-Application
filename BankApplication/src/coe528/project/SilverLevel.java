/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author khant
 * 
 * Overview: SilverLevel is a subclass of CustomerLevel which represents the Silver customer level, which is state of the state design pattern.
 * The Class is responsible for defining specific characteristics including it's fee and membership title unique to the Silver level membership (state)
 * This class provided these characteristics to the application using get methods. 
 * This class immutable as fee and level of this state cannot be changed and are defined at instantiation.
 * 
 * The abstraction function is:
 * AF(c) = a Silver level state L in the banking application, where
 * c.level is the title of the Silver level (state) L
 * c.fee is the purchase fee of the Silver level (state) L
 * 
 * 
 * The rep invariant is:
 * RI(c) = true if c.level.equals("Silver")&&c.fee==20
 * 
 */
public class SilverLevel extends CustomerLevel {
    
    public SilverLevel(){
        this.level = "Silver";
        this.fee = 20;
        
    }
    
    //Effects: Returns the string representation level of the SilverLevel (state) \
    @Override
    public String getLevel(){
        return level;
    }
    //Effects: Returns the double purchase fee of the SilverLevel (state) 
    @Override 
    public double getFee(){
        return fee;
    }
    //Effects: return boolean false if the rep invariant does not hold, else return true.
    public boolean repOk(){
        return (getLevel().equals("Silver") || getFee()==20);
    }

    //Effects: Returns string representation of the SilverLevel
    @Override
    public String toString(){
        return "Level: " + getLevel()+ ", Fee: $"+ getFee();
    }
}
