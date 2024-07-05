package model;

public class Weather extends Card implements Actionable{


    public Weather(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, ability, type, factionName, isHero, description);
    }

    @Override
    public void doAction(Object[] items) {

    }
}
