/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import static com.mycompany.quickchat.Message.readJSONArray;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author RC_Student_lab
 */
public class JUnitValidations 
{
    public static boolean validateMessageLength(String text) 
     {
        if (text.length() > 250) {
            return true;
        } else {
            return false;
        }
    }
     
    public static boolean checkRecipientCell(String recipientNum)
    {
        String cellRegex = "^\\+27[1-9]\\d{8}$";
       
            return Pattern.matches(cellRegex, recipientNum);
    }


 public static boolean createMessageHash(String messageID, int numMessages, String messageText) 
    {
        CharSequence idPrefix = messageID.subSequence(0, 2); 
        
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "N/A";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        
        String hash = String.format("%s:%d:%s%s", idPrefix, 
                                                        numMessages, 
                                                        firstWord, 
                                                        lastWord);
        return true;
    }
 public static List<String> getSentMessages() {
    List<String> sent = new ArrayList<>();

    JSONArray messages = readJSONArray("allMessages.json", "sentMessages");

    for (int i = 0; i < messages.length(); i++) {
        JSONObject msg = messages.getJSONObject(i);
        if ("Sent".equalsIgnoreCase(msg.optString("Flag"))) {
            sent.add(msg.optString("Sent Message"));
        }
    }
    return sent;
}
}
