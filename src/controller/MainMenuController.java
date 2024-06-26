package controller;

import model.Player;
import model.Result;
import model.User;

public class MainMenuController {

    public static void enterMenu(String menuName) {

    }

    public static Result createGame(String opponentName) {
        if (User.getUserByUsername(opponentName) == null)
            return new Result(false, "Opponent not found!");
        if (User.getLoggedInUser().getUsername().equals(opponentName))
            return new Result(false, "You can not play with yourself!!");

        return new Result(true, "welcome to pregame :)");
    }

}

