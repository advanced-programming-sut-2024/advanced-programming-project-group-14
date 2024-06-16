package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.model.Result;

public class LoginMenuController {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button togglePasswordVisibilityButton;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField confirmPasswordTextField;
    @FXML
    private Button toggleConfirmPasswordVisibilityButton;

    @FXML
    public void initialize() {
//        togglePasswordVisibilityButton.setOnAction(event -> togglePasswordVisibility(passwordField, togglePasswordVisibilityButton, passwordTextField));
//        passwordField.textProperty().bindBidirectional(passwordTextField.textProperty());
//        toggleConfirmPasswordVisibilityButton.setOnAction(event -> togglePasswordVisibility(confirmPasswordField, toggleConfirmPasswordVisibilityButton, confirmPasswordTextField));
//        confirmPasswordField.textProperty().bindBidirectional(confirmPasswordTextField.textProperty());
    }

    private void togglePasswordVisibility(PasswordField field, Button button, TextField textField) {
        if (field.isVisible()) {
            field.setVisible(false);
            field.setManaged(false);
            textField.setVisible(true);
            textField.setManaged(true);
            button.setText("🙈");
        } else {
            field.setVisible(true);
            field.setManaged(true);
            textField.setVisible(false);
            textField.setManaged(false);
            button.setText("👁");
        }
    }

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

