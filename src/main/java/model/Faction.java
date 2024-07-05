package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Faction implements Serializable {
    private String name;
    private ArrayList<Commander> commanders = new ArrayList<>();
    private ArrayList<Card> cards;
    private static ArrayList<Faction> factions = new ArrayList<>();

    public Faction(String name){
        this.name = name;
        this.cards = new ArrayList<>();

        factions.add(this);
    }

    public Faction(Faction faction){
        this.name = faction.getName();
        this.commanders = new ArrayList<>(faction.getCommanders());
        this.cards = new ArrayList<>(faction.getCards());
    }

    public ArrayList<Commander> getCommanders() {
        return commanders;
    }


    public void setCommanders(ArrayList<Commander> commanders) {
        this.commanders = commanders;
    }

    public void addCommander(Commander commanders) {
        this.commanders.add(commanders);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public static Faction getFactionByName(String name) {
        for (Faction faction : factions) {
            if (faction.getName().equals(name)) {
                return faction;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public static ArrayList<Faction> getFactions() {
        return factions;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Commander getCommanderByName(String name) {
        for (Commander commander: commanders) {
            if (commander.getName().equals(name))
                return commander;
        }
        return null;
    }
}
