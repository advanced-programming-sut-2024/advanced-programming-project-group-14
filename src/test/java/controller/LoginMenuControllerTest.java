package controller;

import model.Question;
import model.Result;
import model.User;
import model.UsersManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginMenuControllerTest {

    @Mock
    private UsersManager mockUsersManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        User.resetUsers();  // Reset user list before each test
        Question.getQuestions().clear();
    }


    @Test
    public void testLoginStayLoggedIn() {
        User user = new User("testUser", "Test@1234", "testNick", "test@example.com");
        LoginMenuController loginMenuController = new LoginMenuController(mockUsersManager);

        Result result = loginMenuController.login("testUser", "Test@1234", true);

        assertTrue(result.isSuccessful());
        assertEquals("Login successful", result.getMessage());
        verify(mockUsersManager).saveStayLoggedInUser(user);
    }

    @Test
    public void testLoginNotStayLoggedIn() {
        User user = new User("testUser", "Test@1234", "testNick", "test@example.com");
        LoginMenuController loginMenuController = new LoginMenuController(mockUsersManager);

        Result result = loginMenuController.login("testUser", "Test@1234", false);

        assertTrue(result.isSuccessful());
        assertEquals("Login successful", result.getMessage());
        assertEquals(user, User.getLoggedInUser());
        verify(mockUsersManager, never()).saveStayLoggedInUser(any(User.class));
    }

    @Test
    public void testLoginSuccessful() {
        String username = "testUser";
        String password = "Test@1234";
        User user = new User(username, password, "testNick", "test@example.com");

        Result result = LoginMenuController.login(username, password, false);

        assertTrue(result.isSuccessful());
        assertEquals("Login successful", result.getMessage());
        assertEquals(user, User.getLoggedInUser());
    }

    @Test
    public void testLoginUsernameNotFound() {
        String username = "nonExistentUser";
        String password = "Test@1234";

        Result result = LoginMenuController.login(username, password, false);

        assertFalse(result.isSuccessful());
        assertEquals("Username not found!", result.getMessage());
    }

    @Test
    public void testLoginPasswordIncorrect() {
        String username = "testUser";
        String password = "Test@1234";
        User user = new User(username, password, "testNick", "test@example.com");

        Result result = LoginMenuController.login(username, "WrongPassword", false);

        assertFalse(result.isSuccessful());
        assertEquals("Password incorrect!", result.getMessage());
    }

    @Test
    public void testChangePassword() {
        String username = "testUser";
        String password = "Test@1234";
        User user = new User(username, password, "testNick", "test@example.com");

        String newPassword = "NewTest@1234";
        LoginMenuController.changePassword(user, newPassword);

        assertEquals(newPassword, user.getPassword());
    }

    @Test
    public void testCheckAnswerCorrect() {
        String username = "testUser";
        String password = "Test@1234";
        String questionText = "Sample Question?";
        String answer = "Sample Answer";
        Question question = new Question(1, questionText);
        User user = new User(username, password, "testNick", "test@example.com");
        user.setQuestion(new Question(question, answer));

        Result result = LoginMenuController.checkAnswer(user, answer);

        assertTrue(result.isSuccessful());
    }

    @Test
    public void testCheckAnswerIncorrect() {
        String username = "testUser";
        String password = "Test@1234";
        String questionText = "Sample Question?";
        String answer = "Sample Answer";
        Question question = new Question(1, questionText);
        User user = new User(username, password, "testNick", "test@example.com");
        user.setQuestion(new Question(question, answer));

        Result result = LoginMenuController.checkAnswer(user, "Wrong Answer");

        assertFalse(result.isSuccessful());
        assertEquals("Your answer is wrong", result.getMessage());
    }
}
