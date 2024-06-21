package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.model.Result;

public class LoginMenuController {
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private TextField loginPasswordTextField;
    @FXML
    private Button loginToggleButton;
    @FXML
    private PasswordField registerPasswordField;
    public TextField registerPasswordTextField;
    public Button registerToggleButton;
    @FXML
    private PasswordField registerCPasswordField;
    public TextField registerCPasswordTextField;
    public Button registerToggleCButton;

    @FXML
    public void initialize() {
        try {
            loginToggleButton.setOnAction(event -> togglePasswordVisibility(loginPasswordField, loginToggleButton, loginPasswordTextField));
            loginPasswordField.textProperty().bindBidirectional(loginPasswordTextField.textProperty());
            registerToggleButton.setOnAction(event -> togglePasswordVisibility(registerPasswordField, registerToggleButton, registerPasswordTextField));
            registerPasswordField.textProperty().bindBidirectional(registerPasswordTextField.textProperty());
            registerToggleCButton.setOnAction(event -> togglePasswordVisibility(registerCPasswordField, registerToggleCButton, registerCPasswordTextField));
            registerCPasswordField.textProperty().bindBidirectional(registerCPasswordTextField.textProperty());
        }catch (NullPointerException e){}

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

