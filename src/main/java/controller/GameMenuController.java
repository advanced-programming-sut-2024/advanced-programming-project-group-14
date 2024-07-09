package controller;


import model.*;
import model.abilities.*;
import view.GameMenuView;

import java.sql.Array;
import java.util.ArrayList;
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
            Card toAdd1 = opponentPlayer.getDeck().get(chosenCard1);

            currentPlayer.getDeck().remove(toAdd);
            currentPlayer.getHand().add(toAdd);

            opponentPlayer.getDeck().remove(toAdd1);
            opponentPlayer.getHand().add(toAdd1);
        }
    }

    public static void placeCard(Card card, String rowName, Card targetCard) {
        if (card.getAbility().equals("Decoy") && targetCard == null) return;

        if (rowName.equals("Weather")) {
            Object[] objects = {null, card};
            if (card.getAbility().equals("Scorch"))
                ((Scorch) card).doAction(objects);
            else
                ((Weather) card).doAction(objects);
            currentGameTable.addToWeather(card);
            currentPlayer.getHand().remove(card);
            changeTurn();
            return;
        }

        Row row = getRow(card, rowName, false);

        if (card.getType().equals("Special") && !card.getName().equals("Decoy")) {
            if (rowName.equals("Close Combat Unit") && currentPlayer.getCloseCombat().getSpecial() == null)
                row.setSpecial(card);
            else if (rowName.equals("Ranged Unit") && currentPlayer.getRangedCombat().getSpecial() == null)
                row.setSpecial(card);
            else if (rowName.equals("Siege Unit") && currentPlayer.getSiege().getSpecial() == null)
                row.setSpecial(card);
            else return;
        } else {
            if (rowName.equals("Close Combat Unit") && (card.getType().equals("Close Combat Unit") || (card.getType().equals("Special")) || card.getType().equals("Agile Unit")))
                row.addToCards(card);
            else if (rowName.equals("Ranged Unit") && (card.getType().equals("Ranged Unit") || (card.getType().equals("Special")) || card.getType().equals("Agile Unit")))
                row.addToCards(card);
            else if (rowName.equals("Siege Unit") && card.getType().equals("Siege Unit") || (card.getType().equals("Special")))
                row.addToCards(card);
            else return;
        }

        currentPlayer.getHand().remove(card);
        checkExistActionableCardInRow(row);


        row = getRow(card, rowName, true);

        Object[] forAction = {row, card, targetCard};

        doAction(card, forAction);

        changeTurn();
    }

    public static void doAction(Card card, Object[] forAction) {
        if (card.getAbility() != null) {
            switch (card.getAbility()) {
                case "CommandersHorn":
                    ((CommandersHorn) card).doAction(forAction);
                    break;
                case "Decoy":
                    ((Decoy) card).doAction(forAction);
                    break;
                case "Mardroem":
                    ((Mardroeme) card).doAction(forAction);
                    break;
                case "Medic":
                    ((Medic) card).doAction(forAction);
                    changeTurn();
                    break;
                case "MoralBoost":
                    ((MoralBoost) card).doAction(forAction);
                    break;
                case "Muster":
                    ((Muster) card).doAction(forAction);
                    break;
                case "Scorch":
                    ((Scorch) card).doAction(forAction);
                    break;
                case "Spy":
                    ((Spy) card).doAction(forAction);
                    break;
                case "TightBond":
                    ((TightBond) card).doAction(forAction);
                    break;
                //case "Transformers": ((Transformers) card).doAction(forAction); break;
            }
        }
    }

    private static void checkExistActionableCardInRow(Row row) {
        for (Card card : row.getCards()) {
            if (card.getAbility().equals("CommandersHorn")) ((CommandersHorn) card).doAction(new Object[]{row});
            if (card.getAbility().equals("MoralBoost")) ((MoralBoost) card).doAction(new Object[]{row, card});
        }
    }

    public static void changeTurn() {
        if (opponentPlayer.isPassed())
            return;
        Player tempPlayer = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = tempPlayer;
    }

    private static Row getRow(Card card, String rowName, boolean forAction) {
        Row row = switch (rowName) {
            case "Ranged Unit" -> currentPlayer.getRangedCombat();
            case "Siege Unit" -> currentPlayer.getSiege();
            default -> currentPlayer.getCloseCombat();
        };

        if ((forAction && card.getAbility().equals("Scorch")) || card.getAbility().equals("Spy")) {
            row = switch (rowName) {
                case "Ranged Unit" -> opponentPlayer.getRangedCombat();
                case "Siege Unit" -> opponentPlayer.getSiege();
                default -> opponentPlayer.getCloseCombat();
            };
        }
        return row;
    }

    public static void vetoCard(Card card) {
        Random random = new Random();
        Card cardToAdd = currentPlayer.getDeck().get(random.nextInt(0, currentPlayer.getDeck().size()));
        currentPlayer.getHand().add(cardToAdd);
        currentPlayer.getHand().remove(card);
        currentPlayer.increaseNumberOfVetoUse();
    }

    public static void resetRow(Row row) {
        for (Card card : row.getCards()) {
            card.setMoralBoostAction(false);
            card.setCommandersHornAction(false);
        }
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

    public static void reviveCard(Card card) {
        currentPlayer.getDiscardPile().remove(card);
        String rowName = card.getType();
        if (card.getType().contains("Agile")) rowName = "Close Combat Unit";
        placeCard(card, rowName, null);
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

    public static void endTurn() {
        checkForRoundWinner();
        clearTable();
        currentPlayer.setPassed(false);
        opponentPlayer.setPassed(false);
        currentGameTable.increaseRoundNumber();

    }

    private static void checkForRoundWinner() {
        int roundNumber = currentGameTable.getRoundNumber();
        currentPlayer.setScoresOfRound(roundNumber, currentPlayer.calculateTotalScore());
        opponentPlayer.setScoresOfRound(roundNumber, opponentPlayer.calculateTotalScore());
        if (currentPlayer.getScoreOfRound(roundNumber) < opponentPlayer.getScoreOfRound(roundNumber))
            currentPlayer.decreaseLife();
        else
            opponentPlayer.decreaseLife();

        if (currentPlayer.getLives()==0 || opponentPlayer.getLives()==0)
            endGame();
    }

    private static void clearTable() {
        for (Row row : currentPlayer.getRows()) {
            currentPlayer.getDiscardPile().addAll(row.getCards());
            row.getCards().clear();
            row.setSpecial(null);
        }
        for (Row row : opponentPlayer.getRows()) {
            opponentPlayer.getDiscardPile().addAll(row.getCards());
            row.getCards().clear();
            row.setSpecial(null);
        }
        currentGameTable.setWeather(new ArrayList<>());
    }

    public void discardSpells() {

    }

    public static void endGame() {
        currentPlayer.addGamePlayed(currentGameTable);
        opponentPlayer.addGamePlayed(currentGameTable);
        new GameMenuView().endGame();
    }

    public int CalculatePlayersTotalScore() {
        return 0;
    }

}
