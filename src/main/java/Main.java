import controller.GameMenuController;
import controller.PreGameMenuController;
import model.*;
import view.*;

import java.util.Date;
import java.util.Scanner;

public class Main {
    static{
        PreGameMenuController.currentPlayer = new Player(new User("amir","1","amir","amir@gmail.com"));
        PreGameMenuController.opponentPlayer = new Player(new User("ali","1","ali","ali@gmail.com"));
    }
    public static void main(String[] args) {
        UsersManager usersManager = new UsersManager();
        Scanner scanner = new Scanner(System.in);
        PreGameMenuView.run();
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