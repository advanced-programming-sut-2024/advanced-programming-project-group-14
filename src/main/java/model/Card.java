package model;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Card implements Serializable {
    private static ArrayList<Card> cards = new ArrayList<>();
    private String name;
    private boolean commandersHornAction;
    private boolean moralBoostAction;
    private int power;
    private int currentPower;
    private int capacity;
    private String ability;
    private String type;
    private String factionName;
    private boolean isHero;
    private String description;
    private Row currentRow;
    private String currentPlace;
    private Label label;

    public Card(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        this.name = name;
        this.power = power;
        this.currentPower = power;
        this.ability = ability;
        this.type = type;
        this.capacity = capacity;
        this.factionName = factionName;
        this.isHero = isHero;
        this.description = description;
        this.moralBoostAction = false;
        this.commandersHornAction = false;
        cards.add(this);

        if (factionName.equals("Neutral")) {
            for (Faction faction : Faction.getFactions())
                faction.addCard(this);
        } else
            Faction.getFactionByName(factionName).addCard(this);
    }

    public static ArrayList<Card> getCards() {
        return cards;
    }

    public static Card getCardByName(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name)) {
                return card;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getAbility() {
        return ability;
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

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void calcCurrentPower() {
        currentPower = power;
        if (currentRow != null && currentRow.isWeatherAction() && !isHero) currentPower = 1;
        if (moralBoostAction) currentPower += 1;
        if (commandersHornAction) currentPower *= 2;
    }

    public int getCurrentPower() {
        calcCurrentPower();
        return currentPower;
    }

    public void setCommandersHornAction(boolean commandersHornAction) {
        this.commandersHornAction = commandersHornAction;
    }

    public void setMoralBoostAction(boolean moralBoostAction) {
        this.moralBoostAction = moralBoostAction;
    }

    public Row getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(Row currentRow) {
        this.currentRow = currentRow;
    }

    public void setCurrentPower(int currentPower) {
        this.currentPower = currentPower;
    }
}
