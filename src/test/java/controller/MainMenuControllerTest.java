package controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import model.Player;
import model.Result;
import model.User;
import model.UsersManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MainMenuControllerTest {

    @Mock
    private UsersManager mockUsersManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        User.resetUsers(); // فرض بر این است که متدی برای ریست کردن کاربران وجود دارد.
        new MainMenuController(mockUsersManager);
    }

    @Test
    public void testLogout() {
        User user = new User("testUser", "Test@1234", "testNick", "test@example.com");
        User.setLoggedInUser(user);
        MainMenuController.logout();

        assertNull(User.getLoggedInUser());
        verify(mockUsersManager).saveStayLoggedInUser(null);
    }

    @Test
    public void testCreateGameOpponentNotFound() {
        Result result = MainMenuController.createGame("unknownUser");

        assertFalse(result.isSuccessful());
        assertEquals("Opponent not found!", result.getMessage());
    }

    @Test
    public void testCreateGameSelfPlay() {
        User user = new User("testUser", "Test@1234", "testNick", "test@example.com");
        User.setLoggedInUser(user);

        Result result = MainMenuController.createGame("testUser");

        assertFalse(result.isSuccessful());
        assertEquals("You can not play with yourself!!", result.getMessage());
    }

    @Test
    public void testCreateGameSuccess() {
        User user1 = new User("testUser1", "Test@1234", "testNick1", "test1@example.com");
        User user2 = new User("testUser2", "Test@1234", "testNick2", "test2@example.com");
        User.setLoggedInUser(user1);

        Result result = MainMenuController.createGame("testUser2");

        assertTrue(result.isSuccessful());
        assertEquals("welcome to pregame :)", result.getMessage());
        assertNotNull(PreGameMenuController.currentPlayer);
        assertNotNull(PreGameMenuController.opponentPlayer);
    }
}
