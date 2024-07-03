package view;

import controller.GameMenuController;
import controller.PreGameMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Card;
import model.Player;
import model.abilities.Spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class GameMenuView extends MenuView {
    public static Stage stage;
    public int cardWidth = 70;
    public int cardHeight = 130;

    public ImageView opponentLeaderImage;
    public ImageView currentLeaderImage;
    public GridPane spell;
    public GridPane playerHand;
    public GridPane playerSiege;
    public GridPane playerRanged;
    public GridPane playerCloseCombat;
    public GridPane opponentCloseCombat;
    public GridPane opponentRanged;
    public GridPane opponentSiege;
    public GridPane opponentDiscardPile;
    public ImageView opponentDeck;
    public GridPane currentDiscardPile;
    public ImageView currentDeck;
    public GridPane opponentSiegeSpecial;
    public GridPane opponentRangedSpecial;
    public GridPane opponentCloseCombatSpecial;
    public GridPane playerCloseCombatSpecial;
    public GridPane playerRangedSpecial;
    public GridPane playerSiegeSpecial;
    public Label opponentName;
    public Label opponentCardCount;
    public Label opponentCrystal;
    public Label currentName;
    public Label currentCardCount;
    public Label currentCrystal;

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
        spell.getChildren().add(new ImageView(new Image(String.valueOf(getClass().getResource("/Images/spell.jpg")))));
        currentDeck.setImage(new Image(String.valueOf(getClass().getResource("/Images/deck.jpg"))));
        opponentDeck.setImage(new Image(String.valueOf(getClass().getResource("/Images/deck.jpg"))));
        opponentName.setText(GameMenuController.opponentPlayer.getUsername());
        currentName.setText(GameMenuController.currentPlayer.getUsername());
        
        for (int i=0; i<GameMenuController.currentPlayer.getHand().size();i++) {
            ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("/Images/" + GameMenuController.currentPlayer.getHand().get(i).getName() + ".jpg"))));
            imageView.setFitWidth(cardWidth);
            imageView.setFitHeight(cardHeight);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            });
            playerHand.add(imageView,i,0);
        }
        playerHand.setAlignment(Pos.CENTER);
        playerHand.setHgap(10);

    }

    public void leaderAction() {
    }
}
