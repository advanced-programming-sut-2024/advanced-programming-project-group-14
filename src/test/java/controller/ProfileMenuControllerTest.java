package controller;

import model.Result;
import model.User;
import model.GameTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ProfileMenuControllerTest {

    @BeforeEach
    public void setUp() {
        // Reset the User class before each test
        User.resetUsers();
        User.setLoggedInUser(new User("testUser", "Test@1234", "testNick", "test@example.com"));
    }

    @Test
    public void testChangeUsernameSuccessful() {
        Result result = ProfileMenuController.changeUsername("newUser");
        assertTrue(result.isSuccessful());
        assertEquals("Username changed successfully", result.getMessage());
        assertEquals("newUser", User.getLoggedInUser().getUsername());
    }

    @Test
    public void testChangeUsernameTaken() {
        new User("existingUser", "password", "nick", "email@example.com");
        Result result = ProfileMenuController.changeUsername("existingUser");
        assertFalse(result.isSuccessful());
        assertEquals("Username is already taken!", result.getMessage());
    }

    @Test
    public void testChangeUsernameInvalid() {
        Result result = ProfileMenuController.changeUsername("invalid username");
        assertFalse(result.isSuccessful());
        assertEquals("Username is invalid!", result.getMessage());
    }

    @Test
    public void testChangeNickname() {
        Result result = ProfileMenuController.changeNickname("newNick");
        assertTrue(result.isSuccessful());
        assertEquals("Nickname changed successfully", result.getMessage());
        assertEquals("newNick", User.getLoggedInUser().getNickname());
    }

    @Test
    public void testChangeEmailSuccessful() {
        Result result = ProfileMenuController.changeEmail("new@example.com");
        assertTrue(result.isSuccessful());
        assertEquals("Email changed successfully", result.getMessage());
        assertEquals("new@example.com", User.getLoggedInUser().getEmail());
    }

    @Test
    public void testChangeEmailInvalid() {
        Result result = ProfileMenuController.changeEmail("invalid-email");
        assertFalse(result.isSuccessful());
        assertEquals("Email is invalid!", result.getMessage());
    }

    @Test
    public void testChangePasswordSuccessful() {
        Result result = ProfileMenuController.changePassword("NewTest@1234", "Test@1234");
        assertTrue(result.isSuccessful());
        assertEquals("Password changed successfully", result.getMessage());
        assertEquals("NewTest@1234", User.getLoggedInUser().getPassword());
    }

    @Test
    public void testChangePasswordIncorrectOldPassword() {
        Result result = ProfileMenuController.changePassword("NewTest@1234", "WrongOldPassword");
        assertFalse(result.isSuccessful());
        assertEquals("Password is incorrect!", result.getMessage());
    }

    @Test
    public void testChangePasswordInvalidNewPassword() {
        Result result = ProfileMenuController.changePassword("short", "Test@1234");
        assertFalse(result.isSuccessful());
        assertEquals("Password is to short!", result.getMessage());
    }

    @Test
    public void testNumberOfGameToShowDefault() {
        User.getLoggedInUser().setGamePlayed(new ArrayList<>());
        Result result = ProfileMenuController.numberOfGameToShow("");
        assertFalse(result.isSuccessful());
        assertEquals("You haven't played any games yet!", result.getMessage());
    }

    @Test
    public void testNumberOfGameToShowSpecificNumber() {
        ArrayList<GameTable> games = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            games.add(mock(GameTable.class));
        }
        User.getLoggedInUser().setGamePlayed(games);
        Result result = ProfileMenuController.numberOfGameToShow("3");
        assertTrue(result.isSuccessful());
        assertEquals("3", result.getMessage());
    }

    @Test
    public void testNumberOfGameToShowGreaterThanPlayed() {
        ArrayList<GameTable> games = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            games.add(mock(GameTable.class));
        }
        User.getLoggedInUser().setGamePlayed(games);
        Result result = ProfileMenuController.numberOfGameToShow("10");
        assertTrue(result.isSuccessful());
        assertEquals("3", result.getMessage());
    }

    @Test
    public void testNumberOfGameToShowInvalidNumber() {
        Result result = ProfileMenuController.numberOfGameToShow("0");
        assertFalse(result.isSuccessful());
        assertEquals("The number should be greater than 0", result.getMessage());
    }
}
