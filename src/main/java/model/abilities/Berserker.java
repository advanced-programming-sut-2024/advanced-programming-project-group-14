package model.abilities;

import model.Actionable;
import model.Card;

public class Berserker extends Card implements Actionable {


    public Berserker(String name, int power, int capacity, String type, String faction, boolean isHero, String description) {
        super(name, power, capacity, type, faction, isHero, description);
    }

    @Override
    public void doAction() {

    }
}
