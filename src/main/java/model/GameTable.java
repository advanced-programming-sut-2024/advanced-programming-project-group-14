package model;

import java.util.Date;

public class GameTable {

    private int roundNumber;
    private Date date;
    private Player player1;
    private Player player2;
    private Card spell;

    public GameTable(Date date, Player player1, Player player2) {
        this.date = date;
        this.player1 = player1;
        this.player2 = player2;
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

    public Card getSpell() {
        return spell;
    }

    public void setSpell(Card spell) {
        this.spell = spell;
    }

    public void increaseRoundNumber() {
        this.roundNumber++;
    }

    public Player getWinner() {
        return player1;
    }
}
