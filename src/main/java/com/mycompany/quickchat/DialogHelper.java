/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */ 
package com.mycompany.quickchat;

import javax.swing.JOptionPane;

/**
 * @author RC_Student_lab 
 * DialogHelper class for handling JOptionPane settings and exceptional selections .
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
     * Exits the program with a farewell message if input is null.
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
}
      
/**
* References:
* OpenAI. (2025, May 1). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat 
*/
