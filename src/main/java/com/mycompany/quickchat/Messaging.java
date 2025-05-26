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
                            
        String[] chat = new String[messageLimit];
        String[] msgID = new String[messageLimit];
        String[] recipientNum = new String[messageLimit];
        
        
        for(int i = 0; i < messageLimit; i++)
        {
            recipientNum[i] = Message.checkRecipientCell();
            chat[i] = Message.SentMessage((i + 1), recipientNum[i]);
            msgID[i] = Message.checkMessageID(); 
        }
        Message.printSentMessages();
        Message.returnTotalMessages();
        Message.storeMessages(chat);
    }
    
    public static void recentMessages()
    {      
        JOptionPane.showMessageDialog(null, "Coming Soon.");    
    }
}
