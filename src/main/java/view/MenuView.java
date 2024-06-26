package view;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuView extends Application {
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

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showSuccessfulMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

}
