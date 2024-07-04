package model.abilities;

import controller.GameMenuController;
import model.*;

public class Scorch extends Card implements Actionable {


    public Scorch(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, ability, type, factionName, isHero, description);
    }

    private static int getMaxPowerInTable(Player currentPlayer, int maxPowerInTable) {
        for (Row playerRow : currentPlayer.getRows()) {
            for (Card card : playerRow.getCards()) {
                if (card.getPower() > maxPowerInTable && !card.isHero())
                    maxPowerInTable = card.getPower();
            }
        }
        return maxPowerInTable;
    }

    private static void deleteMaxPower(Player currentPlayer, int maxPowerInTable) {
        for (Row playerRow : currentPlayer.getRows()) {
            for (Card card : playerRow.getCards()) {
                if (card.getPower() == maxPowerInTable && !card.isHero()) {
                    currentPlayer.getDiscardPile().add(card);
                    playerRow.getCards().remove(card);
                }
            }
        }
    }

    @Override
    public void doAction(Object[] items) {
        Row row = (Row) items[0];
        Scorch scorch = (Scorch) items[1];
        if (scorch.getName().equals("Scorch")) {
            int maxPowerInTable = 0;
            maxPowerInTable = Math.max(getMaxPowerInTable(GameMenuController.currentPlayer, maxPowerInTable), getMaxPowerInTable(GameMenuController.opponentPlayer, maxPowerInTable)) ;
            deleteMaxPower(GameMenuController.currentPlayer, maxPowerInTable);
            deleteMaxPower(GameMenuController.opponentPlayer, maxPowerInTable);
        }
        else {
            int totalPowerInRow = 0;
            for (Card card : row.getCards()) {
                if (!card.isHero()) {
                    totalPowerInRow += card.getPower();
                }
            }
            if (totalPowerInRow > 10) {
                for (Card card : row.getCards()) {
                    if (!card.isHero()) {
                        GameMenuController.currentPlayer.getDiscardPile().add(card);
                        row.getCards().remove(card);
                    }
                }
            }
        }
    }
}
