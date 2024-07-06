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
import model.Row;
import model.abilities.Spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class GameMenuView extends MenuView {
    public static Stage stage;
    public int cardWidth = 70;
    public int cardHeight = 120;
    public Card clickedCard;

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

        ArrayList<GridPane> rows = new ArrayList<>(Arrays.asList(playerRanged,playerSiege,playerCloseCombat,opponentSiege,opponentRanged,opponentCloseCombat));
        ArrayList<GridPane> specials = new ArrayList<>(Arrays.asList(playerRangedSpecial,playerSiegeSpecial,playerCloseCombatSpecial,opponentSiegeSpecial,opponentRangedSpecial,opponentCloseCombatSpecial));
        ArrayList<GridPane> rowsWithSpecials = new ArrayList<>();
        rowsWithSpecials.addAll(rows);
        rowsWithSpecials.addAll(specials);

        spell.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            if (clickedCard!=null)
                GameMenuController.placeCard(clickedCard,"Weather");
        });
        for (GridPane gridPane: rowsWithSpecials) {
            gridPane.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
                String rowName = null;
                if (clickedCard != null) {
                    if (gridPane.getId().contains("Close"))
                        rowName = "Close Combat Unit";
                    else if (gridPane.getId().equals("Ranged"))
                        rowName = "Ranged Unit";
                    else if (gridPane.getId().contains("Siege"))
                        rowName = "Siege Unit";
                }
                GameMenuController.placeCard(clickedCard, rowName);
            });
        }

        GameMenuController.loadHand();
        for (int i=0; i<GameMenuController.currentPlayer.getHand().size();i++) {
            ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("/Images/" + GameMenuController.currentPlayer.getHand().get(i).getName() + ".jpg"))));
            imageView.setFitWidth(cardWidth);
            imageView.setFitHeight(cardHeight);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                clickedCard = getCardFromUrl(imageView.getImage().getUrl());
                resetGridPanes(rows,specials);
                showAvailableRows(clickedCard);
            });
            playerHand.add(imageView,i,0);
        }
        playerHand.setAlignment(Pos.CENTER);
        playerHand.setHgap(10);

    }

    private void resetGridPanes(ArrayList<GridPane> rows,ArrayList<GridPane> specials) {
        spell.getChildren().add(new ImageView(new Image(String.valueOf(getClass().getResource("/Images/spell.jpg")))));
        playerHand.setStyle("-fx-border-color: #a57a1c; -fx-border-width: 2px; -fx-background-color: #1c1c1c; -fx-pref-height: 120; -fx-pref-width: 600;");
        for (GridPane gridPane: rows) {
            gridPane.setStyle("-fx-border-color: #a57a1c; -fx-border-width: 2px; -fx-background-color: #1c1c1c; -fx-pref-height: 120; -fx-pref-width: 600;");
        }
        for (GridPane gridPane: specials) {
            gridPane.setStyle("-fx-border-color: #a57a1c; -fx-border-width: 2px; -fx-background-color: #1c1c1c; -fx-pref-height: 120; -fx-pref-width: 70;");
        }

    }

    private Card getCardFromUrl(String Url){
        if (Url != null && !Url.isEmpty()) {
            int lastSlashIndex = Url.lastIndexOf('/');
            if (lastSlashIndex == -1) {
                lastSlashIndex = Url.lastIndexOf('\\');
            }
            if (lastSlashIndex != -1 && lastSlashIndex < Url.length() - 1) {
                String name = Url.substring(lastSlashIndex + 1,Url.lastIndexOf('.'));
                return GameMenuController.currentPlayer.getFaction().getCardByName(name);
            }
        }
        return null;
    }

    private void showAvailableRows(Card card) {
        ArrayList<GridPane> rows = getRows(card);
        for (GridPane gridPane: rows) {
            System.out.println(gridPane.getId());
            if (gridPane.getId().contains("Special"))
                gridPane.setStyle("-fx-background-color: #FEE250; -fx-border-color: #a57a1c;  -fx-pref-height: 120; -fx-pref-width: 70;");
            else if(gridPane.getId().contains("spell"))
                gridPane.getChildren().add(new ImageView(new Image(String.valueOf(getClass().getResource("/Images/selectedSpell.jpg")))));
            else
                gridPane.setStyle("-fx-background-color: #FEE250; -fx-border-color: #a57a1c;  -fx-pref-height: 120; -fx-pref-width: 600;");

        }
    }

    public ArrayList<GridPane> getRows(Card card){
        boolean isSpy = false;
        ArrayList<GridPane> gridPanes = new ArrayList<>();
        if (card.getAbility()=="Spy")
            isSpy = true;

        switch (card.getType()){
            case "Close Combat Unit":
                if (isSpy) gridPanes.add(opponentCloseCombat);
                else gridPanes.add(playerCloseCombat);
                break;
            case "Ranged Unit":
                if (isSpy) gridPanes.add(opponentRanged);
                else gridPanes.add(playerRanged);
                break;
            case "Siege Unit":
                if (isSpy) gridPanes.add(opponentSiege);
                else gridPanes.add(playerSiege);
                break;
            case "Agile Unit":
                if (isSpy){
                    gridPanes.add(opponentCloseCombat);
                    gridPanes.add(playerRanged);
                }else{
                    gridPanes.add(playerCloseCombat);
                    gridPanes.add(playerRanged);
                }
                break;
            case "Special":
                gridPanes.add(playerCloseCombatSpecial);
                gridPanes.add(playerRangedSpecial);
                gridPanes.add(playerSiegeSpecial);
                break;
            case "Spell":
            case "Weather":
                gridPanes.add(spell);
                break;
        }
        return gridPanes;
    }

    public void leaderAction() {
    }
}
