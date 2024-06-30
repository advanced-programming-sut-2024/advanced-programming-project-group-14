package model.abilities;

import model.Actionable;
import model.Card;

public class MoralBoost extends Card implements Actionable {


    public MoralBoost(String name, int power, int capacity, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, type, factionName, isHero, description);
    }

    @Override
    public void doAction() {

    }
}
