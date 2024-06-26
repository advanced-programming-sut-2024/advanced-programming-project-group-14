package main.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoginMenuView extends Application {
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

    public static void run(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/FXML/ProfileMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/resources/CSS/gwent-theme.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Register Menu");
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        primaryStage.show();
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
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Questions");
        dialog.setHeaderText("Please choose a question and answer it:");

        ButtonType OkType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OkType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label questionLabel = new Label("Question:");
        ComboBox<String> questionComboBox = new ComboBox<>();
        ArrayList<String> questions = new ArrayList<>();
        //ToDo:
        // add questions to Arraylist
        questionComboBox.getItems().addAll(questions);
        questionComboBox.setStyle("-fx-text-fill: #d4af37; -fx-border-radius: 3px;");

        Label answerLabel = new Label("Answer:");
        TextField answerField = new TextField();

        grid.add(questionLabel, 0, 0);
        grid.add(questionComboBox, 1, 0);
        grid.add(answerLabel, 0, 1);
        grid.add(answerField, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("/resources/CSS/gwent-theme.css").toExternalForm());
        dialog.getDialogPane().getStyleClass().add("dialog-pane");
        dialog.getDialogPane().getContent().getStyleClass().add("dialog-content");
        dialog.getDialogPane().lookup(".header-panel").getStyleClass().add("dialog-header");
        dialog.setTitle("dialog-title");


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == OkType) {
                return new Pair<>(questionComboBox.getValue(), answerField.getText());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            //ToDo:
            // save questions with the answers(result.getKey and result.getValue)
        });
    }

    private void showNewPasswordDialog(){
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
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("/resources/CSS/gwent-theme.css").toExternalForm());
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
            //ToDo:
            // change password(result)
        });
    }

}