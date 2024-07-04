package model.abilities;

import model.Actionable;
import model.Card;
import model.Row;

public class Transformers extends Card implements Actionable {


    public Transformers(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, ability, type, factionName, isHero, description);
    }

    @Override
    public void doAction(Object[] items) {
        Transformers transformers = (Transformers) items[0];
        Row row = (Row) items[1];
        if (transformers.getName().equals("Cow"))
            transformers.setPower(8);
        else
            transformers.setPower(11);
        row.addToCards(transformers);
    }
}
