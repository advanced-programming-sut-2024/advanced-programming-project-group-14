package controller;


import model.Player;
import model.Result;
import model.User;
import model.UsersManager;

public class MainMenuController {
    private static UsersManager usersManager;
    public MainMenuController(UsersManager usersManager) {
        MainMenuController.usersManager = usersManager;
    }
    public static void logout() {
        User.setLoggedInUser(null);
        usersManager.saveStayLoggedInUser(null);
    }

    public static Result createGame(String opponentName) {
        User opponent = User.getUserByUsername(opponentName);
        if (opponent == null)
            return new Result(false, "Opponent not found!");
        if (User.getLoggedInUser().getUsername().equals(opponentName))
            return new Result(false, "You can not play with yourself!!");

        PreGameMenuController.currentPlayer = new Player(User.getLoggedInUser());
        PreGameMenuController.opponentPlayer = new Player(opponent);
        return new Result(true, "welcome to pregame :)");
    }

}

