/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import javax.swing.JOptionPane;

/**
 * Login class stores the registration inputs using constructor objects
 * Stored values are matched with the user login inputs and return true or false
 */
public class Login 
{
    private String storedName, storedSurname;
    private String storedUser, storedPass;

    /**
     * Registration inputs in the main are passed through this constructor method
     * @param storedName
     * @param storedSurname
     * @param storedUser
     * @param storedPass
     */
    public void registerUser(String storedName, String storedSurname, String storedUser, String storedPass)                   
    {                                                                                               
        this.storedName = storedName;
        this.storedSurname = storedSurname;
        this.storedUser = storedUser;
        this.storedPass = storedPass;
    }
    
    /**
     *
     * @param inputUser
     * @param inputPass
     * @return
     */
    public boolean loginUser(String inputUser,String inputPass)                  
    {                                                                 /*Method is called using an object 
                                                                        in the main to pass the login inputs*/
        if (inputUser.equals(storedUser) &&  inputPass.equals(storedPass))
        {  
            JOptionPane.showMessageDialog(null, "Welcome " + storedName + " " + storedSurname  +
                                                ", " + "it is great to see you again!",
                                                "SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                return true;
 
            }
            else
            {
                  JOptionPane.showMessageDialog(null, "Username or password incorrect, please try again.",
                                                "UNSUCCESSFUL",JOptionPane.ERROR_MESSAGE);    
                return false;
        }    
    }
}
