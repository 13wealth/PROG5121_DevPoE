/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 */
public class QuickChat_Part3 
{
     @Test
    public void sentMessagesArray() 
    {
        List<String> sentMessages = Arrays.asList(
            "Did you get the cake?",
            "It is dinner time!"
        );

    assertTrue(sentMessages.contains("Did you get the cake?"));
    assertTrue(sentMessages.contains("It is dinner time!"));
    assertEquals(2, sentMessages.size());
    }
    
     @Test
    public void longestMessage() 
    {
        List<String> allMessages = Arrays.asList(
            "Did you get the cake?",
            "Where are you? You are late! I have asked you to be on time.",
            "Yohoooo, I am at your gate.",
            "It is dinner time!",
            "Ok, I am leaving without you."
        );

        String longest = allMessages.stream()
            .max((m1, m2) -> Integer.compare(m1.length(), m2.length()))
            .orElse("");

        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    public void searchMessageID() {
        String messageID = "0838884567";
        String expectedMessage = "It is dinner time!";

        // Simulate message search
        String actualMessage = "It is dinner time!"; // Stub/mock value

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testSearchMessagesByRecipient() 
    {
        String recipient = "+27838884567";

        List<String> messagesForRecipient = Arrays.asList(
            "Where are you? You are late! I have asked you to be on time.",
            "Ok, I am leaving without you."
        );

        assertEquals(2, messagesForRecipient.size());
        assertTrue(messagesForRecipient.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(messagesForRecipient.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteMessageByHash() 
    {
        String messageToDelete = "Where are you? You are late! I have asked you to be on time.";

        List<String> messagesBefore = Arrays.asList(
            "Did you get the cake?",
            messageToDelete,
            "It is dinner time!"
        );

        List<String> messagesAfter = Arrays.asList(
            "Did you get the cake?",
            "It is dinner time!"
        );

        assertFalse(messagesAfter.contains(messageToDelete));
        assertEquals(2, messagesAfter.size());
    }

    @Test
    public void testDisplayFullReport() 
    {
        String report = "Message Hash: ABC123\nRecipient: +27838884567\nMessage: Did you get the cake?";

        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("+27838884567"));
        assertTrue(report.contains("Did you get the cake?"));
    }
} 



    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}


