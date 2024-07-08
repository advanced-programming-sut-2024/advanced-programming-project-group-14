package model;

import java.util.ArrayList;

public class Row {

    private String name;
    private Card special;
    private boolean weatherAction;
    private ArrayList<Card> cards;
    private int totalScore;

    public Row(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
        this.totalScore = 0;
        this.weatherAction = false;
    }

    public int getTotalScore() {
        this.totalScore = 0;
        for (Card card : cards)
            this.totalScore += card.getPower();
        return totalScore;
    }

    public String getName() {
        return name;
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
        card.setCurrentRow(this);
    }

    public boolean isWeatherAction() {
        return weatherAction;
    }

    public void setWeatherAction(boolean weatherAction) {
        this.weatherAction = weatherAction;
    }

    public void deleteFromCards(Card card) {
        this.cards.remove(card);
    }
}
