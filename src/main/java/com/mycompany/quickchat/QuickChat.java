/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;

import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */
public class QuickChat 
{
    public static void main(String[] args) 
    {      
        Registration regObj = new Registration();
        
//1. Registration code, takes name & surname, cellphone, username and password inputs   
    //1.1. Name and Surname registration
        String regName,regSurname;
    
            regName = JOptionPane.showInputDialog(null, "Please enter your registration details.\nName:",
                                                        "WELCOME TO QUICK-CHAT", JOptionPane.QUESTION_MESSAGE);  
                DialogHelper.exitIfCancelled(regName);
 
            regSurname = JOptionPane.showInputDialog(null, "Surname",
                                                        "WELCOME TO QUICK-CHAT",3);
                DialogHelper.exitIfCancelled(regSurname);
                
    //2.1. Cellphone number registration
        String cellNumber;
        boolean isPhoneValid = false;
        
        do
        {
            cellNumber = JOptionPane.showInputDialog(null, """
                                                           Must contain the country code
                                                           Must be a valid South African cellphone number""",
                                                        "INPUT CELLPHONE NUMBER",3);
                DialogHelper.exitIfCancelled(cellNumber);
            isPhoneValid = regObj.checkCellPhoneNumber(cellNumber);
        }while (!isPhoneValid);                                         /*While the value is not valid, the statement  
                                                                     in the loop-body continue to execute*/
    //3.1. Username registration
        String regUser;
        boolean isUserValid = false;
        
        do
        {
            regUser = JOptionPane.showInputDialog(null, """
                                                           Must contain an underscore
                                                           Must not be longer than 5 characters""",
                                                        "INPUT USERNAME",3);
                DialogHelper.exitIfCancelled(regUser);
            isUserValid = regObj.checkUserName(regUser);
        }while (!isUserValid); 
        
    //4.1. Password registration 
        String regPass;
        boolean isPassValid = false;
        
        do
        {   regPass = JOptionPane.showInputDialog(null, """
                                                           The Password must contain:
                                                            -At least 8 characters long 
                                                            -At least one uppercase letter 
                                                            -At least one lowercase letter
                                                            -At least one number
                                                            -At least one special character""",
                                                        "INPUT PASSWORD",3);
                DialogHelper.exitIfCancelled(regPass);
            isPassValid = regObj.checkPassWordComplexity(regPass);
        }while (!isPassValid);
        
//2. Login
        String logUser, logPass;       
        boolean loginSuccess = false;
        Login logObj = new Login();
                
            JOptionPane.showMessageDialog(null,"Enter your login details",
                                            "WELCOME TO QUICK-CHAT",JOptionPane.INFORMATION_MESSAGE);
        do
        {   
          //The object passes and stores the inputs in a constructor method in Login class
            logObj.registerUser(regName, regSurname,regUser,regPass);                       
                                                                          
                logUser = JOptionPane.showInputDialog(null, "Username:",
                                                        "LOGIN", JOptionPane.QUESTION_MESSAGE); 
                    DialogHelper.exitIfCancelled(logUser);
                logPass = JOptionPane.showInputDialog(null, "Password:",
                                                "LOGIN", JOptionPane.QUESTION_MESSAGE);
                    DialogHelper.exitIfCancelled(logPass);
            loginSuccess = logObj.loginUser(logUser, logPass);                                                                            
        }while (!loginSuccess);                                                                        

//3. Messaging  
        boolean quit = false;
        Messaging msgObj = new Messaging();        
                
            JOptionPane.showMessageDialog(null,"Welcome to QuickChat",
                                        " ",JOptionPane.INFORMATION_MESSAGE);
        while(!quit)
        {
           String menu = JOptionPane.showInputDialog("""
                                               SELECT AN OPTION
                                               (1) Send Message
                                               (2) Show recent messages 
                                               (3) Quit
                                               """);
                                             DialogHelper.exitIfCancelled(menu);

          //Refer to the Messaging class for full validations       
            switch(menu)
            {
                case "1" ->
                {
                    msgObj.sendMessage();       //Full validation is messaging class
                }
                
                case "2" ->
                {
                    msgObj.recentMessages();    //Full validation is messaging class
                }
                case "3" -> quit = true;
            }
        }
    }
}

/**
* References:
* OpenAI. (2025, May 1). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat 
* 
*/