package controller;

import model.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

public class PreGameMenuController {
    public static Player currentPlayer;
    public static Player opponentPlayer;

    public static Result selectFaction(Faction faction) {
        currentPlayer.getHand().clear();
        currentPlayer.setFaction(faction);
        return new Result(true, "Selected successfully");
    }

    public static Result saveDeck(String flag, String input) {
        if (flag.equals("-f")) {
            String fileAddress = input;
            currentPlayer.saveDeckByFileAddress(fileAddress);
        } else if (flag.equals("-n")) {
            String deckName = input;
            Path path = Paths.get("data/decks/" + deckName);
            if (deckName == null) {
                return new Result(false, "invalid deck name");
            }
            if (Files.exists(path)) {
                return new Result(false, "deck name already exists");
            }
            currentPlayer.saveDeckByDeckName(deckName);
        }
        return new Result(true, "saved successfully");
    }

    public static Result loadDeck(File file) {
        currentPlayer.loadDeckByFile(file);
        return new Result(true, "loaded successfully");
    }

    public static void selectLeader(Commander commander) {
        currentPlayer.setCommander(commander);
    }

    public static Result addToDeck(Card card) {
        if (card == null) {
            return new Result(false, "invalid card name");
        }
        if (currentPlayer.getDeck().size() >= 22) {
            return new Result(false, "deck is full");
        }
        if (card.getCapacity() <= 0) {
            return new Result(false, "card capacity is zero");
        }
        if (card.getType().equals("spell") || card.getType().equals("weather")) {
            if (currentPlayer.numberOfSpecificCardInDeck() >= 10) {
                return new Result(false, "you can't have more than 10 special cards in your deck");
            }
        }

        currentPlayer.getFaction().getCards().remove(card);
        currentPlayer.addToDeck(card);
        return new Result(true, "added successfully");
    }

    public static Result deleteFromDeck(Card card) {
        currentPlayer.getFaction().getCards().add(card);
        currentPlayer.deleteFromDeck(card);
        return new Result(true, "deleted successfully");
    }

    public static Result changeTurn() {
        if (currentPlayer.getDeck().size() < 22) {
            return new Result(false, "deck is not full");
        }
        Player temp = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = temp;
        return new Result(true, "Your turn: " + currentPlayer.getUsername());
    }

    public static Result startGame() {
        if (currentPlayer.getDeck().size() < 22 || opponentPlayer.getDeck().size() < 22) {
            return new Result(false, "One of decks is not full");
        }

        new GameTable(Date.from(new Date().toInstant()), currentPlayer, opponentPlayer);
        return new Result(true, "Welcome to the game!");
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static String getCurrentPlayerFactionName() {
        if (currentPlayer.getFaction() == null)
            return "";
        return currentPlayer.getFaction().getName();
    }

    public static int getCurrentPlayerHandSize() {
        if (currentPlayer.getDeck() == null)
            return 0;
        return currentPlayer.getDeck().size();
    }

    public static int getCurrentPlayerNumberOfSoldiers() {
        int number = 0;
        for (Card card : currentPlayer.getDeck()) {
            if (!card.isHero() || card.getType() != "spell")
                number++;
        }
        return number;
    }

    public static int getCurrentPlayerNumberOfHeroes() {
        int number = 0;
        for (Card card : currentPlayer.getDeck()) {
            if (card.isHero())
                number++;
        }
        return number;
    }

    public static int getCurrentPlayerTotalDeckPower() {
        int power = 0;
        for (Card card : currentPlayer.getDeck()) {
            power += card.getPower();
        }
        return power;
    }

}
