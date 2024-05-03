package model;

public abstract class Spell extends Card{

    public Spell(Card card) {
        super(card.getName(), card.getPower(), card.getType(), card.getAbility(), card.getDescription(), card.getValidRows());
    }
    abstract void action();
}
