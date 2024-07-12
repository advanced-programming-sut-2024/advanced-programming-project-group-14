package model.abilities;

import model.Actionable;
import model.Card;
import model.Row;

public class TightBond extends Card implements Actionable {


    public TightBond(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, ability, type, factionName, isHero, description);
    }

    @Override
    public void doAction(Object[] items) {
        Row row =(Row) items[0];
        TightBond tightBond = (TightBond) items[1];
        int primitivePower = tightBond.getPower();
        int numOfSameCard = 0;
        for (Card card : row.getCards()) {
            if (card.getName().equals(tightBond.getName()))
                numOfSameCard++;
        }
        for (Card card : row.getCards()) {
            if (card.getName().equals(tightBond.getName()))
                card.setPower(primitivePower * numOfSameCard);
        }
    }
}
