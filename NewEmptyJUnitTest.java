package com.mycompany.pt1prog;

import org.junit.Test;
import static org.junit.Assert.*;

public class NewEmptyJUnitTest {

    @Test
    public void testInvalidUsernameThroughLogin() {

        Login login = new Login(
                "kyle!!!!!",
                "Password1!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(login.checkUserName());
    }

    @Test
    public void testValidUsernameThroughLogin() {

        Login login = new Login(
                "kyl_1",
                "Password1!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(login.checkUserName());
    }

    @Test
    public void testLoginMessage() {

        Login login = new Login(
                "kyl_1",
                "Password1!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals(
                "Welcome Kyle Smith, it is great to see you.",
                login.returnLoginStatus()
        );
    }
}