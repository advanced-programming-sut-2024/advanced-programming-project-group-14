package view;

import controller.GameMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Player;

public class GameMenuView extends MenuView {
    public static Stage stage;
    public ImageView opponentLeaderImage;
    public ImageView currentLeaderImage;
    public ImageView spellImage;
    public GridPane playerHand;

    public static void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        GameMenuView.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/GameMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/CSS/gwent-theme.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Game Menu");
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
    }


    @FXML
    public void initialize() {
        currentLeaderImage.setImage(new Image(String.valueOf(getClass().getResource("/Images/" + GameMenuController.currentPlayer.getCommander().getName() + ".jpg"))));
        opponentLeaderImage.setImage(new Image(String.valueOf(getClass().getResource("/Images/" + GameMenuController.opponentPlayer.getCommander().getName() + ".jpg"))));
        spellImage.setImage(new Image(String.valueOf(getClass().getResource("/Images/spell.jpg"))));

    }


}
