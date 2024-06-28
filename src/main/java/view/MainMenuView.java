package view;

import controller.MainMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
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
