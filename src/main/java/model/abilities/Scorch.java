package model.abilities;

import controller.GameMenuController;
import model.Actionable;
import model.Card;
import model.Player;
import model.Row;

import java.util.ArrayList;

public class Scorch extends Card implements Actionable {


    public Scorch(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, ability, type, factionName, isHero, description);
    }

    private static int getMaxPowerInTable(Player currentPlayer, int maxPowerInTable) {
        for (Row playerRow : currentPlayer.getRows()) {
            for (Card card : playerRow.getCards()) {
                if (card.getCurrentPower() > maxPowerInTable && !card.isHero())
                    maxPowerInTable = card.getCurrentPower();
            }
        }
        return maxPowerInTable;
    }

    private static void deleteMaxPower(Player currentPlayer, int maxPowerInTable) {
        ArrayList<Card> cardsToRemove = new ArrayList<>();
        for (Row playerRow : currentPlayer.getRows()) {
            for (Card card : playerRow.getCards()) {
                if (card.getCurrentPower() == maxPowerInTable && !card.isHero()) {
                    currentPlayer.getDiscardPile().add(card);
                    cardsToRemove.add(card);
                    removeCardsInRow(playerRow, card);
                }
            }
            playerRow.getCards().removeAll(cardsToRemove);
        }
    }

    private static void removeCardsInRow(Row playerRow, Card card) {
        GameMenuController.resetRow(playerRow);
        if (card.getAbility().equals("MoralBoost") || card.getAbility().equals("CommandersHorn")) {
            GameMenuController.resetRow(playerRow);
            for (Card rowCard : playerRow.getCards())
                if (rowCard.getAbility().equals("MoralBoost") || rowCard.getAbility().equals("CommandersHorn")) {
                    GameMenuController.doAction(rowCard, new Object[]{playerRow, rowCard});
                }
        }
        if (playerRow.getSpecial() != null && playerRow.getSpecial().getAbility().equals("CommandersHorn")) GameMenuController.doAction(Card.getCardByName("CommandersHorn"), new Object[]{playerRow});
    }

    @Override
    public void doAction(Object[] items) {
        Row row = (Row) items[0];
        Scorch scorch = (Scorch) items[1];
        if (scorch.getName().equals("Scorch")) {
            int maxPowerInTable = 0;
            maxPowerInTable = Math.max(getMaxPowerInTable(GameMenuController.currentPlayer, maxPowerInTable), getMaxPowerInTable(GameMenuController.opponentPlayer, maxPowerInTable));
            deleteMaxPower(GameMenuController.currentPlayer, maxPowerInTable);
            deleteMaxPower(GameMenuController.opponentPlayer, maxPowerInTable);
        } else {
            int totalPowerInRow = row.getTotalScore();

            if (totalPowerInRow > 10) {
                int maxPowerInRow = 0;
                for (Card card : row.getCards()) {
                    if (card.getCurrentPower() > maxPowerInRow && !card.isHero())
                        maxPowerInRow = card.getCurrentPower();
                }
                ArrayList<Card> cardsToRemove = new ArrayList<>();
                for (Card card : row.getCards()) {
                    if (!card.isHero() && card.getCurrentPower() == maxPowerInRow) {
                        removeCardsInRow(row, card);
                        cardsToRemove.add(card);
                        GameMenuController.opponentPlayer.getDiscardPile().add(card);
                    }
                }
                row.getCards().removeAll(cardsToRemove);
            }
        }
    }
}
