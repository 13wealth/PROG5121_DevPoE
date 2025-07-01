/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import javax.swing.JOptionPane;

import org.json.JSONArray;

/**
 * Class creates an environment to:
 *          Select number of messages to send
 *          Assigns a value to messagelimit and uses that as a LVC 
 *          Type, send, store or discard a message
 *          Save all messages in json file on disk
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
        int messageLimit = -1;                       //Initialise an invalid default value/placeholder
        
        while (true)                                 //Run a loop until the user inputs a valid value
        {
            String input = JOptionPane.showInputDialog(null, 
                "Please enter number of messages you want to send:");           

                if (input == null)                              //If user cancels or closes the dialog box
                {
                    JOptionPane.showMessageDialog(null, "Cancelled. Returning to menu.");
                        return;
                }
                    if (input.matches("\\d+"))                  //Checks if input is only digits (0-9)
                    { 
                        messageLimit = Integer.parseInt(input); /*Convert String input to an int and assign
                                                                    it to messageLimit variable and update memory*/
                        if (messageLimit > 0)                   //Checks if input is positive digits
                        {
                            break;                              //Breaks from the loop if both IF statements are true
                        } 
                        else 
                        {
                            JOptionPane.showMessageDialog(null, 
                                                    "Please enter a number greater than 0.");
                        }
                    }
                    else 
                    {
                        JOptionPane.showMessageDialog(null, 
                                            "Invalid input. Only whole numbers allowed.");
                    }
                }

        String[] recipientNum = new String[messageLimit];    
        String[] chat = new String[messageLimit];
           
        for(int i = 0; i < messageLimit; i++)
        {
            recipientNum[i] = Message.checkRecipientCell();          //Uses regex to check recipient cellphone number input
            chat[i] = Message.sentMessage((i + 1), recipientNum[i]); //Create and evironment to type and send the message
        }
            Message.printSentMessages();                             //Prints a list of messeges a user has sent
            Message.returnTotalMessages();                           //Prints a total number of messages a user has sent
            Message.savedMessages(
                              Message.sentIDs,
                              Message.sentHashes,
                              Message.sentRecipients,
                              Message.sentMessages,
                              Message.storedIDs,
                              Message.storedHashes,
                              Message.storedRecipients,
                              Message.storedMessages,
                              Message.disregardedIDs,
                              Message.disregardedHashes,
                              Message.disregardedRecipients,
                              Message.disregardedMessages
        );                                                          //Saves sent, stored and disregarded mesages in a jason file
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
 
