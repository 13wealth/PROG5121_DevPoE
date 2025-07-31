/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.quickchat.RegiValidation;
import com.mycompany.quickchat.Login;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 * Runs JUnit tests: (assertEquals):
 *		-Cellphone number is correctly formatted
 *		-Cellphone number is incorrectly formatted
 *		-Username is correctly formatted
 *		-Username is incorrectly formatted
 *		-Password meets complexity requirements
 *		-Password does not meet complexity requirements

 * Runs JUnit tests: (assertTrue/False):
 *		-Login successful: True
 *		-Login failed: False
 *		-Username is correctly formatted: True
 *		-Username is incorrectly formatted: False
 *		-Password meets complexity requirements: True
 *		-Password does not meet complexity requirements: False
 *		-Cellphone number is correctly formatted: True
 *		-Cellphone number is incorrectly formatted: False
 */

public class QuickChat_Part1
{
    RegiValidation regTest_Obj = new RegiValidation();
    Login logTest_Obj = new Login();

    
//assertEquals   
@Test
    public void usernameCorrectFormat()
    {
        assertEquals(true,regTest_Obj.checkUserName("smi_1"));
    }

@Test
    public void usernameIncorrectFormat()
    {
        assertEquals(false,regTest_Obj.checkUserName("smit!!!!!!!"));
    }
    
@Test
    public void passwordCorretFormat()
    {
        assertEquals(true,regTest_Obj.checkPassWordComplexity("Ch&&sec@ke99!"));
    }
    
@Test
    public void passwordIncorretFormat()
    {
        assertEquals(false,regTest_Obj.checkPassWordComplexity("password"));
    }

@Test
    public void cellPhoneCorrectFormat()
    {
        assertEquals(true,regTest_Obj.checkCellPhoneNumber("+27838968976"));
    }

@Test
    public void cellPhoneIncorrectFormat()
    {
        assertEquals(false,regTest_Obj.checkCellPhoneNumber("08966553"));
    }

    
//assertTrue
@Test
    public void loginSuccess()
    {
        logTest_Obj.registerUser("Smith", "Mbele", "kyl_1", "Ch&&sec@ke99!");
        assertTrue(logTest_Obj.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }
    
@Test    
     public void loginFailed()
     {
        assertFalse(logTest_Obj.loginUser("kyle!!!!!!!", "password"));
     }
    
@Test
    public void correctUserNameFormat()
    {
        assertTrue(regTest_Obj.checkUserName("kyl_1"));
    }
    
@Test
    public void incorrectUserNameFormat()
    {
        assertFalse(regTest_Obj.checkUserName("kyle!!!!!!!"));
    }
    
@Test
    public void correctPasswordFormat()
    {
        assertTrue(regTest_Obj.checkPassWordComplexity("Ch&&sec@ke99!"));
    }
 
@Test
    public void incorrectPasswordFormat()
    {
        assertFalse(regTest_Obj.checkPassWordComplexity("password"));
    }
    
@Test
    public void correctCellPhoneFormat()
    {
        assertTrue(regTest_Obj.checkCellPhoneNumber("+27838968976"));
    }
    
@Test
    public void incorrectCellPhoneFormat()
    {
        assertFalse(regTest_Obj.checkCellPhoneNumber("08966553"));
    }
}