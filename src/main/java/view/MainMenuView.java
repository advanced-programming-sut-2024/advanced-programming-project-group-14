package view;

import controller.MainMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Result;

public class MainMenuView extends MenuView{
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        MainMenuView.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/MainMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/CSS/gwent-theme.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.setHeight(600);
        stage.setWidth(800);
        stage.show();
        try {
            String musicFile = getClass().getResource("/Media/profile.mp3").toExternalForm();
            Media sound = new Media(musicFile);
            player = new MediaPlayer(sound);
            player.setCycleCount(MediaPlayer.INDEFINITE);
            player.play();
        } catch (Exception e) {
            System.err.println("Error loading or playing the media file: " + e.getMessage());
        }
    }

    public void logout() {
        MainMenuController.logout();
        goToLoginMenu(stage);
    }

    public void startGame() {
        Result result = MainMenuController.createGame(getOpponentName());
        if (!result.isSuccessful())
            showError(result.getMessage());
        else{
            goToPreGameMenu(stage);
        }

    }

    public void openProfileMenu() {
        goToProfileMenu(stage);
    }

    private String getOpponentName() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Opponent");
        dialog.setHeaderText(null);
        dialog.setContentText("Who do you want to play with?");

        // Show dialog and get the result
        return dialog.showAndWait().orElse(null);
    }
}
