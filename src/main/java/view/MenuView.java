package view;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MenuView extends Application {
    public MediaView mediaView;

    public static MediaPlayer player;
    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void goToLoginMenu(Stage stage){
        LoginMenuView loginMenuView = new LoginMenuView();
        try {
            loginMenuView.start(stage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goToRegisterMenu(Stage stage){
        RegisterMenuView registerMenuView = new RegisterMenuView();
        try {
            registerMenuView.start(stage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goToMainMenu(Stage stage){
        MainMenuView mainMenuView = new MainMenuView();
        try {
            mainMenuView.start(stage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goToProfileMenu(Stage stage){
        ProfileMenuView profileMenuView = new ProfileMenuView();
        try {
            profileMenuView.start(stage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void goToScoreBoardMenu(Stage stage){
        ScoreBoardMenuView scoreBoardMenuView = new ScoreBoardMenuView();
        try {
            scoreBoardMenuView.start(stage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goToPreGameMenu(Stage stage){
        PreGameMenuView preGameMenuView = new PreGameMenuView();
        try {
            preGameMenuView.start(stage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goToGameMenu(Stage stage){
        GameMenuView gameMenuView = new GameMenuView();
        try {
            gameMenuView.start(stage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showSuccessfulMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public ButtonType showConfirmMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("");
        alert.setContentText(message);

        // Show the alert and wait for a response
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            return ButtonType.OK;
        } else {
            return ButtonType.CANCEL;
        }
    }

}
