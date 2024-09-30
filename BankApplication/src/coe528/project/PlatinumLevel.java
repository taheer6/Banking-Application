/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author khant
 * 
 * Overview: PlatinumLevel is a subclass of CustomerLevel which represents the Platinum customer level, which is state of the state design pattern.
 * The Class is responsible for defining specific characteristics including it's fee and membership title unique to the Platinum level membership (state)
 * This class provided these characteristics to the application using get methods. 
 * This class immutable as fee and level of this state cannot be changed and are defined at instantiation.
 * 
 * The abstraction function is:
 * AF(c) = a Platinum level state P in the banking application, where
 * c.level is the title of the Platinum level (state) P
 * c.fee is the purchase fee of the Platinum level (state) P
 * 
 * The rep invariant is:
 * RI(c) = true if c.level.equals("Platinum")&&c.fee==0
 * 
 */
public class PlatinumLevel extends CustomerLevel {
    
     public PlatinumLevel(){
        this.level = "Platinum";
        this.fee = 0;
        
    }
    
    //Effects: Returns the string representation level of the PlatinumLevel (state)
    @Override
    public String getLevel(){
        return level;
    }
    //Effects: Returns the double purchase fee of the PlatinumLevel (state) 
    @Override 
    public double getFee(){
        return fee;
    }
    //Effects: return boolean false if the rep invariant does not hold, else return true.
    public boolean repOk(){
        return (getLevel().equals("Platinum") || getFee()==0);
    }

    //Effects: Returns string representation of the PlatinumLevel.
    @Override
    public String toString(){
        return "Level: " + getLevel()+ ", Fee: $"+ getFee();
    }
}
