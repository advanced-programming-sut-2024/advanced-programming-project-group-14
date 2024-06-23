package controller;

import model.*;

import java.io.File;
import java.util.Date;

public class PreGameMenuController {

    public static Player currentPlayer;
    public static Player opponentPlayer;

    public static Result showFaction() {

        return new Result(true, "");
    }

    public static Result selectFaction(String factionName) {
        Faction faction = Faction.getFactionByName(factionName);
        currentPlayer.setFaction(faction);
        return new Result(true, "Selected successfully");
    }

    public static Result showCards() {

        return new Result(true, "");
    }

    public static Result showDeck() {

        return new Result(true, "");
    }

    public static Result showCurrentUserInfo() {

        return new Result(true, "");
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




    public static Result showLeaders() {

        return new Result(true, "");
    }

    public static Result selectLeader(int number) {
        Faction faction = currentPlayer.getFaction();
        currentPlayer.setCommander(faction.getCommanderByNumber(number));
        return new Result(true, "selected successfully");
    }


    public static Result addToDeck(String cardName) {
        Card card = Card.getCardByName(cardName);
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
        currentPlayer.addToDeck(card);
        return new Result(true, "added successfully");
    }


    public static Result deleteFromDeck(Card card) {

        return new Result(true, "");
    }

    public static Result changeTurn() {

            return new Result(true, "");
    }

    public static Result startGame() {

        return new Result(true, "");
    }

}
