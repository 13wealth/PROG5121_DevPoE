/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import java.util.regex.Pattern;

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
        return Pattern.matches(cellRegex, cellPhone);
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
        return Pattern.matches(userNameRegex, username);
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
        return Pattern.matches(passWordRegex, password);
    }

    /**
     * Method to validate all inputs and displays all results on the GUI
     * @param cellphone
     * @param username
     * @param password
     */
    public void validateInputs(String cellphone, String username, String password)
    {
        StringBuilder successMsg = new StringBuilder("SUCCESSFUL: \n");
        StringBuilder errorMsg = new StringBuilder("UNSUCCESSFUL: \n");

        if (checkCellPhoneNumber(cellphone)) 
        {
            successMsg.append("- Cellphone number added.\n");
        } else {
            errorMsg.append("""
                            - Invalid cellphone number. 
                                • Must start with +27 and be followed by 9 digits.
                                • Example: +27123456789\n
                            """);
        }

        if (checkUserName(username)) 
        {
            successMsg.append("- Username added.\n");
        } else {
            errorMsg.append("""
                            - Invalid username.
                                • Must contain an underscore and be 1-5 characters long.
                                • Example: user_1\n
                            """);
        }

        if (checkPassWordComplexity(password)) 
        {
            successMsg.append("- Password added.\n");
        } else {
            errorMsg.append("""
                            - Invalid password.
                                • Must be at least 8 characters long.
                                • Contain uppercase, number, and special character.
                                • Example: Passw0rd!
                            """);
        }
            JOptionPane.showMessageDialog(null, successMsg.toString() + "\n" + errorMsg.toString(), 
                                        "VALIDATION RESULTS", JOptionPane.INFORMATION_MESSAGE);
    }
}

/**
* References:
* OpenAI. (2025, May 1). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
* 
*/
 
