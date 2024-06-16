package main.model;

import java.util.ArrayList;

public class Card {

    private String name;
    private int power;
    private String type;
    private String ability;
    private String description;
    private ArrayList<Integer> validRows;

    public Card(String name, int power, String type, String ability, String description, ArrayList<Integer> validRows) {
        this.name = name;
        this.power = power;
        this.type = type;
        this.ability = ability;
        this.description = description;
        this.validRows = validRows;
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

    public ArrayList<Integer> getValidRows() {
        return validRows;
    }
}
