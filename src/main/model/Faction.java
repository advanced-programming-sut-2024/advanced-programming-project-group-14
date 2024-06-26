package model;

import java.util.ArrayList;

public class Faction {
    private String name;
    private String photoName;
    private ArrayList<Commander> commanders;

    private static ArrayList<Faction> factions;

    public Faction(ArrayList<Commander> commanders) {
        this.commanders = commanders;
    }

    public ArrayList<Commander> getCommanders() {
        return commanders;
    }

    public Commander getCommanderByNumber(int number){
        for (Commander commander : commanders) {
            if (commander.getNumber() == number){
                return commander;
            }
        }
        return null;
    }
    public static Faction getFactionByName(String name){
        for (Faction faction : factions) {
            if (faction.getName().equals(name)){
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

}
