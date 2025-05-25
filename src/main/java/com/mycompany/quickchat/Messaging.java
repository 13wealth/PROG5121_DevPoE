/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import javax.swing.*;

/**
 * Gets the user's preferred number of messages to send and assigned unique_ID and message count
 * Regular expression was assisted by ChatGPT (2025, May 1) to handle non-numeric inputs.
 * Parse was assisted by ChatGPT (2025, May 1) to convert input to aString variable to Integer.
 */
public class Messaging 
{
    public void sendMessage()
    {
                                          /*messageLimit() takes input as int but because JOptionPane only 
                                            returns String, we need to parse/convert from String back to int
                                            before we can use it in the for-loop and as element size*/
        int messageLimit = Integer.parseInt(JOptionPane.showInputDialog(null, 
                                    "Please enter number of messages you want to send: "));
                            //UIHelper.exitIfCancelled(messageLimit);
                            
        String[] chat = new String[messageLimit];
        String[] msgID = new String[messageLimit];
        
        for(int i = 0; i < messageLimit; i++)
        {
            chat[i] = DialogHelper.setUpMessage(i + 1);
            msgID[i] = DialogHelper.checkMessageID();
        }
    }
    
    public static void recentMessages()
    {
        /*JLabel textDisplay = new JLabel("Comin Soon.");
        textDisplay.setHorizontalAlignment(0);*/
        
        JOptionPane.showMessageDialog(null, "Coming Soon.");    
    
    }
}
  
       
/***********************************************************************************************************     
        do
        {
            String numMessages = JOptionPane.showInputDialog(null, """
                                                        Please enter number of messages you want to send.
                                                        Message limit (1 to 10):
                                                        """);
                                                   UIHelper.exitIfCancelled(numMessages);

            if(numMessages.matches("\\d+"))                //Checks that the input contains only numbers
            {
                messageLimit = Integer.parseInt(numMessages); //Converts the variable from String to Integer
                            
                    if(messageLimit > 0 && messageLimit <= 10)
                    {
                        validInput = true;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, 
                                                    "Please enter a number between 1 and 10.");
                    }
            }
            else
            {
                JOptionPane.showMessageDialog(null, 
                                            "Please enter digits only (no letters or symbols).");
            }
        }while(!validInput);
      return messageLimit;
    }   
        
        
    /**
     * Creates a window to type messages, if long scroll is enables.
     * Settings for a chat box and buttons are defined. 
     *
    public void sendMessage()
    {
        JTextArea textBox = new JTextArea(10,40);     //Creates a new textbox for typing message
        textBox.setBackground(Color.black);                  //Settings for the textbox
        textBox.setForeground(Color.yellow);
        textBox.setWrapStyleWord(true);
        textBox.setLineWrap(true);
        
        Object[] customButton = {"Send","Exit"};                //This setting helps us rename/cutomise our buttons
        
        int textInput = JOptionPane.showOptionDialog(null, new JScrollPane(textBox), 
                            "Type your message", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, customButton, customButton[0]);
                          UIHelper.exitIfNotOk(textInput);
    }
}*/
