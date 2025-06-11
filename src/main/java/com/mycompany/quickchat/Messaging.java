/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import javax.swing.*;
import org.json.JSONArray;

/**
 * 
 */
public class Messaging 
{
    private JSONArray messageArray;
    /**
     * Gets the user's preferred number of messages to send and assigned unique_ID, message hash and message count
     * Regular expression was assisted by ChatGPT (2025, May 1) to handle non-numeric inputs.
     * Parse was assisted by ChatGPT (2025, May 1) to convert input to aString variable to Integer.
     */    
//(1) Send Message 
    public void sendMessage()
    {                                      
        int messageLimit = Integer.parseInt(JOptionPane.showInputDialog(null, 
                                    "Please enter number of messages you want to send: "));
        
        String[] chat = new String[messageLimit];
        String[] msgID = new String[messageLimit];
        String[] recipientNum = new String[messageLimit];

        for(int i = 0; i < messageLimit; i++)
        {
            recipientNum[i] = Message.checkRecipientCell();          //Full validation in the message class
            chat[i] = Message.sentMessage((i + 1), recipientNum[i]); //Full validation in the message class
        }
        Message.printSentMessages();
        Message.returnTotalMessages();
        Message.savedMessages(
                              Message.sentIDs,
                              Message.sentHashes,
                              Message.sentMessages,
                              Message.storedIDs,
                              Message.storedHashes,
                              Message.storedMessages,
                              Message.disregardedIDs,
                              Message.disregardedHashes,
                              Message.disregardedMessages
        );
    }

//(2) Show recent messages      
    public static void recentMessages()
    {         
        String[] sentOnly = Message.readFromFile("allMessages.json", "sentMessages");

        if (sentOnly.length == 0) 
        {
            JOptionPane.showMessageDialog(null, "No sent messages found.");
        } 
        else 
        {
            StringBuilder sb = new StringBuilder("Recent Sent Messages:\n\n");
            for (String msg : sentOnly) 
            {
                sb.append(msg).append("\n\n");
            }

            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }
}