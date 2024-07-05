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
        Row row = (Row) items[0];
        Card card = (Card) items[1];
        row.deleteFromCards(card);
        GameMenuController.currentPlayer.getHand().add(card);
    }
}
