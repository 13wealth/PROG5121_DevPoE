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
     * Gets the user's preferred number of messages to send  
     * Assigns unique_ID, message hash and message count
     * Menu option(1)
     */     
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
                              Message.sentRecipients,
                              Message.sentMessages,
                              Message.storedIDs,
                              Message.storedHashes,
                              Message.storedMessages,
                              Message.disregardedIDs,
                              Message.disregardedHashes,
                              Message.disregardedMessages
        );
    }

    /**
     * Displays recently sent messages (Only text details)
     * Menu option(2)
     */
    public static void recentMessages()
    {           
        Message.printSentMessages();
    }
}
