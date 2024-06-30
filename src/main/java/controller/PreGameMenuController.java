package controller;

import model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class PreGameMenuController {
    public static Player currentPlayer;
    public static Player opponentPlayer;

    public static void selectFaction(Faction faction) {
        currentPlayer.setFaction(faction);
    }

    public static Result saveDeck(String flag, String input) {
        if(flag.equals("-f")){
            String fileAddress = input;
            File file = new File(fileAddress);
            File parent = file.getParentFile();
            if(!parent.exists() && !parent.mkdirs()){
                return new Result(false, "invalid file address");
            }
            if(fileAddress==null){
                return new Result(false, "invalid file address");
            }
            if(!fileAddress.endsWith(".txt")){
                return new Result(false, "invalid file address");
            }
            currentPlayer.saveDeckByFileAddress(fileAddress);
        }
        else if(flag.equals("-n")){
            String deckName = input;

            currentPlayer.saveDeckByDeckName(deckName);
        }
        return new Result(true, "saved successfully");
    }

    public static Result loadDeck(String flag, String input) {
        if(flag.equals("-f")){
            String fileAddress = input;
            currentPlayer.loadDeckByFileAddress(fileAddress);
        }
        else if(flag.equals("-n")){
            String deckName = input;
            currentPlayer.loadDeckByDeckName(deckName);
        }
        return new Result(true, "");
    }

    public static void selectLeader(Commander commander) {
        currentPlayer.setCommander(commander);
    }

    public static Result addToDeck(Card card) {
        if(card==null){
            return new Result(false, "invalid card name");
        }
        if(currentPlayer.getDeck().size()>=22){
            return new Result(false, "deck is full");
        }
        if(card.getCapacity()<=0){
            return new Result(false, "card capacity is zero");
        }
        if(card.getType().equals("sell")|| card.getType().equals("weather")){
            if(currentPlayer.numberOfSpecificCardInDeck()>=10){
                return new Result(false, "you can't have more than 10 special cards in your deck");
            }
        }

        currentPlayer.getFaction().getCards().remove(card);
        currentPlayer.addToDeck(card);
        return new Result(true, "added successfully");
    }

    public static Result deleteFromDeck(Card card) {
        currentPlayer.getFaction().getCards().add(card);
        currentPlayer.deleteFromDeck(card);
        return new Result(true, "deleted successfully");
    }

    public static Result changeTurn() {
        if(currentPlayer.getDeck().size()<22){
            return new Result(false, "deck is not full");
        }
        Player temp = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = temp;
        return new Result(true, "Your turn: "+ currentPlayer.getUsername());
    }

    public static Result startGame() {
        if(currentPlayer.getDeck().size()<22){
            return new Result(false, "deck is not full");
        }

        new GameTable(Date.from(new Date().toInstant()), currentPlayer, opponentPlayer);
        return new Result(true, "game started successfully");
    }

    public static Player getCurrentPlayer(){
        return currentPlayer;
    }

    public static String getCurrentPlayerFactionName() {
        if(currentPlayer.getFaction() == null)
            return "";
        return currentPlayer.getFaction().getName();
    }

    public static int getCurrentPlayerHandSize() {
        if(currentPlayer.getDeck() == null)
            return 0;
        return currentPlayer.getDeck().size();
    }

    public static int getCurrentPlayerNumberOfSoldiers() {
        int number = 0;
        for (Card card: currentPlayer.getDeck()) {
            if (card.getAbility() != "hero" || card.getType()!="spell" || card.getType()!="weather")
                number++;
        }
        return number;
    }

    public static int getCurrentPlayerNumberOfHeroes() {
        int number = 0;
        for (Card card: currentPlayer.getDeck()) {
            if (card.getAbility() == "hero")
                number++;
        }
        return number;
    }

    public static int getCurrentPlayerTotalDeckPower() {
        int power = 0;
        for (Card card: currentPlayer.getDeck()) {
            power+=card.getPower();
        }
        return power;
    }

}
