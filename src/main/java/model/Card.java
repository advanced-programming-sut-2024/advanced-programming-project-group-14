package model;

import java.util.ArrayList;

public class Card {
    private String name;
    private int power;
    private int capacity;
    private String ability;
    private String type;
    private String factionName;
    private boolean isHero;
    private String description;
    private String currentPlace;
    private static ArrayList<Card> cards = new ArrayList<>();

    public Card(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        this.name = name;
        this.power = power;
        this.ability = ability;
        this.type = type;
        this.capacity = capacity;
        this.factionName = factionName;
        this.isHero = isHero;
        this.description = description;
    }

    public static ArrayList<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public String getAbility() {
        return ability;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getType() {
        return type;
    }
    public boolean isHero() {
        return isHero;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getFactionName() {
        return factionName;
    }

    public static Card getCardByName(String name){
        for (Card card : cards) {
            if (card.getName().equals(name)){
                return card;
            }
        }
        return null;
    }

}
