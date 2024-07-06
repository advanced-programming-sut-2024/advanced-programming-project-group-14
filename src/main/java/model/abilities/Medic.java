package model.abilities;

import controller.GameMenuController;
import model.Actionable;
import model.Card;

public class Medic extends Card implements Actionable {


    public Medic(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, ability, type, factionName, isHero, description);
    }

    @Override
    public void doAction(Object[] items) {
        Card card = (Card) items[1];
        switch (card.getType()) {
            case "Ranged Unit":
                GameMenuController.currentPlayer.getRangedCombat().getCards().add(card);
            case "Siege Unit":
                GameMenuController.currentPlayer.getSiege().getCards().add(card);
            default:
                GameMenuController.currentPlayer.getCloseCombat().getCards().add(card);
        }
    }
}
