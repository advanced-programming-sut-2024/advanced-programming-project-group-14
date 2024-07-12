package controller;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PreGameMenuControllerTest {

    @Mock
    private Player mockPlayer;

    @Mock
    private Player mockOpponentPlayer;

    @Mock
    private Faction mockFaction;

    @Mock
    private Commander mockCommander;

    @Mock
    private Card mockCard;
    @Mock
    private File mockFile;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        PreGameMenuController.currentPlayer = mockPlayer;
        PreGameMenuController.opponentPlayer = mockOpponentPlayer;
    }

    /*
    @Test
    public void testSelectFaction() {
        when(mockPlayer.getHand()).thenReturn(new ArrayList<>());
        Result result = PreGameMenuController.selectFaction(mockFaction);

        verify(mockPlayer).setFaction(mockFaction);
        assertTrue(result.isSuccessful());
        assertEquals("Selected successfully", result.getMessage());
    }

    @Test
    public void testSaveDeckByFileAddress() {
        String fileAddress = "path/to/deck";
        doNothing().when(mockPlayer).saveDeckByFileAddress(fileAddress);

        Result result = PreGameMenuController.saveDeck("-f", fileAddress);

        verify(mockPlayer).saveDeckByFileAddress(fileAddress);
        assertTrue(result.isSuccessful());
        assertEquals("saved successfully", result.getMessage());
    }

    @Test
    public void testSaveDeckByDeckName() {
        String deckName = "newDeck";
        Path path = Paths.get("data/decks/" + deckName);
        when(Files.exists(path)).thenReturn(false);
        doNothing().when(mockPlayer).saveDeckByDeckName(deckName);
        Result result = PreGameMenuController.saveDeck("-n", deckName);

        verify(mockPlayer).saveDeckByDeckName(deckName);
        assertTrue(result.isSuccessful());
        assertEquals("saved successfully", result.getMessage());
    }

    @Test
    public void testSaveDeckByDeckNameExists() {
        String deckName = "existingDeck";
        Path path = Paths.get("data/decks/" + deckName);
        when(Files.exists(path)).thenReturn(true);

        Result result = PreGameMenuController.saveDeck("-n", deckName);

        assertFalse(result.isSuccessful());
        assertEquals("deck name already exists", result.getMessage());
    }

    @Test
    public void testSaveDeckInvalidDeckName() {
        Result result = PreGameMenuController.saveDeck("-n", null);

        assertFalse(result.isSuccessful());
        assertEquals("invalid deck name", result.getMessage());
    }

    @Test
    public void testLoadDeck() {
        doNothing().when(mockPlayer).loadDeckByFile(mockFile);

        Result result = PreGameMenuController.loadDeck(mockFile);

        verify(mockPlayer).loadDeckByFile(mockFile);
        assertTrue(result.isSuccessful());
        assertEquals("loaded successfully", result.getMessage());
    }

    @Test
    public void testSelectLeader() {
        PreGameMenuController.selectLeader(mockCommander);

        verify(mockPlayer).setCommander(mockCommander);
    }

    @Test
    public void testAddToDeck() {
        when(mockCard.getType()).thenReturn("Special");
        when(mockPlayer.getDeck()).thenReturn(new ArrayList<>());
        doNothing().when(mockPlayer).addToDeck(mockCard);

        Result result = PreGameMenuController.addToDeck(mockCard);

        verify(mockPlayer).addToDeck(mockCard);
        assertTrue(result.isSuccessful());
        assertEquals("added successfully", result.getMessage());
    }

    @Test
    public void testAddToDeckInvalidCard() {
        Result result = PreGameMenuController.addToDeck(null);

        assertFalse(result.isSuccessful());
        assertEquals("invalid card name", result.getMessage());
    }

    @Test
    public void testDeleteFromDeck() {
        doNothing().when(mockPlayer).deleteFromDeck(mockCard);

        Result result = PreGameMenuController.deleteFromDeck(mockCard);

        verify(mockPlayer).deleteFromDeck(mockCard);
        assertTrue(result.isSuccessful());
        assertEquals("deleted successfully", result.getMessage());
    }
*/
    @Test
    public void testChangeTurnDeckNotFull() {
        when(mockPlayer.getDeck()).thenReturn(new ArrayList<>());

        Result result = PreGameMenuController.changeTurn();

        assertFalse(result.isSuccessful());
        assertEquals("Deck is not full", result.getMessage());
    }

    @Test
    public void testChangeTurnNoCommander() {
        ArrayList<Card> deck = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            deck.add(mockCard);
        }
        when(mockPlayer.getDeck()).thenReturn(deck);
        when(mockPlayer.getCommander()).thenReturn(null);

        Result result = PreGameMenuController.changeTurn();

        assertFalse(result.isSuccessful());
        assertEquals("Choose a leader please", result.getMessage());
    }

    @Test
    public void testChangeTurnOpponentTurn() {
        ArrayList<Card> playerDeck = new ArrayList<>();
        ArrayList<Card> opponentDeck = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            playerDeck.add(mockCard);
            opponentDeck.add(mockCard);
        }
        when(mockPlayer.getDeck()).thenReturn(playerDeck);
        when(mockPlayer.getCommander()).thenReturn(mockCommander);
        when(mockOpponentPlayer.getDeck()).thenReturn(opponentDeck);

        Result result = PreGameMenuController.changeTurn();

        assertFalse(result.isSuccessful());
        assertEquals("Your opponent has passed it's turn, please start the game", result.getMessage());
    }

    @Test
    public void testChangeTurnSuccess() {
        ArrayList<Card> playerDeck = new ArrayList<>();
        ArrayList<Card> opponentDeck = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            playerDeck.add(mockCard);
            //opponentDeck.add(mockCard);
        }
        when(mockPlayer.getDeck()).thenReturn(playerDeck);
        when(mockPlayer.getCommander()).thenReturn(mockCommander);
        when(mockOpponentPlayer.getDeck()).thenReturn(opponentDeck);
        when(mockOpponentPlayer.getUsername()).thenReturn("Opponent");

        Result result = PreGameMenuController.changeTurn();

        assertTrue(result.isSuccessful());
        assertEquals("Your turn: Opponent", result.getMessage());
    }

    @Test
    public void testStartGameDeckNotFull() {
        when(mockPlayer.getDeck()).thenReturn(new ArrayList<>());
        when(mockOpponentPlayer.getDeck()).thenReturn(new ArrayList<>());

        Result result = PreGameMenuController.startGame();

        assertFalse(result.isSuccessful());
        assertEquals("One of decks is not full", result.getMessage());
    }

    @Test
    public void testStartGameSuccess() {
        ArrayList<Card> playerDeck = new ArrayList<>();
        ArrayList<Card> opponentDeck = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            playerDeck.add(mockCard);
            opponentDeck.add(mockCard);
        }
        when(mockPlayer.getDeck()).thenReturn(playerDeck);
        when(mockOpponentPlayer.getDeck()).thenReturn(opponentDeck);

        Result result = PreGameMenuController.startGame();

        assertTrue(result.isSuccessful());
        assertEquals("Welcome to the game!", result.getMessage());
    }

    @Test
    public void testGetCurrentPlayerFactionName() {
        when(mockPlayer.getFaction()).thenReturn(mockFaction);
        when(mockFaction.getName()).thenReturn("FactionName");

        String factionName = PreGameMenuController.getCurrentPlayerFactionName();

        assertEquals("FactionName", factionName);
    }

    @Test
    public void testGetCurrentPlayerHandSize() {
        when(mockPlayer.getDeck()).thenReturn(new ArrayList<>());

        int handSize = PreGameMenuController.getCurrentPlayerHandSize();

        assertEquals(0, handSize);
    }

    @Test
    public void testGetCurrentPlayerNumberOfSoldiers() {
        ArrayList<Card> deck = new ArrayList<>();
        Card soldierCard = mock(Card.class);
        when(soldierCard.getType()).thenReturn("Soldier");
        deck.add(soldierCard);

        when(mockPlayer.getDeck()).thenReturn(deck);

        int numberOfSoldiers = PreGameMenuController.getCurrentPlayerNumberOfSoldiers();

        assertEquals(1, numberOfSoldiers);
    }

    @Test
    public void testGetCurrentPlayerNumberOfHeroes() {
        ArrayList<Card> deck = new ArrayList<>();
        Card heroCard = mock(Card.class);
        when(heroCard.isHero()).thenReturn(true);
        deck.add(heroCard);

        when(mockPlayer.getDeck()).thenReturn(deck);

        int numberOfHeroes = PreGameMenuController.getCurrentPlayerNumberOfHeroes();

        assertEquals(1, numberOfHeroes);
    }

    @Test
    public void testGetCurrentPlayerTotalDeckPower() {
        ArrayList<Card> deck = new ArrayList<>();
        Card card = mock(Card.class);
        when(card.getPower()).thenReturn(10);
        deck.add(card);

        when(mockPlayer.getDeck()).thenReturn(deck);

        int totalPower = PreGameMenuController.getCurrentPlayerTotalDeckPower();

        assertEquals(10, totalPower);
    }
}
