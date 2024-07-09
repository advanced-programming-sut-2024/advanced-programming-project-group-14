package controller;

import model.Question;
import model.Result;
import model.User;
import model.UsersManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class LoginMenuController {
    private static UsersManager usersManager;

    public LoginMenuController(UsersManager usersManager) {
        LoginMenuController.usersManager = usersManager;
    }

    public static Result login(String username, String password, boolean stayLoggedIn) {
        if (User.getUserByUsername(username) == null)
            return new Result(false, "Username not found!");

        if (!User.getUserByUsername(username).getPassword().equals(password))
            return new Result(false, "Password incorrect!");

        if (stayLoggedIn) {
            usersManager.saveStayLoggedInUser(User.getUserByUsername(username));
            return new Result(true, "Login successful");
        }
        User.setLoggedInUser(User.getUserByUsername(username));
        return new Result(true, "Login successful");
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

