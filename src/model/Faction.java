package model;

import java.util.ArrayList;

public class Faction {

    private ArrayList<Commander> commanders;

    public Faction(ArrayList<Commander> commanders) {
        this.commanders = commanders;
    }

    public ArrayList<Commander> getCommanders() {
        return commanders;
    }
}
