package model;

public abstract class Spell extends Card{

    public Spell(Card card) {
        super(card.getName(), card.getPower(), card.getCapacity(), card.getAbility(),  card.getFactionName(), card.getType(), card.isHero(), card.getDescription());
    }
    abstract void action();
}
