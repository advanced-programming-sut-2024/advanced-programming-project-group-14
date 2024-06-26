package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import main.model.Result;

public class ProfileMenuController {

    public static Result changeUsername(String username) {

        return new Result(true, "");
    }

    public static Result changeNickname(String nickname) {

        return new Result(true, "");
    }

    public static Result changeEmail(String email) {

        return new Result(true, "");
    }

    public static Result changePassword(String newPassword, String oldPassword) {

        return new Result(true, "");
    }

    public static Result showUsersInfo() {

        return new Result(true, "");
    }

    public static Result showGameHistory(int number) {

        return new Result(true, "");
    }

}
