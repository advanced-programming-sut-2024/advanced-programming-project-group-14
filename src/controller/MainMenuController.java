package controller;

import model.Player;
import model.Result;
import model.User;

public class MainMenuController {

    public static void logout(String menuName) {
        // ToDo clean stay login file
    }

    public static Result createGame(String opponentName) {
        if (User.getUserByUsername(opponentName) == null)
            return new Result(false, "Opponent not found!");
        if (User.getLoggedInUser().getUsername().equals(opponentName))
            return new Result(false, "You can not play with yourself!!");

        return new Result(true, "welcome to pregame :)");
    }

}

