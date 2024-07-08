package model.abilities;

import model.Actionable;
import model.Card;
import model.Row;

public class MoralBoost extends Card implements Actionable {


    public MoralBoost(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, ability, type, factionName, isHero, description);
    }

    @Override
    public void doAction(Object[] items) {
        Row row = (Row) items[0];
        MoralBoost moralBoost = (MoralBoost) items[1];
        for (Card card : row.getCards()) {
            if (card != moralBoost && !card.isHero())
                card.setMoralBoostAction(true);
        }
    }
}
