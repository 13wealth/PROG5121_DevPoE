/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import java.awt.Color;
import javax.swing.*;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author RC_Student_lab 
 * UIHelper class for handling JOptionPane settings and exceptional selections .
 * Assisted by ChatGPT (2025, May 1) to handle non-YES JOptionPane inputs.
 */
public class DialogHelper 
{
    /**
     * Checks if the user cancelled/closed input on *JOptionPane.showInputDialog*
     * Exits the program with a farewell message if input is null.
     * @param input
     */
    public static void exitIfCancelled(String input)
    {
        if(input == null)
        {
            JOptionPane.showMessageDialog(null, "Thank you for visiting QuickChat",
                                                    "EXIT",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
        
    /**
     * Checks if the user clicked any other button except OK on *JOptionPane.showConfirmDialog*
     * @param input
     */
    public static void exitIfNotOk(int input)
    {
        if(input != JOptionPane.OK_OPTION)
        {
            JOptionPane.showMessageDialog(null, "Thank you for visiting QuickChat",
                                                    "EXIT",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Creates and stores a unique_ID for each message sent
     * Assisted by ChatGPT (2025, May 25) to generate 1 random non-repeating message ID.
     * @return
     */
    public static String messageID()
    {
        Random genID = new Random();
        
        long uniqueID = 1000000000L + (long)(genID.nextDouble() * 9000000000L);
            return "" + uniqueID;
    }
    
    /**
     * Contains the cell number of the recipient
     * Number entered is controlled by regular expression for:
     * - Has to be 10 digits long
     * - Has an international code
     * @param cellPhone
     * @return
     */
    public static String recipientNumber()
    {
        String cellRegex = "^\\+27[1-9]\\d{8}$";

        while(true)
        {
            String recipientNum = JOptionPane.showInputDialog(null, """
                                                               Must contain the country code
                                                               Must be a valid South African cellphone number""",
                                                            "ENTER RECIPIENT CELLPHONE NUMBER",3);
                                exitIfCancelled(recipientNum);
                if(Pattern.matches(cellRegex, recipientNum))
                {           
                    return recipientNum;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, 
                                        "Cellphone number incorrectly formatted or does not contain international code.",
                                        "UNSUCCESSFUL",JOptionPane.ERROR_MESSAGE);
            }
        }
    }       

    /**
     * Creates a text box that allows a user to type large text and enable to scroll
     * Counts and stores the message typed back to the 
     * Validates the character count and loops until correct count is inputted 
     * @param numMessages
     * @return 
     */
    public static String setUpMessage(int numMessages)
    {
        JTextArea textBox = new JTextArea(10,30);     //Creates a new textbox for typing message
        textBox.setBackground(Color.black);                  //Settings for the textbox
        textBox.setForeground(Color.yellow);
        textBox.setWrapStyleWord(true);
        textBox.setLineWrap(true);
                                                                //Custom buttons to replace 'OK' and 'Cancel'
        Object[] customButton = {"Send Message",                //Button 0   
                                 "Store Message to Send later", //Button 1
                                 "Disregard Message"};          //Button 2
        while(true)
        {
            int sendMessage = JOptionPane.showOptionDialog(null, new JScrollPane(textBox), "Message: " 
                                                        + numMessages, JOptionPane.DEFAULT_OPTION,
                                                        JOptionPane.INFORMATION_MESSAGE, null, 
                                                        customButton, customButton[0]);
                switch(sendMessage)
                {
                    case 0 ->                                   //Validates if user clicks Button 0
                    {
                        String text = textBox.getText();         /*Stores input messages in 'text' and 
                                                                   returned if user clicks Button 0*/
                        if(text.length() > 250)
                        {
                            JOptionPane.showMessageDialog(null, 
                                                     "Please enter a message of less than 250 characters"); 
                                continue;                       //Loops user back to retype instead of reseting 
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Message sent");
                                return text;
                        }
                    }
                    
                    case 1 ->                                   //Validates if user clicks Button 1
                    {
                        JOptionPane.showMessageDialog(null, "Message stored and will be sent later");
                            return "";
                    }
                   
                    case 2,-1 ->                               //Validates if user clicks Button 2 or closes the box
                    {
                        JOptionPane.showMessageDialog(null, "Program exited without sending a message.");
                            return null;
                    }
                        
                        
            
            
            
            
            
                    /*if(sendMessage == 0)
                    {
                    String text = textBox.getText();               /Stores input messages in 'text' and
                    returned if user clicks "Send"
                    if(text.length() > 250)
                    {
                    JOptionPane.showMessageDialog(null,
                    "Please enter a message of less than 250 characters");
                    continue;                           //Loops user back to retype instead of reseting
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null, "Message sent");
                    return text;
                    }
                    }
                    else if(sendMessage == 1)
                    {
                    JOptionPane.showMessageDialog(null, "Message stored and will be sent later");
                    return "";
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null, "Program exited without sending a message.");
                    return null;*/
            }
        }
    }
}
      
/**
* References:
* OpenAI. (2025, May 1). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat 
* OpenAI. (2025, May 25). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
*/
