package main.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Player extends User {

    private Row closeCombat;
    private Row rangedCombat;
    private Row siege;
    private int lives;
    private Commander commander;
    private Faction faction;
    private ArrayList<Card> hand;
    private ArrayList<Card> discardPile;
    private HashMap<Integer, Integer> scoresOfRound;
    private int numberOfVetoUse;
    private boolean isPassed;

    public Player(User user) {
        super(user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail());
        this.lives = 2;
    }

    public Row getCloseCombat() {
        return closeCombat;
    }

    public void addToCloseCombat(Card card) {
        this.closeCombat.addToCards(card);
    }

    public Row getRangedCombat() {
        return rangedCombat;
    }

    public void addToRangedCombat(Card card) {
        this.rangedCombat.addToCards(card);
    }

    public Row getSiege() {
        return siege;
    }

    public void addToSiege(Card card) {
        this.siege.addToCards(card);
    }

    public int getLives() {
        return lives;
    }

    public Commander getCommander() {
        return commander;
    }

    public void setCommander(Commander commander) {
        this.commander = commander;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addToHand(Card card) {
        this.hand.add(card);
    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    public void addToDiscardPile(Card card) {
        this.discardPile.add(card);
    }

    public int getNumberOfVetoUse() {
        return numberOfVetoUse;
    }

    public void increaseNumberOfVetoUse() {
        this.numberOfVetoUse++;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public int calculateTotalScore() {
        return 0;
    }

    public void decreaseLife() {

    }
}
