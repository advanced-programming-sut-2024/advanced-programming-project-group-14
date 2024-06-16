package main.model;

import java.util.ArrayList;

public class Row {

    private Card special;
    private ArrayList<Card> cards;

    public Row() {
        this.special = null;
        this.cards = null;
    }

    public int calculateTotalScore() {
        return 0;
    }

    public Card getSpecial() {
        return special;
    }

    public void setSpecial(Card special) {
        this.special = special;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addToCards(Card card) {
        this.cards.add(card);
    }
}
