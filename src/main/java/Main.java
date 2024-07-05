import controller.GameMenuController;
import controller.PreGameMenuController;
import model.*;
import view.*;

import java.util.Date;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        UsersManager usersManager = new UsersManager();
        usersManager.loadUsers();
        Scanner scanner = new Scanner(System.in);
        GameMenuView.run();
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