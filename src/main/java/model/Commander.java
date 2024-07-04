package model;

import java.io.Serializable;

public class Commander implements Serializable {
    private String factionName;
    private String name;

    public Commander(String factionName, String name) {
        this.factionName = factionName;
        this.name = name;

        Faction.getFactionByName(factionName).addCommander(this);
    }

    public String getFaction() {
        return factionName;
    }

    public String getName() {
        return name;
    }
}


