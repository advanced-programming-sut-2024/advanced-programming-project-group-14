package model.abilities;

import controller.GameMenuController;
import model.Actionable;
import model.Card;
import model.Row;

public class Decoy extends Card implements Actionable {


    public Decoy(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, ability, type, factionName, isHero, description);
    }

    @Override
    public void doAction(Object[] items) {
        Card card = (Card) items[0];
        Row row = (Row) items[1];
        row.getCards().remove(card);
        GameMenuController.currentPlayer.getHand().add(card);
    }
}
