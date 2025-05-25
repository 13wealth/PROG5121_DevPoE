/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
     * Creates a text box that allows a user to type large text and enable to scroll
     * Counts and stores the message typed back to the method
     * @param numMessages
     * @return 
     */
    public static String setUpMessage(int numMessages)
    {
        JTextArea textBox = new JTextArea(10,40);     //Creates a new textbox for typing message
        textBox.setBackground(Color.black);                  //Settings for the textbox
        textBox.setForeground(Color.yellow);
        textBox.setWrapStyleWord(true);
        textBox.setLineWrap(true);
        
        Object[] customButton = {"Send","Exit"};                //Custom buttons
        
        int textField = JOptionPane.showOptionDialog(null, new JScrollPane(textBox), 
                            "Message: " + numMessages, JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, 
                            customButton, customButton[0]);
            if(textField == 0)
            {
                return textBox.getText();
            }
            else
            {
            return "Program exited without sending a message.";
        }   
    } 

    /**
     * Creates and stores a unique_ID for each message sent
     * @return
     */
    public static String checkMessageID()
    {
        long messageID = (long)(Math.random()* 9_000_000_000L) + 1_000_000_000L;
        return Long.toString(messageID);
    }

}

/**
* References:
* OpenAI. (2025, May 1). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat 
* 
*/
