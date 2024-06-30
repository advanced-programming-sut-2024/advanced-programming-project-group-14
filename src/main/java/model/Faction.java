package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Faction {
    private String name;
    private String photoName;
    private ArrayList<Commander> commanders = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();

    private static ArrayList<Faction> factions = new ArrayList<>();

    public Faction(String name, String photoName){
        this.name = name;
        this.photoName = photoName;

        factions.add(this);
    }

    public Faction(Faction faction){
        this.name = faction.getName();
        this.photoName = faction.getName();
        this.commanders = new ArrayList<>(faction.getCommanders());
        this.cards = new ArrayList<>(faction.getCards());
    }

    public ArrayList<Commander> getCommanders() {
        return commanders;
    }

    public Commander getCommanderByNumber(int number) {
        for (Commander commander : commanders) {
            if (commander.getNumber() == number) {
                return commander;
            }
        }
        return null;
    }

    public void setCommanders(ArrayList<Commander> commanders) {
        this.commanders = commanders;
    }

    public void addCommander(Commander commanders) {
        this.commanders.add(commanders);
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

    public String getPhotoName() {
        return this.photoName;
    }

    public static ArrayList<Faction> getFactions() {
        return factions;
    }

    public static Faction getFactionByPhotoName(String photoName) {
        for (Faction faction : factions) {
            if (faction.getPhotoName().equals(photoName)) {
                return faction;
            }
        }
        return null;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

}
