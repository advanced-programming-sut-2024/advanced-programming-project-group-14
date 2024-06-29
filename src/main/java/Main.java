import controller.PreGameMenuController;
import model.*;
import view.PreGameMenuView;

import java.util.Date;
import java.util.Scanner;

public class Main{
    //For debug
    static {
        User user1 = new User("amir","1","ahy","amir@gmail.com");
        User user2 = new User("ali","1","amb","ali@gmail.com");
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
        Faction faction1 = new Faction("faction1","faction1.jpg");
        Faction faction2 = new Faction("faction2","faction2.jpg");
        Card card1 = new Card("card1",10,2,"1","aloo","", "card1.jpg", 1);
        Card card2 = new Card("card2",15,1,"2","salam","", "card2.jpg", 2);
        Card card3 = new Card("leader1",5,3,"3","","", "leader1.jpg", 1);
        Card card4 = new Card("leader2",10,2,"4","","", "leader2.jpg", 3);
        Commander commander1 = new Commander(card3,faction1);
        Commander commander2 = new Commander(card4, faction2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PreGameMenuView.run();
    }
}