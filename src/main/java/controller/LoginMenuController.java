package controller;

import model.Result;
import model.User;
import model.UsersManager;
import server.GameDatabase;

import java.sql.SQLException;
import java.util.Date;

public class LoginMenuController {
    private static UsersManager usersManager= new UsersManager();

    public LoginMenuController(UsersManager usersManager) {
        LoginMenuController.usersManager = usersManager;
    }

    public static Result login(String username, String password, boolean stayLoggedIn) {
        try {
            if (GameDatabase.getUserByUsername(username) == null)
                return new Result(false, "Username not found!");

            if (!GameDatabase.getUserByUsername(username).getPassword().equals(password))
                return new Result(false, "Password incorrect!");

            if (stayLoggedIn) {
                usersManager.saveStayLoggedInUser(GameDatabase.getUserByUsername(username));
                return new Result(true, "Login successful");
            }
            User.setLoggedInUser(GameDatabase.getUserByUsername(username));
            return new Result(true, "Login successful");
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result(false, "Could not connect to the server.");
        }
    }

    public static void changePassword(User user, String password) {
        user.setPassword(password);
    }

    public static Result checkAnswer(User user, String answer) {
        if (user.getQuestion().getAnswer().equals(answer))
            return new Result(true, "");

        return new Result(false, "Your answer is wrong");
    }
}

