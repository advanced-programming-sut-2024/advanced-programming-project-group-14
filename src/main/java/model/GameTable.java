package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class GameTable {
    private int roundNumber;
    private HashMap<Integer, Player> roundWinner;
    private Date date;
    private Player player1;
    private Player player2;
    private ArrayList<Card> weather;

    public GameTable(Date date, Player player1, Player player2) {
        this.date = date;
        this.player1 = player1;
        this.player2 = player2;
        this.weather = new ArrayList<>();
        this.roundNumber = 1;
        this.roundWinner = new HashMap<>();
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public Date getDate() {
        return date;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public ArrayList<Card> getWeather() {
        return weather;
    }

    public void addToWeather(Card weather) {
        this.weather.add(weather);
    }

    public void setWeather(ArrayList<Card> weather) {
        this.weather = weather;
    }

    public void increaseRoundNumber() {
        this.roundNumber++;
    }

    public Player getGameWinner() {
        if (player2.getLives() == 0)
            return player1;
        return player2;
    }

    public Player getRoundWinner(int roundNumber) {
        return roundWinner.get(roundNumber);
    }

    public void setRoundWinner(int roundNumber, Player winner, Player loser) {
        roundWinner.put(roundNumber, winner);
        loser.decreaseLife();
    }
}
