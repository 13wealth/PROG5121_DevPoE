/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import java.awt.Color;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author RC_Student_lab 
 *
 */
public class Message 
{
    public static ArrayList<String> sentMessages = new ArrayList<>();
    /**
     * Creates and stores a unique_ID for each message sent
     * Assisted by ChatGPT (2025, May 25) to generate 1 random non-repeating message ID.
     * @return
     */
    public static String checkMessageID()
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
     * @return
     */
    public static String checkRecipientCell()
    {
        String cellRegex = "^\\+27[1-9]\\d{8}$";

        while(true)
        {
            String recipientNum = JOptionPane.showInputDialog(null, """
                                                               Must contain the country code
                                                               Must be a valid South African cellphone number""",
                                                            "ENTER RECIPIENT CELLPHONE NUMBER",3);
                                DialogHelper.exitIfCancelled(recipientNum);
                if(Pattern.matches(cellRegex, recipientNum))
                {           
                    return recipientNum;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, 
                                        "Cellphone number incorrectly formatted or does not contain international code.",
                                        "UNSUCCESSFUL",JOptionPane.ERROR_MESSAGE);
                    return null;
            }
        }
    }       

    /**
     * Creates a text box that allows a user to type large text and enable to scroll
     * Counts and stores the message typed back to the 
     * Validates the character count and loops until correct count is inputted 
     * @param numMessages
     * @param recipient
     * @return 
     */
    public static String SendMessage(int numMessages, String recipient)
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
                                                        JOptionPane.INFORMATION_MESSAGE,
                                                        null, customButton, customButton[0]);
                switch(sendMessage)
                {
                    case 0 ->                                   //Validates if user clicks Button 0
                    {
                        String text = textBox.getText();        /*Stores input messages in 'text' and 
                                                                  returned if user clicks Button 0*/
                        if(text.length() > 250)
                        {
                            JOptionPane.showMessageDialog(null, 
                                                     "Please enter a message of less than 250 characters"); 
                                continue;                       //Loops user back to retype instead of reseting 
                        }
                        else
                        {
                            String id = checkMessageID();
                            String hash = createMessageHash(id, numMessages, text);
                            
                            JOptionPane.showMessageDialog(null, "Message sent!\n\nMessageID: " + id + 
                                                          "\nMessage Hash: " + hash + "\nRecipient: " + recipient +
                                                            "\nMessage: " + text);
                                    sentMessages.add(text);
                                return text;
                        }
                    }
                    
                    case 1 ->                                   //Validates if user clicks Button 1
                    {
                        JOptionPane.showMessageDialog(null, "Message stored and will be sent later");
                            return "";
                    }
                   
                    case 2,-1 ->                               //Validates if user clicks Button 2 or closes the dialog box
                    {
                        JOptionPane.showMessageDialog(null, "Program exited without sending a message.");
                            return null;
                }
            }
        }
    }

    /**
     * Creates a unique message hash for every message sent
     * Assisted by ChatGPT (2025, May 26) to create a message hash
     * @param messageID
     * @param numMessages
     * @param messageText
     * @return
     */
    public static String createMessageHash(String messageID, int numMessages, String messageText)
    {
        CharSequence idPrefix = messageID.subSequence(0, 2); /*Extracts the first two numbers
                                                                               of the message ID*/
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "N/A";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        
        String hash = String.format("%s:%d:%s%s", idPrefix, 
                                                        numMessages, 
                                                        firstWord, 
                                                        lastWord);
        return hash.toUpperCase();
    }
    
    /**
     * Stores all messages in JSON file once all have been sent
     * Assisted by ChatGPT (2025, May 26) to create JSON dependency/import and method
     */
    public static void storeMessages(String[] messages) 
    {
        JSONArray messageList = new JSONArray();
            
            for (String message : messages) 
            {
                if (message != null && !message.isEmpty()) {
                    JSONObject msgObject = new JSONObject();
                    msgObject.put("message", message);
                    messageList.put(msgObject);
                }
            }
            try (FileWriter file = new FileWriter("messages.json")) 
            {
                file.write(messageList.toString(4));
                file.flush();
                System.out.println("Messages saved to JSON.");
            } catch (Exception e) {
                System.out.println("Error saving messages: " + e.getMessage());
            }
        }
     
    /**
     * Send a list of messages that were send as the program was running
     * Assisted by ChatGPT (2025, May 27) to build the printSentMEssages
     */
    public static void printSentMessages() 
    {
        StringBuilder allMessages = new StringBuilder();
            
            for (int i = 0; i < sentMessages.size(); i++) 
            {
                allMessages.append((i + 1)).append(". ").append(sentMessages.get(i)).append("\n");
            }

    JOptionPane.showMessageDialog(null, allMessages.toString(), 
                                    "All Sent Messages", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Returns total number of messages sent while program was running
     * @return
     */
    public static int returnTotalMessages()
    {
        JOptionPane.showMessageDialog(null, "Total messages sent: " + sentMessages.size());
        return sentMessages.size();
    }
}
    
/**
* References:
* OpenAI. (2025, May 25). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
* OpenAI. (2025, May 26). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
* OpenAI. (2025, May 27). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
*/