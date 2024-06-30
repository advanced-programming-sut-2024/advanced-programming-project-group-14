package model.abilities;

import model.Actionable;
import model.Card;

public class Mardroeme extends Card implements Actionable {


    public Mardroeme(String name, int power, int capacity, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, type, factionName, isHero, description);
    }

    @Override
    public void doAction() {

    }
}
