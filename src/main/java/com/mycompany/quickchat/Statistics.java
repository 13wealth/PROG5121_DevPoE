/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import javax.swing.*;


/**
 *
 * @author RC_Student_lab
 */
public class Statistics 
{
    public void messageStats()
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
                                                            (7) Main menu
                                                            """, 
                                    "STATISTICS MENU", 3);
                    DialogHelper.exitIfCancelled(menu); 
                    
            switch(menu)
            {
                //case "1" -> displaySendersAndRecipients();
                //case "2" -> displayLongestMessage();
                //case "3" -> searchByMessageID();
                //case "4" -> searchByRecipient();
                //case "5" -> deleteByHash();
                case "6" -> displayFullReport();
                //case "7" -> { return; } // Exit statistics menu
                default -> JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }
    }   

    public void displayFullReport()
    {
        String[] sentOnly = Message.readFromFile("allMessages.json", "sentMessages");
        
            if (sentOnly.length == 0)
            {
                JOptionPane.showMessageDialog(null, "No sent messages found.");
            }
            else
            {
                StringBuilder report = new StringBuilder("Recent Sent Messages:\n\n");
        
            for (String msg : sentOnly)
            {
                report.append(msg).append("\n\n");
            }
        
            JOptionPane.showMessageDialog(null, report.toString());
        }                               
    }
}


/*
a)Display sender and recipient of all sent messages
b)Display longest message
c)Search for a message ID and display corresponding recipient and message
d)Search for all messages sent to a particular recipient
e)Delete a message using the message hash
f)Display a report that lists the full details of all the sent messages
*/