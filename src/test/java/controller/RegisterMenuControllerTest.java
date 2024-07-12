package controller;

import model.Question;
import model.Result;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegisterMenuControllerTest {

    @BeforeEach
    public void setUp() {
        // Reset the User class before each test
        User.resetUsers();
    }

    @Test
    public void testRegisterSuccessful() {
        String username = "testUser";
        String password = "Test@1234";
        String passwordConfirm = "Test@1234";
        String nickname = "testNick";
        String email = "test@example.com";

        Result result = RegisterMenuController.register(username, password, passwordConfirm, nickname, email);

        assertTrue(result.isSuccessful());
        assertEquals("Register successful", result.getMessage());
        assertNotNull(User.getLoggedInUser());
        assertEquals(username, User.getLoggedInUser().getUsername());
    }

    @Test
    public void testRegisterUsernameTaken() {
        String username = "testUser";
        String password = "Test@1234";
        String passwordConfirm = "Test@1234";
        String nickname = "testNick";
        String email = "test@example.com";

        User existingUser = new User(username, password, nickname, email);

        Result result = RegisterMenuController.register(username, password, passwordConfirm, nickname, email);

        assertFalse(result.isSuccessful());
        assertTrue(result.getMessage().contains("Username is already taken"));
    }

    @Test
    public void testRegisterInvalidUsername() {
        String username = "invalid username!";
        String password = "Test@1234";
        String passwordConfirm = "Test@1234";
        String nickname = "testNick";
        String email = "test@example.com";

        Result result = RegisterMenuController.register(username, password, passwordConfirm, nickname, email);

        assertFalse(result.isSuccessful());
        assertEquals("Username is invalid!", result.getMessage());
    }

    @Test
    public void testRegisterInvalidEmail() {
        String username = "testUser";
        String password = "Test@1234";
        String passwordConfirm = "Test@1234";
        String nickname = "testNick";
        String email = "invalidEmail";

        Result result = RegisterMenuController.register(username, password, passwordConfirm, nickname, email);

        assertFalse(result.isSuccessful());
        assertEquals("Email is invalid!", result.getMessage());
    }

    @Test
    public void testCheckPassword() {
        String password = "Test@1234";
        String passwordConfirm = "Test@1234";

        Result result = RegisterMenuController.checkPassword(password, passwordConfirm);

        assertTrue(result.isSuccessful());
    }

    @Test
    public void testCheckPasswordTooShort() {
        String password = "Tes@1";
        String passwordConfirm = "Tes@1";

        Result result = RegisterMenuController.checkPassword(password, passwordConfirm);

        assertFalse(result.isSuccessful());
        assertEquals("Password is to short!", result.getMessage());
    }

    @Test
    public void testCheckPasswordNoUpperCase() {
        String password = "test@1234";
        String passwordConfirm = "test@1234";

        Result result = RegisterMenuController.checkPassword(password, passwordConfirm);

        assertFalse(result.isSuccessful());
        assertEquals("Password should have at least one lowercase letter and one uppercase letter", result.getMessage());
    }

    @Test
    public void testCheckPasswordNoNumber() {
        String password = "Test@word";
        String passwordConfirm = "Test@word";

        Result result = RegisterMenuController.checkPassword(password, passwordConfirm);

        assertFalse(result.isSuccessful());
        assertEquals("Password should have at least one number", result.getMessage());
    }

    @Test
    public void testCheckPasswordNoSpecialChar() {
        String password = "Test1234";
        String passwordConfirm = "Test1234";

        Result result = RegisterMenuController.checkPassword(password, passwordConfirm);

        assertFalse(result.isSuccessful());
        assertEquals("Password should have at least one special character", result.getMessage());
    }

    @Test
    public void testCheckPasswordMismatch() {
        String password = "Test@1234";
        String passwordConfirm = "Test@4321";

        Result result = RegisterMenuController.checkPassword(password, passwordConfirm);

        assertFalse(result.isSuccessful());
        assertEquals("Passwords are not same", result.getMessage());
    }

    @Test
    public void testGenerateRandomPassword() {
        String password = RegisterMenuController.generateRandomPassword();

        assertNotNull(password);
        assertTrue(password.length() >= 10);
        assertTrue(password.matches(".*[a-z].*"));
        assertTrue(password.matches(".*[A-Z].*"));
        assertTrue(password.matches(".*[0-9].*"));
        assertTrue(password.matches(".*[!@#$%^&*].*"));
    }

    @Test
    public void testPickQuestion() {
        User user = mock(User.class);
        User.setLoggedInUser(user);

        RegisterMenuController.pickQuestion(1, "Sample Answer");

        verify(user).setQuestion(any(Question.class));
    }
}
