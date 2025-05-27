/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;

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
}