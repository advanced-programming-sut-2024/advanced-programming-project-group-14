package model;

public abstract class Soldier extends Card{

    public Soldier(Card card) {
        super(card.getName(), card.getPower(), card.getCapacity(), card.getType(), card.getAbility(), card.getDescription(), card.getPhotoName(), card.getValidRows());
    }

    abstract void action();

}
