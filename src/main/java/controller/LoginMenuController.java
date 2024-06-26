package controller;

import model.Question;
import model.Result;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class LoginMenuController {

    static Random random = new Random();


    public static Result login(String username, String password, boolean stayLoggedIn) {
        if (User.getUserByUsername(username) == null)
            return new Result(false, "Username not found!");

        if (User.getUserByUsername(username).getPassword().equals(password))
            return new Result(false, "Password incorrect!");

        // ToDo implement stay logged in

        User.setLoggedInUser(User.getUserByUsername(username));
        return new Result(true, "Login successful");
    }

    public static void changePassword(String password) {
        User.getLoggedInUser().setPassword(password);
    }

    public static Result checkAnswer(String answer) {
        if (User.getLoggedInUser().getQuestion().getAnswer().equals(answer))
            return new Result(true, "");

        return new Result(false, "Your answer is wrong");
    }
}

