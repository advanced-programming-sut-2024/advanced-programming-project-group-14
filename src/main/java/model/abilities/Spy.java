package model.abilities;

import controller.GameMenuController;
import model.Actionable;
import model.Card;

import java.util.ArrayList;
import java.util.Random;

public class Spy extends Card implements Actionable {


    public Spy(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, ability, type, factionName, isHero, description);
    }

    @Override
    public void doAction(Object[] items) {
        ArrayList<Card> deck = GameMenuController.currentPlayer.getDeck();
        ArrayList<Card> hand = GameMenuController.currentPlayer.getHand();

        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            int toMove = random.nextInt(0, deck.size());
            hand.add(deck.get(toMove));
            deck.remove(toMove);
        }
    }
}
