/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

/**
 *
 * @author RC_Student_lab
 */
public class JUnitValidations 
{
     public static String validateMessageLength(String text) 
     {
        if (text.length() > 250) {
            return "Message exceeds 250 characters by 20, please reduce size.";
        } else {
            return "Message ready to send";
        }
    }
}

