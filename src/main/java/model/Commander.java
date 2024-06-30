package model;

public class Commander extends Card {
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


