package model.abilities;

import controller.GameMenuController;
import model.Actionable;
import model.Card;
import model.Row;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Muster extends Card implements Actionable {


    public Muster(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, ability, type, factionName, isHero, description);
    }

    public static boolean isTeammate(String str1, String str2) {
        Set<String> wordsSet = new HashSet<>(Arrays.asList(str1.split(" ")));
        for (String word : str2.split(" ")) {
            if (wordsSet.contains(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void doAction(Object[] items) {
        Row row = (Row) items[0];
        Muster muster = (Muster) items[1];
        Iterator<Card> iterator = GameMenuController.currentPlayer.getHand().iterator();
        for (Card card : GameMenuController.currentPlayer.getHand()) {
            if (iterator.hasNext()) {
                if (isTeammate(muster.getName(), card.getName())){
                    GameMenuController.currentPlayer.getHand().remove(card);
                    row.addToCards(card);
                }
            }
        }
        Iterator<Card> iterator1 = GameMenuController.currentPlayer.getDeck().iterator();
        for (Card card : GameMenuController.currentPlayer.getDeck()) {
            if (iterator1.hasNext()) {
                if (isTeammate(muster.getName(), card.getName())) {
                    GameMenuController.currentPlayer.getDeck().remove(card);
                    row.addToCards(card);
                }
            }
        }
    }
}
