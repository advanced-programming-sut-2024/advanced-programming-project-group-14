package controller;


import model.Card;
import model.GameTable;
import model.Player;
import model.Result;

import java.util.Random;

public class GameMenuController {
    public static GameTable currentGameTable;
    public static Player currentPlayer;
    public static Player opponentPlayer;

    public void vetoCard(Card card) {
        Random random = new Random();
        Card cardToAdd = currentPlayer.getDeck().get(random.nextInt(0, currentPlayer.getDeck().size()));
        currentPlayer.getHand().add(cardToAdd);
        currentPlayer.getHand().remove(card);
    }

    public String showDeck() {
        return "";
    }

    public Result showInHand(int cardNumber) {
        return new Result(true, "");
    }

    public Result getNumOfRemainingCards() {
        return new Result(true, "");
    }

    public Result showDiscardPile() {
        return new Result(true, "");
    }

    public Result showCardsInRow(int rowNumber) {
        return new Result(true, "");
    }

    public Result showSpellInPlay() {
        return new Result(true, "");
    }

    public void placeCard(Card card, String row) {
        if (row == null) {
            currentGameTable.addToWeather(card);
            return;
        }
        if (card.getType().equals("Special")) {
        switch (row) {
                case "Close Combat Unit":
                    currentPlayer.getCloseCombat().addToCards(card);
                case "Ranged Unit":
                    currentPlayer.getRangedCombat().addToCards(card);
                case "Siege Unit":
                    currentPlayer.getSiege().addToCards(card);
            }
        }
        else {
            switch (row) {
                case "Close Combat Unit":
                    currentPlayer.getCloseCombat().setSpecial(card);
                case "Ranged Unit":
                    currentPlayer.getRangedCombat().setSpecial(card);
                case "Siege Unit":
                    currentPlayer.getSiege().setSpecial(card);
            }
        }
    }

    public void doAction(Card card) {

    }

    public void reviveCard(Card card) {

    }

    public Result showCommander() {
        return new Result(true, "");
    }

    public void playCommanderPower() {

    }

    public Result showPlayersInfo() {
        return new Result(true, "");
    }

    public Result showPlayersLives() {
        return new Result(true, "");
    }

    public Result showNumberOfCardsInHand() {
        return new Result(true, "");
    }

    public Result showTurnInfo() {
        return new Result(true, "");
    }

    public Result showTotalScore() {
        return new Result(true, "");
    }

    public Result showTotalScoreOfRow(int rowNumber) {
        return new Result(true, "");
    }

    public void passRound() {

    }

    public void endTurn() {

    }

    public void disCardSpells() {

    }

    public void endGame() {

    }

    public int CalculatePlayersTotalScore() {
        return 0;
    }


}
