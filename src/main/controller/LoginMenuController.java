package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.model.Result;

public class LoginMenuController {

    public static Result register(String username, String password, String passwordConfirm, String nickname, String email) {


        return new Result(true, "");
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

