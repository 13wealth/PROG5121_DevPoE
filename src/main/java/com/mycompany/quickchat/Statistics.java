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
                                                            (2) View Longest message
                                                            (3) Search by Message ID      
                                                            (4) Search by Recipient
                                                            (5) Delete message by Hash      
                                                            (6) Main menu
                                                            """, 
                                    "STATISTICS MENU", 3);
                    DialogHelper.exitIfCancelled(menu);   
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