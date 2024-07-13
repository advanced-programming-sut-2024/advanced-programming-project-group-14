package controller;


import model.Player;
import model.Result;
import model.User;

public class MainMenuController {

    public static void logout(String clientId) {
        User.addLoggedInUser(clientId, null);
    }

    public static Result createGame(User me, String opponentName) {
        User opponent = User.getUserByUsername(opponentName);
        if (opponent == null)
            return new Result(false, "Opponent not found!");
        if (me.getUsername().equals(opponentName))
            return new Result(false, "You can not play with yourself!!");

        User.addCurrentMatches(me, opponent);
        User.addCurrentMatches(opponent, me);

        PreGameMenuController.currentPlayer = new Player(me);
        PreGameMenuController.opponentPlayer = new Player(opponent);
        return new Result(true, "welcome to pregame :)");
    }

    public static Result checkIsInGame(User user) {
        if (User.getCurrentMatches().get(user) == null)
            return new Result(false, "");
        return new Result(true,"");
    }

}

