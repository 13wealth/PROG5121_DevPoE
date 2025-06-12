/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import javax.swing.*;


/**
 * This is a sub-menu, option(3) from the main menu
 * Displays statistics and properties of ALL the messages
 */
public class Statistics 
{
    public void messageStats(Login logObj)
    {
        while(true)
        {                                           
            String menu = JOptionPane.showInputDialog(null, """
                                                            (1) View Sender & Recipient
                                                            (2) View Longest Message
                                                            (3) Search by Message ID      
                                                            (4) Search by Recipient
                                                            (5) Delete Message by Hash
                                                            (6) View Full Report
                                                            (7) Return to Main menu
                                                            """, 
                                    "STATISTICS MENU", 3);
                    DialogHelper.exitIfCancelled(menu); 
                    
            switch(menu)
            {
                case "1" -> displaySendersAndRecipients(logObj);
                case "2" -> displayLongestMessage();
                //case "3" -> searchByMessageID();
                //case "4" -> searchByRecipient();
                //case "5" -> deleteByHash();
                case "6" -> displayFullReport();
                case "7" -> { return; }
                default -> JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }
    }   

    /**
     * Display sender and recipient of all sent messages
     * Option(1) from the menu
     * Assisted by ChatGPT (2025, June 13) to create the logic for the method
     * @param logObj
     */
    public void displaySendersAndRecipients(Login logObj) 
    {
        String[] recipients = Message.readSendersAndRecipients("allMessages.json", 
                                                                "sentMessages", logObj);

            if (recipients.length == 0) 
            {
                JOptionPane.showMessageDialog(null, "No sent messages found.");
            } 
            else 
            {
                StringBuilder result = new StringBuilder("Senders and Recipients:\n\n");
        
            for (String line : recipients) 
            {
                result.append(line).append("\n");
            }
            JOptionPane.showMessageDialog(null, result.toString());
        }
    }
    
    /**
     * Display longest message
     * Option(2) from the menu
     * Assisted by ChatGPT (2025, June 13) for getting and measuring characters of the message
     */
    public void displayLongestMessage() 
    {
        String[] sentOnly = Message.readAllData("allMessages.json", "sentMessages");
        
            if (sentOnly.length == 0)
            {
                JOptionPane.showMessageDialog(null, "No sent messages found.");
                return;
            }
       
        String longestMessage = "";
        int maxLength = 0; 
        
        for (String msg : sentOnly) 
        {
            try 
            {
            String[] parts = msg.split("Sent Message: "); // Extract the actual "Sent Message" portion
                if (parts.length < 2) 
                    continue;
         
            String messageContent = parts[1].trim();  // Get just the message
                if (messageContent.length() > maxLength) 
                {
                    maxLength = messageContent.length();
                    longestMessage = msg;
                }
            } 
            catch (Exception e) 
            {
                System.out.println("Error processing message: " + e.getMessage());
            }
        }
            if (!longestMessage.isEmpty()) 
            {
                JOptionPane.showMessageDialog(null, "Longest Sent Message:\n\n" + longestMessage);
            } 
            else 
        {
            JOptionPane.showMessageDialog(null, "No valid messages found.");
        }
    }
 
    /**
     *  Display a report that lists the full details of all the sent messages
     *  Option(6) from the menu
     */
    public void displayFullReport()
    {
        String[] sentOnly = Message.readAllData("allMessages.json", "sentMessages");
        
            if (sentOnly.length == 0)
            {
                JOptionPane.showMessageDialog(null, "No sent messages found.");
            }
            else
            {
                StringBuilder result = new StringBuilder("Recent Sent Messages:\n\n"); /*Creates a header and prepares 
                                                                                a StringBuilder to hold the message body.*/        
            for (String msg : sentOnly)
            {
                result.append(msg).append("\n\n");
            }
        
            JOptionPane.showMessageDialog(null, result.toString());
        }                               
    }  
}
    
 /**
* References:
* OpenAI. (2025, June 13). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
*/

/*
a)
b)
c)Search for a message ID and display corresponding recipient and message
d)Search for all messages sent to a particular recipient
e)Delete a message using the message hash
f)
*/