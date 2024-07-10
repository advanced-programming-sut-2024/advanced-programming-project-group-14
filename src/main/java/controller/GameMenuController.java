package controller;


import model.*;
import model.abilities.*;
import view.GameMenuView;

import java.util.ArrayList;
import java.util.Random;

public class GameMenuController {
    public static GameTable currentGameTable;
    public static Player currentPlayer;
    public static Player opponentPlayer;

    public static void loadHand() {
        Random random = new Random();

        int capacityOfHand = 10;

        if (currentPlayer.getCommander().getName().equals("DaisyoftheValley"))
            capacityOfHand = 11;

        loadHandOfEachPlayer(random, capacityOfHand, currentPlayer);
        if (!opponentPlayer.getCommander().getName().equals("DaisyoftheValley"))
            capacityOfHand = 10;

        loadHandOfEachPlayer(random, capacityOfHand, opponentPlayer);


    }

    private static void loadHandOfEachPlayer(Random random, int capacityOfHand, Player opponentPlayer) {
        for (int i = 0; i < capacityOfHand; i++) {
            int chosenCard = random.nextInt(0, opponentPlayer.getDeck().size());
            Card toAdd = opponentPlayer.getDeck().get(chosenCard);
            opponentPlayer.getDeck().remove(toAdd);
            opponentPlayer.getHand().add(toAdd);
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
                case "Mardroeme":
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
        if (row.getSpecial() != null && row.getSpecial().getAbility().equals("CommandersHorn"))
            ((CommandersHorn) row.getSpecial()).doAction(new Object[]{row});
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

    public static void reviveCard(Card card) {
        currentPlayer.getDiscardPile().remove(card);
        String rowName = card.getType();
        if (card.getType().contains("Agile")) rowName = "Close Combat Unit";
        placeCard(card, rowName, null);
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

        if (currentPlayer.getLives() == 0 || opponentPlayer.getLives() == 0)
            endGame();
    }

    private static void clearTable() {
        for (Row row : currentPlayer.getRows()) {
            currentPlayer.getDiscardPile().addAll(row.getCards());
            row.getCards().clear();
            row.setWeatherAction(false);
            row.setSpecial(null);
        }
        for (Row row : opponentPlayer.getRows()) {
            opponentPlayer.getDiscardPile().addAll(row.getCards());
            row.getCards().clear();
            row.setWeatherAction(false);
            row.setSpecial(null);
        }
        currentGameTable.setWeather(new ArrayList<>());
    }

    public static void endGame() {
        currentPlayer.addGamePlayed(currentGameTable);
        opponentPlayer.addGamePlayed(currentGameTable);
        new GameMenuView().endGame();
    }

    public static void playCommanderPower() {
        currentPlayer.setUsedCommanderAction(true);
        switch (currentPlayer.getCommander().getName()) {
            case "TheSiegemaster":
                placeCardOfCommanderAction(Card.getCardByName("Impenetrablefog"));
                break;
            case "TheSteel-Forged":
                ((Weather) Card.getCardByName("ClearWeather")).doAction(new Object[]{null, Card.getCardByName("ClearWeather")});
                changeTurn();
                break;
            case "KingofTemeria":
                ((CommandersHorn) Card.getCardByName("CommandersHorn")).doAction(new Object[]{currentPlayer.getSiege()});
                ((CommandersHorn) Card.getCardByName("CommandersHorn")).doAction(new Object[]{opponentPlayer.getSiege()});
                changeTurn();
                break;
            case "LordCommanderoftheNorth":
                ((Scorch) Card.getCardByName("Villentretenmerth")).doAction(new Object[]{currentPlayer.getSiege(), Card.getCardByName("Villentretenmerth")});
                ((Scorch) Card.getCardByName("Villentretenmerth")).doAction(new Object[]{opponentPlayer.getSiege(), Card.getCardByName("Villentretenmerth")});
                changeTurn();
                break;
            case "SonofMedell":
                ((Scorch) Card.getCardByName("Villentretenmerth")).doAction(new Object[]{currentPlayer.getRangedCombat(), Card.getCardByName("Villentretenmerth")});
                ((Scorch) Card.getCardByName("Villentretenmerth")).doAction(new Object[]{opponentPlayer.getRangedCombat(), Card.getCardByName("Villentretenmerth")});
                changeTurn();
                break;
            case "TheWhiteFlame":
                placeCardOfCommanderAction(Card.getCardByName("TorrentialRain"));
                break;
            case "HisImperialMajesty":
                break;
            case "EmperorofNilfgaard":
                break;
            case "TheRelentless":
                break;
            case "InvaderoftheNorth":
                invaderoftheNorthAction();
                changeTurn();
                break;
            case "BringerofDeath":
                ((CommandersHorn) Card.getCardByName("CommandersHorn")).doAction(new Object[]{currentPlayer.getCloseCombat()});
                ((CommandersHorn) Card.getCardByName("CommandersHorn")).doAction(new Object[]{opponentPlayer.getCloseCombat()});
                changeTurn();
                break;
            case "KingofthewildHunt":
                ((Medic) Card.getCardByName("MennoCoehoorn")).doAction(new Object[]{});
                break;
            case "DestroyerofWorlds":
                break;
            case "CommanderoftheRedRiders":
                commanderoftheRedRidersAction();
                changeTurn();
                break;
            case "TheTreacherous":
                theTreacherousAction();
                changeTurn();
                break;
            case "QueenofDolBlathanna":
                queenofDolBlathanna();
                changeTurn();
                break;
            case "TheBeautiful":
                ((CommandersHorn) Card.getCardByName("CommandersHorn")).doAction(new Object[]{currentPlayer.getRangedCombat()});
                ((CommandersHorn) Card.getCardByName("CommandersHorn")).doAction(new Object[]{opponentPlayer.getRangedCombat()});
                changeTurn();
                break;
            case "DaisyoftheValley": //Done
                break;
            case "PurebloodElf":
                placeCardOfCommanderAction(Card.getCardByName("BitingFrost"));
                break;
            case "HopeoftheAenSeidhe":
                break;
            case "CrachanCraite":
                crachanCraiteAction();
                changeTurn();
                break;
            case "KingBran":
                break;

        }

    }

    private static void queenofDolBlathanna() {
        if (opponentPlayer.getCloseCombat().getTotalScore() > 10) {
            int maxPower = 0;
            for (Card card : opponentPlayer.getRangedCombat().getCards())
                if (card.getCurrentPower() > maxPower && !card.isHero()) maxPower = card.getCurrentPower();
            for (Card card : opponentPlayer.getRangedCombat().getCards()) {
                if (card.getCurrentPower() == maxPower && !card.isHero()) {
                    opponentPlayer.getRangedCombat().getCards().remove(card);
                    break;
                }
            }
        }
    }

    private static void invaderoftheNorthAction() {
        Random random = new Random();
        reviveCard(currentPlayer.getHand().get(random.nextInt(0, currentPlayer.getHand().size())));
    }

    private static void commanderoftheRedRidersAction() {
        for (Card card : currentPlayer.getHand()) {
            if (card.getType().equals("Weather")) {
                placeCard(card, "Weather", null);
                break;
            }
        }
    }

    private static void theTreacherousAction() {
        for (Row row : currentPlayer.getRows()) {
            for (Card card : row.getCards()) {
                if (card.getAbility().equals("Spy"))
                    card.setCurrentPower(card.getCurrentPower() * 2);
            }
        }
        for (Row row : opponentPlayer.getRows()) {
            for (Card card : row.getCards()) {
                if (card.getAbility().equals("Spy"))
                    card.setCurrentPower(card.getCurrentPower() * 2);
            }
        }
    }

    private static void crachanCraiteAction() {
        for (Card card : currentPlayer.getDiscardPile())
            currentPlayer.getDeck().add(card);
        currentPlayer.getDiscardPile().clear();
        for (Card card : opponentPlayer.getDiscardPile())
            opponentPlayer.getDeck().add(card);
        opponentPlayer.getDiscardPile().clear();

    }

    private static void placeCardOfCommanderAction(Card card1) {
        for (Card card : currentPlayer.getHand()) {
            if (card.getName().equals(card1.getName())) {
                placeCard(card, "Weather", null);
                break;
            }
        }
    }


    public static void increaseHitPointCheat() {
        if (currentPlayer.getLives() == 1)
            currentPlayer.setLives(currentPlayer.getLives() + 1);
    }

    public static void addChosenCardCheat() {
        GameMenuView.showFactionCard();
    }

    public static void addSpyCardCheat() {
        for (Card card : Card.getCards()) {
            if (card.getAbility().equals("Spy") && card.getFactionName().equals(currentPlayer.getFaction().getName()))
                currentPlayer.getHand().add(card);
        }
    }

    public static void addMoralBoostCardCheat() {
        for (Card card : Card.getCards()) {
            if (card.getAbility().equals("MoralBoost") && card.getFactionName().equals(currentPlayer.getFaction().getName()))
                currentPlayer.getHand().add(card);
        }
    }

    public static void addHeroCardCheat() {
        for (Card card : Card.getCards()) {
            if (card.isHero() && card.getFactionName().equals(currentPlayer.getFaction().getName()))
                currentPlayer.getHand().add(card);
        }
    }

    public static void addClearWeatherCheat() {
        for (Card card : Card.getCards()) {
            if (card.isHero() && card.getFactionName().equals(currentPlayer.getFaction().getName()))
                currentPlayer.getHand().add(card);
        }
    }

    public static void changeTurnCheat() {
        changeTurn();
    }

}
