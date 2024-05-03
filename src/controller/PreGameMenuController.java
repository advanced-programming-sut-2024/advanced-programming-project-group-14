package controller;

import model.Player;
import model.Result;

public class PreGameMenuController {

    public static Player currentPlayer;
    public static Player opponentPlayer;

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

}
