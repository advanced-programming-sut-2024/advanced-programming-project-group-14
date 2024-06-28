import model.GameTable;
import model.Player;
import model.Question;
import model.User;
import view.LoginMenuView;

import java.util.Date;
import java.util.Scanner;

public class Main{
    //For debug
    static {
        User newUser = new User("amir","1","ahy","amir@gmail.com");
        User user2 = new User("ali","1","amb","ali@gmail.com");
        Question question = new Question(1,"alooo?");
        question.setAnswer("salam");
        newUser.setQuestion(question);
        Player player1 = new Player(newUser);
        Player player2 = new Player(user2);
        player1.setScoresOfRound(1,10);
        player1.setScoresOfRound(2,20);
        player1.setScoresOfRound(3,30);
        player2.setScoresOfRound(1,30);
        player2.setScoresOfRound(2,10);
        player2.setScoresOfRound(3,20);
        newUser.addGamePlayed(new GameTable(new Date(),player2,player1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginMenuView.run();
    }
}