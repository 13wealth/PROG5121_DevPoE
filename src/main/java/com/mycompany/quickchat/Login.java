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
    private String registeredName, registeredSurname;
    private String registeredUsername, registeredPassword;

    /**
     * Registration inputs in the main are passed through this constructor method
     * @param name
     * @param surname
     * @param username
     * @param password
     */
    public void registerUser(String name, String surname, String username, String password)                   
    {                                                                                               
        this.registeredName = name;
        this.registeredSurname = surname;
        this.registeredUsername = username;
        this.registeredPassword = password;
    }
    
    public String getFullName()
    {
        return registeredName + " " + registeredSurname; /*Getter to assign the inputs to the sender variable
                                                           readSendersAndRecipients() in Message class*/
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
        if (inputUser.equals(registeredUsername) &&  inputPass.equals(registeredPassword))
        {  
            JOptionPane.showMessageDialog(null, "Welcome " + registeredName + " " + registeredSurname  +
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
