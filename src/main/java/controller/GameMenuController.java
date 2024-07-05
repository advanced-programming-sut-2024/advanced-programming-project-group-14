package controller;


import model.Card;
import model.GameTable;
import model.Player;
import model.Result;
import model.abilities.Decoy;

import java.util.ArrayList;

public class GameMenuController {
    public static GameTable currentGameTable;
    public static Player currentPlayer;
    public static Player opponentPlayer;

    public Result vetoCard(int cardNumber) {

        return new Result(true, "");
    }
    public String showDeck(){
        return "";
    }

    public Card randomChoose(ArrayList<Card> deck){
        return null;
    }

    public Result showInHand(int cardNumber) {
        return new Result(true,"");
    }

    public Result numOfRemainingCards() {
        return new Result(true,"");
    }

    public Result showDiscardPile() {
        return new Result(true,"");
    }

    public Result showCardsInRow(int rowNumber) {
        return new Result(true,"");
    }

    public Result showSpellInPlay() {
        return new Result(true,"");
    }

    public Result placeCard(int cardNumber, int roundNumber) {
        return new Result(true,"");
    }

    public void doAction(Card card){

    }

    public void reviveCard(Card card){

    }

    public Result showCommander() {
        return new Result(true,"");
    }

    public void playCommanderPower() {

    }

    public Result showPlayersInfo() {
        return new Result(true,"");
    }

    public Result showPlayersLives() {
        return new Result(true,"");
    }

    public Result showNumberOfCardsInHand() {
        return new Result(true,"");
    }

    public Result showTurnInfo() {
        return new Result(true,"");
    }

    public Result showTotalScore() {
        return new Result(true,"");
    }

    public Result showTotalScoreOfRow(int rowNumber) {
        return new Result(true,"");
    }

    public void passRound() {

    }

    public void endTurn(){

    }

    public void disCardSpells(){

    }

    public void endGame(){

    }

    public int CalculatePlayersTotalScore(){
        return  0;
    }


}
