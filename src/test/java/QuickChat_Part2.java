/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.quickchat.JUnitValidations;
import com.mycompany.quickchat.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 */
public class QuickChat_Part2 
{
    @Test
    public void totalMessagesSent()
    {
        Message.sentMessages.add("Hi Mike, can you join us for dinner tonight");
        Message.sentMessages.add("Hi Keegan, did you recieve the payment?");

        assertEquals(2,Message.sentMessages.size());
    }
    
    @Test
    public void shortMessageLimit()
    {
        String shortMessage = "Hello, this is a test message.";
    
        assertEquals(true,JUnitValidations.validateMessageLength(shortMessage));
    }
    
    @Test
    public void longMessageLimit()
    {
        String longMessage = "Message exceeds 250 characters by 20, please reduce size.";
    
        assertEquals(false,JUnitValidations.validateMessageLength(longMessage));
    }
    
    @Test
    public void recipientNumberCorrectFormat()
    {
        assertEquals(true,JUnitValidations.checkRecipientCell("+27718693002"));
    }
    
    @Test
    public void recipientNumberIncorrectFormat()
    {
    assertEquals(false,JUnitValidations.checkRecipientCell("08575975889"));
    }
    
    @Test
    public void correctHash()
    {
    assertEquals(true,JUnitValidations.createMessageHash("00", 1, "HITONIGHT"));
    }   
}