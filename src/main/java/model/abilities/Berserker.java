package model.abilities;

import model.Actionable;
import model.Card;

public class Berserker extends Card implements Actionable {


    public Berserker(String name, int power, int capacity, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, type, factionName, isHero, description);
    }

    @Override
    public void doAction() {

    }
}
