package main.model;

import java.util.ArrayList;

public class Faction {
    private String name;
    private String photoName;
    private ArrayList<Commander> commanders;

    private static ArrayList<Faction> factions = new ArrayList<>();

    public Faction(ArrayList<Commander> commanders) {
        this.commanders = commanders;
    }

    public static ArrayList<Faction> getFactions() {
        return factions;
    }

    public ArrayList<Commander> getCommanders() {
        return commanders;
    }

    public String getName() {
        return this.name;
    }

    public String getPhotoName() {
        return this.photoName;
    }
}
