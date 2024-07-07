package controller;


import model.*;
import model.abilities.*;

import java.util.Random;

public class GameMenuController {
    public static GameTable currentGameTable;
    public static Player currentPlayer;
    public static Player opponentPlayer;

    public static void loadHand() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int chosenCard = random.nextInt(0, currentPlayer.getDeck().size());
            int chosenCard1 = random.nextInt(0, opponentPlayer.getDeck().size());

            Card toAdd = currentPlayer.getDeck().get(chosenCard);
            Card toAdd1 = currentPlayer.getDeck().get(chosenCard1);

            currentPlayer.getDeck().remove(toAdd);
            currentPlayer.getHand().add(toAdd);

            opponentPlayer.getDeck().remove(toAdd1);
            opponentPlayer.getHand().add(toAdd1);
        }
    }

    public static void placeCard(Card card, String rowName) {
        if (rowName == null) {
            currentGameTable.addToWeather(card);
            return;
        }

        Row row = switch (rowName) {
            case "Ranged Unit" -> currentPlayer.getRangedCombat();
            case "Siege Unit" -> currentPlayer.getSiege();
            default -> currentPlayer.getCloseCombat();
        };

        if (card.getType().equals("Special")) {
            if (rowName.equals("Close Combat Unit") && currentPlayer.getCloseCombat().getSpecial() == null)
                currentPlayer.getCloseCombat().setSpecial(card);
            if (rowName.equals("Ranged Unit") && currentPlayer.getRangedCombat().getSpecial() == null)
                currentPlayer.getRangedCombat().setSpecial(card);
            if (rowName.equals("Siege Unit") && currentPlayer.getSiege().getSpecial() == null)
                currentPlayer.getSiege().setSpecial(card);
        } else {
            if (rowName.equals("Close Combat Unit") && (card.getType().equals("Close Combat Unit") || card.getType().equals("Agile Unit")))
                currentPlayer.getCloseCombat().addToCards(card);
            if (rowName.equals("Ranged Unit") && (card.getType().equals("Ranged Unit") || card.getType().equals("Agile Unit")))
                currentPlayer.getRangedCombat().addToCards(card);
            if (rowName.equals("Siege Unit") && card.getType().equals("Siege Unit"))
                currentPlayer.getSiege().addToCards(card);
        }
        currentPlayer.getHand().remove(card);

        Object[] forAction = {row, card};

        if (card.getAbility() != null) {
            switch (card.getAbility()) {
                case "CommandersHorn": ((CommandersHorn) card).doAction(forAction); break;
                case "Decoy": ((Decoy) card).doAction(forAction); break;
                case "Mardroem": ((Mardroeme) card).doAction(forAction); break;
                case "Medic": ((Medic) card).doAction(forAction); break;
                case "MoralBoost": ((MoralBoost) card).doAction(forAction); break;
                case "Muster": ((Muster) card).doAction(forAction); break;
                case "Scorch": ((Scorch) card).doAction(forAction); break;
                case "Spy": ((Spy) card).doAction(forAction); break;
                case "TightBond": ((TightBond) card).doAction(forAction); break;
                case "Transformers": ((Transformers) card).doAction(forAction); break;
            }
        }

        Player tempPlayer = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = tempPlayer;
    }

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
