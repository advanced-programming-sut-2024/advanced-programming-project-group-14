import controller.PreGameMenuController;
import model.*;
import view.*;

import java.util.Date;
import java.util.Scanner;

public class Main {
    //For debug
    static {
        User user1 = new User("amir", "1", "ahy", "amir@gmail.com");
        User user2 = new User("ali", "1", "amb", "ali@gmail.com");
        user1.setNumOfWin(2);
        user2.setNumOfWin(4);
        Question question = new Question(1, "alooo?");
        question.setAnswer("salam");
        user1.setQuestion(question);
        Player player1 = new Player(user1);
        Player player2 = new Player(user2);
        player1.setScoresOfRound(1, 10);
        player1.setScoresOfRound(2, 20);
        player1.setScoresOfRound(3, 30);
        player2.setScoresOfRound(1, 30);
        player2.setScoresOfRound(2, 10);
        player2.setScoresOfRound(3, 20);
        user1.addGamePlayed(new GameTable(new Date(), player2, player1));

        PreGameMenuController.currentPlayer = new Player(user1);
        PreGameMenuController.opponentPlayer = new Player(user2);
        Faction faction_realms = new Faction("faction_realms");
        Faction faction_skellige = new Faction("faction_skellige");
    }

    public static void main(String[] args) {
        UsersManager usersManager = new UsersManager();
        usersManager.loadUsers();
        Scanner scanner = new Scanner(System.in);
        LoginMenuView.run();
        saveUsers(usersManager);
    }

    private static void saveUsers(UsersManager usersManager) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            usersManager.saveUsers(User.getAllUsers());
        }));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}