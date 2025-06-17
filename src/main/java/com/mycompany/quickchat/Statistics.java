/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import java.awt.HeadlessException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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
                                                            (1) Sent Messages: View Sender & Recipient
                                                            (2) Sent Messages: View Longest Message
                                                            (3) All  Messages: Search by Message ID      
                                                            (4) Sent Messages: Search by Recipient
                                                            (5) All  Messages: Delete by Hash
                                                            (6) Sent Messages: View Full Report
                                                            (7) Return to Main menu
                                                            """, 
                                    "STATISTICS MENU", 3);
                    DialogHelper.exitIfCancelled(menu); 
                    
            switch(menu)
            {
                case "1" -> displaySendersAndRecipients(logObj);
                
                case "2" -> displayLongestMessage();
                
                case "3" -> 
                {
                    String[] options = {"sentMessages", "storedMessages", "disregardedMessages"};
                    String selection =  (String) JOptionPane.showInputDialog(
                                                                null,
                                                                "Select which message section to search in:",
                                                                "SEARCH FOR MESSAGES",
                                                                JOptionPane.PLAIN_MESSAGE,
                                                                null,
                                                                options,
                                                                options[0]
                            );
                        if(selection != null)
                        {
                            String messageID = JOptionPane.showInputDialog(null, 
                                                                    "Enter Message ID to search:");
                                DialogHelper.exitIfCancelled(messageID);
                                    searchMessageID("allMessages.json", selection, messageID);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Search cancelled");
                                return; 
                        }   
                }
                
                case "4" -> searchRecipient();
                
                case "5" -> 
                {
                    String[] options = {"sentMessages", "storedMessages", "disregardedMessages"};
                    String section = (String) JOptionPane.showInputDialog(
                                                                null,
                                                                "Choose message section to delete from:",
                                                                "Delete by Hash",
                                                                JOptionPane.PLAIN_MESSAGE,
                                                                null,
                                                                options,
                                                                options[0]
                            );
                        if (section != null) 
                        {
                            String hash = JOptionPane.showInputDialog(null, "Enter the Message Hash to delete:");
                                DialogHelper.exitIfCancelled(hash);
                                    deleteMessage("allMessages.json", section, hash);
                        } 
                        else 
                        {
                            JOptionPane.showMessageDialog(null, "Delete cancelled.");
                        }
                    }
                
                
                case "6" -> displayFullReport();
                
                case "7" -> {return;}
                
                default -> JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }
    }   

    /**
     * Displays sender and recipient of all sent messages
     * Option(1) from the sub-menu
     * Assisted by ChatGPT (2025, June 13) to create the logic for the method
     * @param logObj
     */
    public void displaySendersAndRecipients(Login logObj) 
    {
        String[] recipients = Message.readSenderRecipient("allMessages.json", 
                                                                "sentMessages", logObj);

            if (recipients.length == 0) 
            {
                JOptionPane.showMessageDialog(null, "No sent messages found.");
            } 
            else 
            {
                StringBuilder result = new StringBuilder("Senders and Recipients of all sent messages:\n\n");
        
            for (String line : recipients) 
            {
                result.append(line).append("\n");
            }
            JOptionPane.showMessageDialog(null, result.toString());
        }
    }
    
    /**
     * Displays longest message
     * Option(2) from the sub-menu
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
            String[] parts = msg.split("Sent Message: "); //Extract the actual "Sent Message" portion
                if (parts.length < 2) 
                    continue;
         
            String messageContent = parts[1].trim(); 
                if (messageContent.length() > maxLength) 
                {
                    maxLength = messageContent.length();
                    longestMessage = msg;
                }
            } 
            catch (HeadlessException | JSONException x) 
            {
                System.out.println("Error processing message: " + x.getMessage());
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
     * Searches for a message ID and display corresponding recipient and message
     * Option(3) from the sub-menu
     * Assisted by ChatGPT (2025, June 17) setting up and reviewing the method
     * @param filename
     * @param arrayKey
     * @param messageID
     */
    public static void searchMessageID(String filename, String arrayKey, String messageID)
    {
        JSONArray messages = Message.readJSONArray(filename, arrayKey);     //Call the method that reads the JSON file
            boolean found = false;

        for (int i = 0; i < messages.length(); i++)             //Goes through each and every message in the JSON file
        {
            JSONObject msg = messages.getJSONObject(i);
            
                if (msg.optString("Message ID").equalsIgnoreCase(messageID)) //Checks the messageID being searched for 
                {
                    JOptionPane.showMessageDialog(null,
                                                "Recipient: " + msg.optString("Recipient No") + "\n" +
                                                "Message: " + msg.optString("Sent Message"));
                    found = true;
                    break;
                }
            }

            if (!found) 
            {
                JOptionPane.showMessageDialog(null, "Message ID not found.");
            }
        } 

    /**
     * Searches for all messages sent to a particular recipient
     * Option (4) from the sub-menu
     */
    public static void searchRecipient()
    {
        String recipient = JOptionPane.showInputDialog(null, 
                    "Enter recipient number to search (e.g: +27627680711):");
                                DialogHelper.exitIfCancelled(recipient);

        JSONArray messages = Message.readJSONArray("allMessages.json", "sentMessages"); /*Reads the JSON file and
                                                                                                      gets the sentMessage array*/
        StringBuilder results = new StringBuilder("Message(s) sent to " + recipient + ":\n\n");
            boolean found = false;                                          //A flag to track if any matching messages are found

        for (int i = 0; i < messages.length(); i++)             //Loops through every message object in the sentMessages array.
        {
            JSONObject msg = messages.getJSONObject(i);

                if (msg.optString("Recipient No").equalsIgnoreCase(recipient)) //Checks number in the array with the input
                {
                    results.append("Message: ").append(msg.optString("Sent Message")).append("\n");
                            found = true;
                }
            }
        
            if (found) 
            {
                JOptionPane.showMessageDialog(null, results.toString());
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "No messages found for recipient: " + recipient);
            }
        }

    /**
     * Delete a message using the message hash
     * @param filename
     * @param arrayKey
     * @param messageHash
     */
    public static void deleteMessage(String filename, String arrayKey, String messageHash)
    {
        try 
        {
            String data = new String(Files.readAllBytes(Paths.get(filename)));
            JSONObject root = new JSONObject(data);

            if (root.has(arrayKey)) 
            {
                JSONArray messages = root.getJSONArray(arrayKey);
                    boolean deleted = false;

                for (int i = 0; i < messages.length(); i++) 
                {
                    JSONObject msg = messages.getJSONObject(i);
                    
                    if (msg.optString("Message Hash").equalsIgnoreCase(messageHash)) 
                    {
                        messages.remove(i);                         //Removes the message from array
                        deleted = true;
                        break;
                    }
                }   
                
                        if (deleted)                                         //Re-save the modified root object
                        {
                            try (FileWriter file = new FileWriter(filename)) 
                            {
                                file.write(root.toString(4)); // Indent for readability
                            }
                                JOptionPane.showMessageDialog(null, "Message deleted successfully.");
                        } 
                        else 
                        {
                            JOptionPane.showMessageDialog(null, "Message Hash not found.");
                        }
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "No array found with key: " + arrayKey);
                    }
        } 
            catch (IOException | JSONException x) 
            {
                JOptionPane.showMessageDialog(null, "Error deleting: " + x.getMessage());
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
                StringBuilder results = new StringBuilder("Recent Sent Messages:\n\n"); /*Creates a header and prepares 
                                                                                a StringBuilder to hold the message body.*/        
                for (int i = 0; i < sentOnly.length; i++)                   //Enhanced for-each-loop: (String msg : sentOnly)               
                {
                    String msg = sentOnly[i];
                    results.append(msg).append("\n\n");         //Add two newlines as the loops add/appends 'results'
                }
            JOptionPane.showMessageDialog(null, results.toString());
        }                               
    }  
}
    
 /**
* References:
* OpenAI. (2025, June 13). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
*/
