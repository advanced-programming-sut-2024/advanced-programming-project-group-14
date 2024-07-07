package model.abilities;

import model.Actionable;
import model.Card;
import model.Row;

public class CommandersHorn extends Card implements Actionable {


    public CommandersHorn(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, ability, type, factionName, isHero, description);
    }

    @Override
    public void doAction(Object[] items) {
        Row row = (Row) items[0];
        for (Card card : row.getCards()) {
            if (!card.isHero() && !card.getAbility().equals("CommandersHorn"))
                card.setPower(card.getPower() * 2);
        }
    }
}
