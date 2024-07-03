import controller.GameMenuController;
import controller.PreGameMenuController;
import model.*;
import view.GameMenuView;
import view.LoginMenuView;
import view.PreGameMenuView;
import view.ScoreBoardMenuView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main{
    //For debug
    static {
        User user1 = new User("amir","1","ahy","amir@gmail.com");
        User user2 = new User("ali","1","amb","ali@gmail.com");
        user1.setNumOfWin(2);
        user2.setNumOfWin(4);
        Question question = new Question(1,"alooo?");
        question.setAnswer("salam");
        user1.setQuestion(question);
        Player player1 = new Player(user1);
        Player player2 = new Player(user2);
        player1.setScoresOfRound(1,10);
        player1.setScoresOfRound(2,20);
        player1.setScoresOfRound(3,30);
        player2.setScoresOfRound(1,30);
        player2.setScoresOfRound(2,10);
        player2.setScoresOfRound(3,20);
        user1.addGamePlayed(new GameTable(new Date(),player2,player1));

        PreGameMenuController.currentPlayer = new Player(user1);
        PreGameMenuController.opponentPlayer = new Player(user2);
        Faction faction_realms = new Faction("faction_realms");
        Faction faction_skellige = new Faction("faction_skellige");
        Card realms_esterad = new Card("realms_esterad",10,1,"c","faction_realms",true,"");
        Card realms_trebuchet_1 = new Card("realms_trebuchet_1",5,1,"s","faction_realms",false,"");
        Card realms_foltest_bronze = new Card("realms_foltest_bronze",0,1,"m","faction_realms",false,"");
        Card realms_foltest_gold = new Card("realms_foltest_gold", 0,1,"m","faction_realms",false,"");
        Commander foltest_bronze = new Commander(realms_foltest_bronze,faction_realms);
        Commander foltest_gold = new Commander(realms_foltest_gold,faction_realms);
        GameMenuController.currentPlayer.setCommander(foltest_bronze);
        GameMenuController.opponentPlayer.setCommander(foltest_gold);

        GameMenuController.currentPlayer.addToHand(realms_esterad);
        GameMenuController.currentPlayer.addToHand(realms_esterad);
        GameMenuController.currentPlayer.addToHand(realms_trebuchet_1);
        GameMenuController.currentPlayer.addToHand(realms_trebuchet_1);
        GameMenuController.currentPlayer.addToHand(realms_esterad);
        GameMenuController.currentPlayer.addToHand(realms_esterad);
        GameMenuController.currentPlayer.addToHand(realms_trebuchet_1);
        GameMenuController.currentPlayer.addToHand(realms_esterad);
        GameMenuController.currentPlayer.addToHand(realms_esterad);
        GameMenuController.currentPlayer.addToHand(realms_trebuchet_1);



    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameMenuView.run();
    }
}