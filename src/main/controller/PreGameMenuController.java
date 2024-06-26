package main.controller;

import main.model.Card;
import main.model.Faction;
import main.model.Player;
import main.model.Result;

import java.util.ArrayList;

public class PreGameMenuController {
    private static Player currentPlayer;
    private static Player opponentPlayer;

    public static Result showFaction() {

        return new Result(true, "");
    }

    public static Result selectFaction(String factionName) {

        return new Result(true, "");
    }

    public static Result showCards() {

        return new Result(true, "");
    }

    public static Result showDeck() {

        return new Result(true, "");
    }

    public static Result showCurrentUserInfo() {

        return new Result(true, "");
    }

    public static Result saveDeck(String flag, String input) {

        return new Result(true, "");
    }

    public static Result loadDeck(String flag, String input) {

        return new Result(true, "");
    }

    public static Result showLeaders() {

        return new Result(true, "");
    }

    public static Result selectLeader(int number) {

        return new Result(true, "");
    }

    public static Result addToDeck(String cardName, int count) {

        return new Result(true, "");
    }

    public static Result deleteFromDeck(int cardNumber, int count) {

        return new Result(true, "");
    }

    public static Result changeTurn() {

        return new Result(true, "");
    }

    public static Result startGame() {

        return new Result(true, "");
    }

    public static String getCurrentPlayerName(){
        return currentPlayer.getUsername();
    }

    public static ArrayList<Card> getCurrentPlayerDeck() {
        return currentPlayer.getDeck();
    }

    public static Faction getCurrentPlayerFaction() {
        return currentPlayer.getFaction();
    }

    public static String getCurrentPlayerFactionName() {
        if(currentPlayer.getFaction() == null)
            return "";
        return currentPlayer.getFaction().getName();
    }

    public static int getCurrentPlayerHandSize() {
        if(currentPlayer.getHand() == null)
            return 0;
        return currentPlayer.getHand().size();
    }

    public static int getCurrentPlayerNumberOfSoldiers() {
        int number = 0;
        for (Card card: currentPlayer.getDeck()) {
            if (card.getAbility() != "hero" || card.getType()!="spell" || card.getType()!="weather")
                number++;
        }
        return number;
    }

    public static int getCurrentPlayerNumberOfHeroes() {
        int number = 0;
        for (Card card: currentPlayer.getDeck()) {
            if (card.getAbility() == "hero")
                number++;
        }
        return number;
    }

    public static int getCurrentPlayerTotalDeckPower() {
        int power = 0;
        for (Card card: currentPlayer.getDeck()) {
                power+=card.getPower();
        }
        return power;
    }
}
