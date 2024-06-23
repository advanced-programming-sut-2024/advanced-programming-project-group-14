package controller;

import model.Result;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class LoginMenuController {

    static Random random = new Random();

    public static Result register(String username, String password, String passwordConfirm, String nickname, String email) {
        if (User.getUserByUsername(username) != null)
            return new Result(false, "Username is already taken! Do you want to use this username" + username + "-" + random.nextInt(100));

        if (!Pattern.matches("[a-zA-Z0-9\\-]+", username))
            return new Result(false, "Username is invalid!");

        if (!Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", email))
            return new Result(false, "Email is invalid!");

        if (!Pattern.matches("^[a-zA-Z0-9!@#$%^&*]+$", password))
            return new Result(false, "Password is invalid!");

        if (password.length() < 8)
            return new Result(false, "Password is to short!");

        if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*"))
            return new Result(false, "Password should have at least one lowercase letter and one uppercase letter");

        if (!password.matches(".*[0-9].*"))
            return new Result(false, "Password should have at least one number");

        if (!password.matches(".*[!@#$%^&*].*"))
            return new Result(false, "Password should have at least one special character");

        if (!password.equals(passwordConfirm))
            return new Result(false, "Passwords are not same");

        User user = new User(username, password, nickname, email);
        return new Result(true, "Register successful");
    }

    public static Result login(String username, String password) {

        return new Result(true, "");
    }

    public static String showQuestionList() {

        return "";
    }

    public static Result pickQuestion(int number, String answer, String answerConfirm) {
        return new Result(true, "");
    }

    public static Result forgetPassword(String username) {
        return new Result(true, "");
    }

    public static Result checkAnswer(int number, String answer) {
        return new Result(true, "");
    }
}

