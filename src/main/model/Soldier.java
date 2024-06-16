package main.model;

public abstract class Soldier extends Card{

    public Soldier(Card card) {
        super(card.getName(), card.getPower(), card.getType(), card.getAbility(), card.getDescription(), card.getValidRows());
    }

    abstract void action();

}
