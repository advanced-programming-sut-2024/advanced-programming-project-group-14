package model.abilities;

import model.Actionable;
import model.Card;
import model.Row;

public class Mardroeme extends Card implements Actionable {


    public Mardroeme(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, ability, type, factionName, isHero, description);
    }

    @Override
    public void doAction(Object[] items) {
        Row row = (Row) items[0];
        for (Card card : row.getCards()) {
            if (card.getName().equals("Berserker")) {
                row.deleteFromCards(card);
                row.addToCards(Card.getCardByName("Vidkaarl"));
            }
            else if (card.getName().equals("YoungBerserker")){
                row.deleteFromCards(card);
                row.addToCards(Card.getCardByName("YoungVidkaarl"));
            }
        }

    }
}
