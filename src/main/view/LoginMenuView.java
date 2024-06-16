package main.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Optional;

public class LoginMenuView extends Application {
    public static void run(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login");
        dialog.setHeaderText("Please enter your username and password:");

        // Set the button types
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a username-password pair when the login button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            // Here you can process the username and password, e.g., authenticate the user
            // For demonstration purposes, we'll just show an alert with the entered data
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Username: " + usernamePassword.getKey() + "\nPassword: " + usernamePassword.getValue(), ButtonType.OK);
            alert.setHeaderText("Login Successful");
            alert.showAndWait();
        });
//        Parent root = FXMLLoader.load(getClass().getResource("/resources/FXML/RegisterMenu.fxml"));
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("/resources/CSS/gwent-theme.css").toExternalForm());
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Register Menu");
//        primaryStage.setHeight(600);
//        primaryStage.setWidth(800);
//        primaryStage.show();
    }
}