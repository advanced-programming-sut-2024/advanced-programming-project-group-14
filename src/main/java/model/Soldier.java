package model;

public abstract class Soldier extends Card{

    public Soldier(Card card) {
        super(card.getName(), card.getPower(), card.getCapacity(), card.getType(), card.getFactionName(), card.isHero(), card.getDescription());
    }

    abstract void action();

}
