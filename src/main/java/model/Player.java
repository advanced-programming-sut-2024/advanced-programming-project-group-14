package model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Player extends User {
    private Row closeCombat;
    private Row rangedCombat;
    private Row siege;
    private ArrayList<Row> rows;
    private int lives;
    private Commander commander;
    private Faction faction;
    private ArrayList<Card> hand;
    private ArrayList<Card> discardPile;
    private HashMap<Integer, Integer> scoreOfRounds;
    private int numberOfVetoUse;
    private boolean isPassed;

    public Player(User user) {
        super(user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail());
        this.lives = 2;
        this.hand = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.scoreOfRounds = new HashMap<>();
        this.closeCombat = new Row("Close Combat Unit");
        this.rangedCombat = new Row("Ranged Unit");
        this.siege = new Row("Siege Unit");
        this.rows = new ArrayList<>(Arrays.asList(closeCombat,rangedCombat,siege));

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

    public ArrayList<Row> getRows() {
        return rows;
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

    public HashMap<Integer, Integer> getScoreOfRounds() {
        return scoreOfRounds;
    }

    public int getTotalScoreOfRounds() {
        int total = 0;
        for (int i = 1; i < 4; i++) {
            total += scoreOfRounds.get(i);
        }
        return total;
    }

    public void setScoresOfRound(int round, int score) {
        this.scoreOfRounds.put(round, score);
    }

    public void saveDeckByFileAddress(String fileAddress) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileAddress)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(super.getDeck());
            objectOutputStream.writeObject(this.faction);
            objectOutputStream.writeObject(this.commander);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDeckByDeckName(String deckName) {
        Path directoryPath = Paths.get("data/decks");
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream("data/decks/" + deckName)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(super.getDeck());
            objectOutputStream.writeObject(this.faction);
            objectOutputStream.writeObject(this.commander);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDeckByFile(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            super.setDeck((ArrayList<Card>) objectInputStream.readObject());
            setFaction((Faction) objectInputStream.readObject());
            setCommander((Commander) objectInputStream.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
