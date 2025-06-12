/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author RC_Student_lab 
 *
 */
public class Message
{
    public static ArrayList<String> sentIDs = new ArrayList<>();
    public static ArrayList<String> sentHashes = new ArrayList<>();
    public static ArrayList<String> sentRecipients = new ArrayList<>();
    public static ArrayList<String> sentMessages = new ArrayList<>();
    public static ArrayList<String> storedIDs = new ArrayList<>();
    public static ArrayList<String> storedHashes = new ArrayList<>();
    public static ArrayList<String> storedMessages = new ArrayList<>();
    public static ArrayList<String> disregardedIDs = new ArrayList<>();
    public static ArrayList<String> disregardedHashes = new ArrayList<>();
    public static ArrayList<String> disregardedMessages = new ArrayList<>();
    
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
     * Creates a unique message hash for every message sent
     * Assisted by ChatGPT (2025, May 26) to create a message hash
     * @param uniqueID
     * @param numMessages
     * @param messageText
     * @return
     */
    public static String createMessageHash(String uniqueID, int numMessages, String messageText)
    {
        CharSequence idPrefix = uniqueID.subSequence(0, 2); /*Extracts the first two numbers
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
                                    recipientNum = recipientNum.trim();
                                    
                if(Pattern.matches(cellRegex, recipientNum))
                {           
                    return recipientNum;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, 
                                        "Cellphone number incorrectly formatted or does not contain international code.",
                                        "UNSUCCESSFUL",JOptionPane.ERROR_MESSAGE);
            }
        }
    }       

    /**
     * Creates a text box that allows a user to type large text and enable to scroll
     * Creates custom buttons to handle different message operations
     * Counts and stores the typed message.
     * Validates the character count and loops until correct count is inputted 
     * @param numMessages
     * @param recipient
     * @return 
     */
    public static String sentMessage(int numMessages, String recipient)
    {
        //String recipientNum = checkRecipientCell();
        
        JTextArea textBox = new JTextArea(10,30);     //Creates a new textbox for typing message
        textBox.setBackground(Color.black);                  //Settings for the textbox
        textBox.setForeground(Color.yellow);
        textBox.setWrapStyleWord(true);
        textBox.setLineWrap(true);
                                                                
        Object[] customButton = {                               //Custom buttons to replace 'OK' and 'Cancel'
                                 "Send Message",                //Button 0   
                                 "Store Message to Send later", //Button 1
                                 "Disregard Message"            //Button 2
            };          
        while(true)
        {
            int sendMessage = JOptionPane.showOptionDialog(null, new JScrollPane(textBox), "Message: " 
                                                        + numMessages, JOptionPane.DEFAULT_OPTION,
                                                        JOptionPane.INFORMATION_MESSAGE,
                                                        null, customButton, customButton[0]);
                switch(sendMessage)
                {
                    case 0 ->                                   //Button 0 <Send Message>
                    {
                            String text = textBox.getText();    /*Stores input messages in 'text' and returned 
                                                                  if user clicks Button 0. Trims whitespaces*/
                        if(text.length() > 250)
                        {
                            JOptionPane.showMessageDialog(null, 
                                                     "Please enter a message of less than 250 characters"); 
                                continue;                       //Loops user back to retype instead of reseting the text 
                        }
                        else
                        {
                            String uniqueID = checkMessageID();
                            String messageHash = createMessageHash(uniqueID, numMessages, text);
                            sentIDs.add(uniqueID);             //Appends and Holds <uniqueID> for storage in JSON file
                            sentHashes.add(messageHash);       //Appends and Holds <messageHash> for storage in JSON file
                            sentRecipients.add(recipient);     //Appends and Holds <recipientNumber> for storage in JSON file
                            sentMessages.add(text);            //Appends and Holds <text> for storage in JSON file     
                            
                            JOptionPane.showMessageDialog(null, "Message sent!\n\nMessage ID: " + uniqueID + 
                                                          "\nMessage Hash: " + messageHash + "\nRecipient: " + recipient +
                                                            "\nMessage: " + text);
                        return text;
                        }
                    }
                    
                    case 1 ->                                   //Button 1(Store Message to Send later)
                    {
                            String text = textBox.getText();
                            String uniqueID = checkMessageID(); 
                            String messageHash = createMessageHash(uniqueID, numMessages, text);
                            storedIDs.add(uniqueID);       
                            storedHashes.add(messageHash); 
                            storedMessages.add(text);         
                            
                        JOptionPane.showMessageDialog(null, "Message stored and will be sent later");
                        return "";
                    }
                   
                    case 2,-1 ->                                //Button 2 or closes the dialog box
                    {
                            String text = textBox.getText();
                            String uniqueID = checkMessageID();
                            String messageHash = createMessageHash(uniqueID, numMessages, text);                            
                            disregardedIDs.add(uniqueID);       
                            disregardedHashes.add(messageHash); 
                            disregardedMessages.add(text);
                        JOptionPane.showMessageDialog(null, "Program exited without sending a message.");
                        return "";
                }
            }
        }
    }

    /**
     * Prints a list of messages that were sent as the program was running
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
            
    /**
     * Stores ALL message details in JSON file once all have been sent, stored or disregarded
     * Assisted by ChatGPT (2025, May 26) to create JSON dependency/import and method
     * @param sentIDs
     * @param sentHashes
     * @param sentRecipients
     * @param sentMessages
     * @param storedIDs
     * @param storedHashes
     * @param storedMessages
     * @param disregardedIDs
     * @param disregardedHashes
     * @param disregardedMessages
     */
    public static void savedMessages(
            ArrayList<String> sentIDs, 
            ArrayList<String> sentHashes, 
            ArrayList<String> sentRecipients, 
            ArrayList<String> sentMessages, 
            ArrayList<String> storedIDs, 
            ArrayList<String> storedHashes, 
            ArrayList<String> storedMessages, 
            ArrayList<String> disregardedIDs, 
            ArrayList<String> disregardedHashes, 
            ArrayList<String> disregardedMessages
        )   
    {
        JSONObject root = new JSONObject();         

        JSONArray sentArray = new JSONArray();                                  //Gets and holds sent messages
        for (int i = 0; i < sentMessages.size(); i++) 
        {
            JSONObject msg = new JSONObject();
            msg.put("Message ID", sentIDs.get(i));
            msg.put("Message Hash", sentHashes.get(i));
            msg.put("Recipient No", sentRecipients.get(i));
            msg.put("Sent Message", sentMessages.get(i));
            sentArray.put(msg);
        }

        JSONArray storedArray = new JSONArray();                                //Gets and holds stored messages
        for (int i = 0; i < storedMessages.size(); i++) 
        {
            JSONObject msg = new JSONObject();
            msg.put("Message ID", storedIDs.get(i));
            msg.put("Message Hash", storedHashes.get(i));
            msg.put("Sent Message", storedMessages.get(i));
            storedArray.put(msg);
        }

        JSONArray disregardedArray = new JSONArray();                           //Gets and holds disregarded messages
        for (int i = 0; i < disregardedMessages.size(); i++) 
        {
            JSONObject msg = new JSONObject();
            msg.put("Message ID", disregardedIDs.get(i));
            msg.put("Message Hash", disregardedHashes.get(i));
            msg.put("Sent Message", disregardedMessages.get(i));
            disregardedArray.put(msg);
        }
        
        root.put("sentMessages", sentArray);                            //Key to save sent messages
        root.put("storedMessages", storedArray);                        //Key to save stored messages to be sent later
        root.put("disregardedMessages", disregardedArray);              //Key to save disregarded messages

        try (FileWriter file = new FileWriter("allMessages.json"))      
        {
            file.write(root.toString(4));                           
            file.flush();                                                       //Ensure messaged are stored in disk
            System.out.println("Messages saved to JSON file");
        } 
        catch (IOException e)                                                   //Error handling when saving
        {
            System.out.println("Error saving messages: " + e.getMessage());
        }
    }
     
    /**
     * Reads ALL the data stored in a JSON file and returns it in an array list
     * Assisted by ChatGPT (2025, June 08) to create a file reading method 
     * @param fileName
     * @param arrayKey
     * @return
     */
    public static String[] readAllData(String fileName, String arrayKey)
    {
        ArrayList<String> readFile = new ArrayList<>(); /*Creates a list to temporarily store each message from 
                                                      the file that will be converted to a String[] then returned*/   
        try 
        {                       //Reads the whole file and returns bytes and wraps them to new String() to get text
            String storedData = new String(Files.readAllBytes(Paths.get(fileName)));                
            JSONObject root = new JSONObject(storedData);
        
                if (!root.has(arrayKey))
                {
                    System.out.println("No array with key '" + arrayKey + "' found.");
                        return new String[0];
                }
     
            JSONArray messageArray = root.getJSONArray(arrayKey);
            for (int i = 0; i < messageArray.length(); i++) 
            {
                JSONObject jObj = messageArray.getJSONObject(i); /*Gets the ith object in the array 
                                                                       (like { "Sent Message": "Hello" })*/
                String id = jObj.getString("Message ID");          //Pulls the message ID from the key "Message ID"
                String hash = jObj.getString("Message Hash");      //Pulls the message hash from the key "Message Hash"
                String recipient = jObj.getString("Recipient No"); //Pull the recipent number from key "Recipient No"
                String msg = jObj.getString("Sent Message");       //Pulls the message text from the key "Sent Message"

                String read = String.format("Message ID: %s | Hash: %s | Recipient: %s | Sent Message: %s",
                                                             id, hash, recipient,msg
                );
                readFile.add(read);                                 //Adds that message to the messages list.
            }
        
        }
        catch (IOException | JSONException e)                         //Safely handles any errors when reading the file
        {                                                             //Prints the error message if anything goes wrong
            System.out.println("Error reading messages: " + e.getMessage());
        }
        return readFile.toArray(String[]::new);
    }
   
    /**
     * Finds the sentRecipient key in the JSON file and extracts RECIPIENT information
     * @param fileName
     * @param arrayKey
     * @param logObj
     * @return
     */
    public static String[] readSendersAndRecipients(String fileName, String arrayKey, Login logObj) 
    {
        ArrayList<String> result = new ArrayList<>();
    
        try 
        {
            String storedData = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONObject root = new JSONObject(storedData);

                if (!root.has(arrayKey)) 
                {
                    System.out.println("No array with key '" + arrayKey + "' found.");
                        return new String[0];
                }

                JSONArray messageArray = root.getJSONArray(arrayKey);
                for (int i = 0; i < messageArray.length(); i++) 
                {            
                    JSONObject msg = messageArray.getJSONObject(i);
                    
                    String recipient = msg.optString("Recipient No", "Unknown");
                    String sender = logObj.getFullName();

                result.add("Sender: " + sender + "| Recipient: " + recipient);
                }
        }   
        catch (IOException | JSONException e) 
        {
            System.out.println("Error reading recipients: " + e.getMessage());
        }
        return result.toArray(new String[0]);
    }
    
}
 
/**
* References:
* OpenAI. (2025, June 12). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
*/