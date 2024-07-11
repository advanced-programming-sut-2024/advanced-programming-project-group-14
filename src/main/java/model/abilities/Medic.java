package model.abilities;

import controller.GameMenuController;
import model.Actionable;
import model.Card;
import view.GameMenuView;

public class Medic extends Card implements Actionable {


    public Medic(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, ability, type, factionName, isHero, description);
    }

    @Override
    public void doAction(Object[] items) {
        GameMenuView.showDiscardPile();
    }
}
