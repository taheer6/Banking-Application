/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author khant
 * 
 * Overview: GoldLevel is a subclass of CustomerLevel which represents the Gold customer level, which is state of the state design pattern.
 * The Class is responsible for defining specific characteristics including it's fee and membership title unique to the Gold level membership (state)
 * This class provided these characteristics to the application using get methods. 
 * This class immutable as fee and level of this state cannot be changed and are defined at instantiation.
 * 
 * The abstraction function is:
 * AF(c) = a Gold level state G in the banking application, where
 * c.level is the title of the Gold level (state) G
 * c.fee is the purchase fee of the Gold level (state) G
 * 
 * 
 * The rep invariant is:
 * RI(c) = true if c.level.equals("Gold")&&c.fee==10
 * 
 * 
 */
public class GoldLevel extends CustomerLevel{
        
    public GoldLevel(){
        this.level = "Gold";
        this.fee = 10;
        
    }
    
    //Effects: Returns the string representation level of the GoldLevel (state)
    @Override
    public String getLevel(){
        return level;
    }
    //Effects: Returns the double purchase fee of the GoldLevel (state) 
    @Override 
    public double getFee(){
        return fee;
    }
    //Effects: return boolean false if the rep invariant does not hold, else return true.
    public boolean repOk(){
        return (getLevel().equals("Gold") || getFee()==10);
    }

    //Effects: Returns string representation of the GoldLevel
    @Override
    public String toString(){
        return "Level: " + getLevel()+ ", Fee: $"+ getFee();
    }
}
