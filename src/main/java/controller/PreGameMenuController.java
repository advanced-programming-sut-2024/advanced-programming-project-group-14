package controller;

import model.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class PreGameMenuController {
    public static Player currentPlayer;
    public static Player opponentPlayer;

    public static void selectFaction(Faction faction) {
        currentPlayer.setCommander(null);
        currentPlayer.getHand().clear();
        currentPlayer.setFaction(faction);
    }

    public static void saveDeck(String flag, String input) {
        if (flag.equals("-f")) {
            String fileAddress = input;
            currentPlayer.saveDeckByFileAddress(fileAddress);
        } else if (flag.equals("-n")) {
            String deckName = input;
            Path path = Paths.get("data/decks/" + deckName);
            if (deckName == null) {
                return;
            }
            if (Files.exists(path)) {
                return;
            }
            currentPlayer.saveDeckByDeckName(deckName);
        }
    }

    public static void loadDeck(File file) {
        currentPlayer.loadDeckByFile(file);
    }

    public static void selectLeader(Commander commander) {
        currentPlayer.setCommander(commander);
    }

    public static Result addToDeck(Card card) {
        if (card == null) {
            return new Result(false, "invalid card name");
        }
        if (card.getType().equals("Special") || card.getType().equals("Weather")) {
            if (getCurrentPlayerHandSize() - getCurrentPlayerNumberOfSoldiers() >= 10) {
                return new Result(false, "you can't have more than 10 special cards in your deck");
            }
        }

        currentPlayer.getFaction().removeCard(card);
        currentPlayer.addToDeck(card);
        return new Result(true, "added successfully");
    }

    public static void deleteFromDeck(Card card) {
        currentPlayer.getFaction().addCard(card);
        currentPlayer.deleteFromDeck(card);
    }

    public static Result changeTurn() {
        if (currentPlayer.getDeck().size() < 22) return new Result(false, "Deck is not full");
        if (currentPlayer.getCommander() == null) return new Result(false, "Choose a leader please");
        if (currentPlayer.getDeck().size() >= 22 && opponentPlayer.getDeck().size() >= 22)
            return new Result(false, "Your opponent has passed it's turn, please start the game");
        Player temp = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = temp;
        return new Result(true, "Your turn: " + currentPlayer.getUsername());
    }

    public static Result startGame() {
        if (currentPlayer.getDeck().size() < 22 || opponentPlayer.getDeck().size() < 22) {
            return new Result(false, "One of decks is not full");
        }
        if (currentPlayer.getCommander() == null) return new Result(false, "Choose a leader please");

        GameMenuController.currentGameTable = new GameTable(Date.from(new Date().toInstant()), currentPlayer, opponentPlayer);

        if (currentPlayer.getFaction().getName().equals("Scoiatael") && !opponentPlayer.getFaction().getName().equals("Scoiatael")) {
            GameMenuController.currentPlayer = currentPlayer;
            GameMenuController.opponentPlayer = opponentPlayer;
        } else {
            GameMenuController.currentPlayer = opponentPlayer;
            GameMenuController.opponentPlayer = currentPlayer;
        }
        return new Result(true, "Welcome to the game!");
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static String getCurrentPlayerFactionName() {
        if (currentPlayer.getFaction() == null) return "";
        return currentPlayer.getFaction().getName();
    }

    public static int getCurrentPlayerHandSize() {
        if (currentPlayer.getDeck() == null) return 0;
        return currentPlayer.getDeck().size();
    }

    public static int getCurrentPlayerNumberOfSoldiers() {
        int number = 0;
        for (Card card : currentPlayer.getDeck()) {
            if (card.getType() != "Special" && card.getType() != "Weather") number++;
        }
        return number;
    }

    public static int getCurrentPlayerNumberOfHeroes() {
        int number = 0;
        for (Card card : currentPlayer.getDeck()) {
            if (card.isHero()) number++;
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
