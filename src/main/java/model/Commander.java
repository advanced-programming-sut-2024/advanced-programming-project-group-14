package model;

import java.io.Serializable;

public class Commander extends Card implements Serializable {
    private Faction faction;
    private int number;

    public Commander(Card card, Faction faction) {
        super(card.getName(), card.getPower(), card.getCapacity(),card.getType(), card.getFactionName(), card.isHero(), card.getDescription());
        this.faction = faction;

        faction.addCommander(this);
    }

    public Faction getFaction() {
        return faction;
    }

    public int getNumber() {
        return number;
    }
}


