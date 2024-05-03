package model;

public abstract class Commander extends Card {

    private Faction faction;

    public Commander(Card card, Faction faction) {
        super(card.getName(), card.getPower(), card.getType(), card.getAbility(), card.getDescription(), card.getValidRows());
        this.faction = faction;
    }

    public Faction getFaction() {
        return faction;
    }

    abstract void action();

}


