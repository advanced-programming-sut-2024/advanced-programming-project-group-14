import controller.GameMenuController;
import controller.LoadController;
import controller.PreGameMenuController;
import model.*;
import view.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    static {
        LoadController.loadAll();
        Player player1 = new Player(new User("amir","1","ahy","amir@gmail.com"));
        Player player2 = new Player(new User("ali","1","amb","ali@gmail.com"));
        GameMenuController.currentGameTable = new GameTable(new Date(),player1,player2);
        GameMenuController.currentPlayer = player1;
        GameMenuController.opponentPlayer = player2;
        Faction faction1 = new Faction(Faction.getFactionByName("Monsters"));
        Faction faction2 = new Faction(Faction.getFactionByName("Monsters"));
        player1.setFaction(faction1);
        player2.setFaction(faction2);
        player1.setCommander(faction1.getCommanderByName("DestroyerofWorlds"));
        player2.setCommander(faction1.getCommanderByName("CommanderoftheRedRiders"));
        player1.setDeck(new ArrayList<>(faction1.getCards()));
        player2.setDeck(new ArrayList<>(faction2.getCards()));
    }
    public static void main(String[] args) {
        //UsersManager usersManager = new UsersManager();
        //User user = new User("navid","1234","navid","navidatashinbar1383@gmail.com");
        //usersManager.loadUsers();
        Scanner scanner = new Scanner(System.in);
        LoginMenuView.run();
        //saveUsers(usersManager);
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