package model;

import java.util.ArrayList;

public class Card {

    private String name;
    private int power;
    private String type;
    private int capacity;
    private String ability;
    private String description;
    private String photoName;
    private int validRows;
    private static ArrayList<Card> cards = new ArrayList<>();

    public Card(String name, int power, int capacity, String type, String ability, String description, String photoName, int validRows) {
        this.name = name;
        this.power = power;
        this.type = type;
        this.capacity = capacity;
        this.ability = ability;
        this.description = description;
        this.photoName = photoName;
        this.validRows = validRows;
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

    public String getType() {
        return type;
    }

    public String getAbility() {
        return ability;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getValidRows() {
        return validRows;
    }

    public static Card getCardByName(String name){
        for (Card card : cards) {
            if (card.getName().equals(name)){
                return card;
            }
        }
        return null;
    }

    public String getPhotoName() {
        return photoName;
    }
}
