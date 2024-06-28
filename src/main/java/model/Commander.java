package model;

public class Commander extends Card {
    private Faction faction;
    private int number;

    public Commander(Card card, Faction faction) {
        super(card.getName(), card.getPower(), card.getCapacity(),card.getType(), card.getAbility(), card.getDescription(), card.getPhotoName(), card.getValidRows());
        this.faction = faction;
    }
    public Faction getFaction() {
        return faction;
    }

    public int getNumber() {
        return number;
    }
}


