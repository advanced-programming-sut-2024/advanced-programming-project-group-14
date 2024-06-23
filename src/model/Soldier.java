package model;

import java.util.ArrayList;

public abstract class Soldier extends Card{

    public Soldier(Card card) {
        super(card.getName(), card.getPower(), card.getCapacity(), card.getType(), card.getAbility(), card.getDescription(), card.getValidRows());
    }

    abstract void action();

}
