package view;

import controller.LoginMenuController;
import controller.RegisterMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Question;
import model.Result;
import model.User;

import java.io.IOException;
import java.util.ArrayList;


public class LoginMenuView extends MenuView {
    public static Stage stage;

    @FXML
    public TextField loginUsernameField;
    public CheckBox stayLoginCheckBox;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private TextField loginPasswordTextField;
    @FXML
    private Button loginToggleButton;
    private PasswordField registerPasswordField;
    public TextField registerPasswordTextField;
    public Button registerToggleButton;
    private PasswordField registerCPasswordField;
    public TextField registerCPasswordTextField;
    public Button registerToggleCButton;

    public static void run(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenuView.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/CSS/gwent-theme.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Login Menu");
        stage.setHeight(600);
        stage.setWidth(800);
        stage.show();
    }

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

    private void showQuestionDialog(){
        User user = User.getUserByUsername(loginUsernameField.getText());
        if (user==null){
            showError("Enter a correct Username");
            return;
        }

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Questions");
        dialog.setHeaderText("Please َAnswer the question:");

        ButtonType OkType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OkType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label questionLabel = new Label("Question:");
        Label questionTextLabel = new Label(user.getQuestion().getQuestionText());


        Label answerLabel = new Label("Answer:");
        TextField answerField = new TextField();

        grid.add(questionLabel, 0, 0);
        grid.add(questionTextLabel, 1, 0);
        grid.add(answerLabel, 0, 1);
        grid.add(answerField, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("/CSS/gwent-theme.css").toExternalForm());
        dialog.getDialogPane().getStyleClass().add("dialog-pane");
        dialog.getDialogPane().getContent().getStyleClass().add("dialog-content");
        dialog.getDialogPane().lookup(".header-panel").getStyleClass().add("dialog-header");
        dialog.setTitle("dialog-title");


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == OkType) {
                return answerField.getText();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            Result checkResult = LoginMenuController.checkAnswer(user,result);
            if (!checkResult.isSuccessful())
                showError(checkResult.getMessage());
            else
                showNewPasswordDialog(user);
        });
    }

    private void showNewPasswordDialog(User user){
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("New Password");
        dialog.setHeaderText("Please enter your new password:");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ButtonType OkButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OkButton, ButtonType.CANCEL);
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();
        Label confirmPasswordLabel = new Label("Confirm password:");
        TextField confirmPasswordField = new TextField();

        grid.add(passwordLabel, 0, 0);
        grid.add(passwordField, 1, 0);
        grid.add(confirmPasswordLabel, 0, 1);
        grid.add(confirmPasswordField, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("/CSS/gwent-theme.css").toExternalForm());
        dialog.getDialogPane().getStyleClass().add("dialog-pane");
        dialog.getDialogPane().getContent().getStyleClass().add("dialog-content");
        dialog.getDialogPane().lookup(".header-panel").getStyleClass().add("dialog-header");
        dialog.setTitle("dialog-title");

        // Enable/Disable Ok button depending on whether password and confirm password are the same
        Node okButton = dialog.getDialogPane().lookupButton(OkButton);
        okButton.setDisable(true);

        // Validate input
        passwordField.textProperty().addListener((observable, oldValue, newValue) ->
                okButton.setDisable(newValue.trim().isEmpty() || !newValue.equals(confirmPasswordField.getText())));
        confirmPasswordField.textProperty().addListener((observable, oldValue, newValue) ->
                okButton.setDisable(newValue.trim().isEmpty() || !newValue.equals(passwordField.getText())));

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == OkButton) {
                if (passwordField.getText().equals(confirmPasswordField.getText()))
                    return passwordField.getText();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            Result checkPassword = RegisterMenuController.checkPassword(passwordField.getText(),passwordField.getText());
            if (!checkPassword.isSuccessful())
                showError(checkPassword.getMessage());
            else{
                LoginMenuController.changePassword(user,passwordField.getText());
                showSuccessfulMessage("Password changed successfully");
            }
        });
    }

    public void login() {
        Result result = LoginMenuController.login(loginUsernameField.getText(),loginPasswordTextField.getText(),stayLoginCheckBox.isSelected());
        if (!result.isSuccessful())
            showError(result.getMessage());
        else
            goToMainMenu(stage);
    }

    public void forgetPassword() {
        showQuestionDialog();
    }


    public void openRegisterMenu() {
        goToRegisterMenu(stage);
    }
}