/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import java.util.regex.*;
import javax.swing.JOptionPane;

/**
 * @author RC_Student_lab
 * Registration class
 * This feature validates the user's input using regex pattern when registering.
 * Regular expression was assisted by ChatGPT (2025, April 8) for improved validation.
 */
public class Registration 
{
    /**
     * Method to validate cellphone number
     * Ensures the input matches the South African number format with international code (+27)
     * @param cellPhone
     * @return
     */
    public boolean checkCellPhoneNumber(String cellPhone)
    {
        String cellRegex = "^\\+27[1-9]\\d{8}$";
        if(Pattern.matches(cellRegex, cellPhone))
        {
            int option = JOptionPane.showConfirmDialog(null, 
                                                        "Cellphone number successfully added.\nContinue?",
                                                        "SUCCESS",JOptionPane.YES_NO_OPTION,1);
                    return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null, 
                                "Cellphone number incorrectly formatted or does not contain international code.",
                                "UNSUCCESSFUL",JOptionPane.ERROR_MESSAGE);
                    return false;
        }
    }
    /**
     * Method to validate username
     * Ensures the username contains an underscore and is max 5 characters
     * @param username
     * @return
     */
    public boolean checkUserName(String username)
    {
        String userNameRegex = "^(?=.*_)[a-zA-Z0-9_]{1,5}$";                                      
        if(Pattern.matches(userNameRegex, username))
        {
            int option = JOptionPane.showConfirmDialog(null, "Username successfully captured.\nContinue?",
                                            "SUCCESS",JOptionPane.YES_NO_OPTION,1);         
                    return true;
        }
        else
        {
             JOptionPane.showMessageDialog(null, 
                    "Please make sure that your username contains an unserscore and is no more than five characters in length.",
                    "UNSUCCESSFUL",JOptionPane.ERROR_MESSAGE);

            return false;
        }
    }

    /**
     * Method to validate password
     * Ensures the entire password follows the complexity rules
     * @param password
     * @return
     */
    public boolean checkPassWordComplexity(String password)       
    {
        String passWordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\W_]).{8,}$";
        if(Pattern.matches(passWordRegex, password))
            {
                JOptionPane.showMessageDialog(null, "Password successfully captured",
                                                "REGISTRATION COMPLETE!",JOptionPane.INFORMATION_MESSAGE);
                    return true;
            }
            else
            {
                 JOptionPane.showMessageDialog(null, 
                                                """
                                                     Password is not correctly formatted. 
                                                     Please ensure that the password contains:
                                                     -At least eight characters
                                                     -A capital letter
                                                     -A number and a special character.""",
                                                "UNSUCCESSFUL",JOptionPane.ERROR_MESSAGE);
                return false;
        }
    }
}

/**
* References:
* OpenAI. (2025, May 1). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
* 
*/
