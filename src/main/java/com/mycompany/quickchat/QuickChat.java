/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author RC_Student_lab
 */
public class QuickChat 
{
    public static void main(String[] args) 
    {
        Registration regObj = new Registration();
        Login logObj = new Login();

        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField cellphoneField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

            String regName = "", 
            regSurname = "", 
            cellNumber = "", 
            regUser = "", regPass = "";
            boolean validRegistration = false;

//Registration loop
        while (!validRegistration) 
        {
            JPanel regPanel = new JPanel(new GridLayout(0, 1));
            regPanel.add(new JLabel("Name:"));
            regPanel.add(nameField);
            regPanel.add(new JLabel("Surname:"));
            regPanel.add(surnameField);
            regPanel.add(new JLabel("Cellphone"));
            regPanel.add(cellphoneField);
            regPanel.add(new JLabel("Username"));
            regPanel.add(usernameField);
            regPanel.add(new JLabel("Password"));
            regPanel.add(passwordField);

            int result = JOptionPane.showConfirmDialog(
                                                null,
                                                regPanel,
                                                "QUICK-CHAT REGISTRATION",
                                                JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.PLAIN_MESSAGE
                );
            if (result == JOptionPane.OK_OPTION) 
            {
                regName = nameField.getText().trim();
                regSurname = surnameField.getText().trim();
                cellNumber = cellphoneField.getText().trim();
                regUser = usernameField.getText().trim();
                regPass = new String(passwordField.getPassword());

                boolean validName = !regName.isEmpty() && !regSurname.isEmpty();
                boolean validCell = regObj.checkCellPhoneNumber(cellNumber);
                boolean validUser = regObj.checkUserName(regUser);
                boolean validPass = regObj.checkPassWordComplexity(regPass);

                    if (!validName || !validCell || !validUser || !validPass) 
                    {
                        JOptionPane.showMessageDialog(null, """
                                One or more fields are invalid. Please fix:
                                - Name/Surname must not be empty
                                - Cellphone must be a valid SA number with country code
                                - Username must contain "_" and be 5 chars max
                                - Password must meet complexity requirements
                                """, "INVALID DETAILS", JOptionPane.WARNING_MESSAGE);
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "🎉 Registration Successful!");
                            validRegistration = true;
                                logObj.registerUser(regName, regSurname, regUser, regPass);
                    }
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "Registration Cancelled.");
                    System.exit(0);
            }
        }

//Login loop
        boolean loginSuccess = false;
        String logUser, logPass;

        JOptionPane.showMessageDialog(
                                null, 
                                "Enter your login details", 
                                "WELCOME TO QUICK-CHAT", 
                                JOptionPane.INFORMATION_MESSAGE
             );
        do 
        {
            logUser = JOptionPane.showInputDialog(
                                            null, 
                                            "Username:", 
                                            "LOGIN", 
                                            JOptionPane.QUESTION_MESSAGE);
                             DialogHelper.exitIfCancelled(logUser);

            logPass = JOptionPane.showInputDialog(
                                            null, 
                                            "Password:", 
                                            "LOGIN", 
                                            JOptionPane.QUESTION_MESSAGE);
                            DialogHelper.exitIfCancelled(logPass);

            loginSuccess = logObj.loginUser(logUser.trim(), logPass.trim());

                if (!loginSuccess) 
                {
                    JOptionPane.showMessageDialog(
                                            null, 
                                            "Login failed. Please try again.", 
                                            "ERROR", 
                                            JOptionPane.ERROR_MESSAGE);
                } 
                else 
                {
                    JOptionPane.showMessageDialog(
                                            null, 
                                            "Login Successful!", 
                                            "WELCOME", 
                                            JOptionPane.INFORMATION_MESSAGE);
                }
        } while (!loginSuccess);

//Main menu
        boolean quit = false;
        Messaging msgObj = new Messaging();
        Statistics statObj = new Statistics();

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat", " ", JOptionPane.INFORMATION_MESSAGE);
        while (!quit) 
        {
            String menu = JOptionPane.showInputDialog("""
                    SELECT AN OPTION
                    (1) Send Message
                    (2) Show recent messages
                    (3) View Statistics
                    (4) Quit
                    """);
            DialogHelper.exitIfCancelled(menu);

            switch (menu) 
            {
                case "1" -> msgObj.sendMessage();
                case "2" -> Messaging.recentMessages();
                case "3" -> statObj.messageStats(logObj);
                case "4" -> quit = true;
            }
        }
    }
}

/**
* References:
* OpenAI. (2025, May 1). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat 
* 
*/ 
