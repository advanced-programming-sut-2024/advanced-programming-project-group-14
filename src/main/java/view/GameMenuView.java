package view;

import controller.GameMenuController;
import controller.PreGameMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Card;
import model.Player;
import model.Row;
import model.abilities.Spy;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class GameMenuView extends MenuView {
    public static Stage stage;
    public static double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width * 0.9;
    public static double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height * 0.9;
    public static double cardWidth = 0.06 * screenWidth;
    public static double cardHeight = 0.1 * screenHeight;
    private double leaderImageWidth = 0.09 * screenWidth;
    private double leaderImageHeight = 0.18 * screenHeight;
    private double buttonWidth = 0.06 * screenWidth;
    public Card clickedCard;

    public GridPane mainGridPane;
    public ImageView opponentLeaderImage;
    public ImageView currentLeaderImage;
    public GridPane spell;
    public Button passButton;
    public GridPane playerHand;
    public GridPane playerSiege;
    public GridPane playerRanged;
    public GridPane playerCloseCombat;
    public GridPane space;
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

    public ArrayList<GridPane> rows = new ArrayList<>();
    public ArrayList<GridPane> specials = new ArrayList<>();

    public Label opponentName;
    public Label opponentCardCount;
    public ImageView opponentCrystal;
    public Label currentName;
    public Label currentCardCount;
    public ImageView currentCrystal;
    public Label opponentSiegeScore;
    public Label opponentRangedScore;
    public Label opponentCloseCombatScore;
    public Label playerCloseCombatScore;
    public Label playerRangedScore;
    public Label playerSiegeScore;
    public Label currentScore;
    public Label opponentScore;
    public Label currentDeckSize;
    public Label opponentDeckSize;

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
        stage.setWidth(screenWidth);
        stage.setHeight(screenHeight);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    public void initialize() {
        rows = new ArrayList<>(Arrays.asList(playerRanged, playerSiege, playerCloseCombat, opponentSiege, opponentRanged, opponentCloseCombat));
        specials = new ArrayList<>(Arrays.asList(playerRangedSpecial, playerSiegeSpecial, playerCloseCombatSpecial, opponentSiegeSpecial, opponentRangedSpecial, opponentCloseCombatSpecial));

        GameMenuController.loadHand();
        doVeto();
        resizePanes();
        updateTable();
    }

    private void resizePanes() {
        mainGridPane.setPadding(new Insets(cardWidth, cardWidth, cardWidth, cardWidth));
        opponentLeaderImage.setFitWidth(leaderImageWidth);
        opponentLeaderImage.setFitHeight(leaderImageHeight);
        currentLeaderImage.setFitWidth(leaderImageWidth);
        currentLeaderImage.setFitHeight(leaderImageHeight);
        spell.setMaxWidth(2 * cardWidth);
        spell.setMaxHeight(cardHeight + 5);
        spell.setMinWidth(2 * cardWidth);
        spell.setMinHeight(cardHeight + 5);
        passButton.setPrefWidth(buttonWidth);

        playerHand.setMaxWidth(11 * cardWidth);
        playerHand.setMaxHeight(cardHeight + 10);
        playerHand.setMinWidth(11 * cardWidth);
        playerHand.setMinHeight(cardHeight + 10);
        space.setMaxWidth(9 * cardWidth);
        space.setMinWidth(9 * cardWidth);
        for (GridPane gridPane : rows) {
            gridPane.setMinWidth(8 * cardWidth);
            gridPane.setMinHeight(cardHeight + 10);
            gridPane.setMaxWidth(8 * cardWidth);
            gridPane.setMaxHeight(cardHeight + 10);
        }
        for (GridPane gridPane : specials) {
            gridPane.setMinWidth(cardWidth);
            gridPane.setMinHeight(cardHeight + 10);
            gridPane.setMaxWidth(cardWidth);
            gridPane.setMaxHeight(cardHeight + 10);
        }

        opponentDiscardPile.setMinWidth(cardWidth);
        opponentDiscardPile.setMinHeight(cardHeight + 10);
        currentDiscardPile.setMinWidth(cardWidth);
        currentDiscardPile.setMinHeight(cardHeight + 10);
        opponentDiscardPile.setMaxWidth(cardWidth);
        opponentDiscardPile.setMaxHeight(cardHeight + 10);
        currentDiscardPile.setMaxWidth(cardWidth);
        currentDiscardPile.setMaxHeight(cardHeight + 10);
        currentDeck.setFitWidth(cardWidth);
        currentDeck.setFitHeight(cardHeight + 10);
        opponentDeck.setFitWidth(cardWidth);
        opponentDeck.setFitHeight(cardHeight + 10);

        currentCrystal.setFitWidth(cardWidth);
        currentCrystal.setFitHeight(cardHeight * 0.3);
        opponentCrystal.setFitWidth(cardWidth);
        opponentCrystal.setFitHeight(cardHeight * 0.3);
    }

    private void updateTable() {
        updateRows();
        updateDiscardPilesAndImages();
        updateTableLabels();
        loadPlayerHand();
        resetGridPanes();
        updateSpell();
    }

    private void updateRows() {
        ArrayList<GridPane> rowsWithSpecials = new ArrayList<>();
        rowsWithSpecials.addAll(rows);
        rowsWithSpecials.addAll(specials);


        for (GridPane gridPane : rowsWithSpecials) {
            gridPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                String rowName = null;
                if (clickedCard != null) {
                    if (gridPane.getId().contains("Close"))
                        rowName = "Close Combat Unit";
                    else if (gridPane.getId().contains("Ranged"))
                        rowName = "Ranged Unit";
                    else if (gridPane.getId().contains("Siege"))
                        rowName = "Siege Unit";
                    GameMenuController.placeCard(clickedCard, rowName, null);
                    clickedCard = null;
                    updateTable();
                }
            });
        }

        for (GridPane gridPane : rows) {
            gridPane.getChildren().clear();
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(10);
        }
        for (int i = 0; i < GameMenuController.currentPlayer.getCloseCombat().getCards().size(); i++) {
            Card card = GameMenuController.currentPlayer.getCloseCombat().getCards().get(i);
            StackPane stackPane = getStackPaneOfCard(card);
            if (!card.getName().equals("Decoy"))
                ((Label) stackPane.getChildren().get(1)).setText(String.valueOf(card.getCurrentPower()));
            stackPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if (clickedCard != null && clickedCard.getName().equals("Decoy"))
                    GameMenuController.placeCard(clickedCard, "Close Combat Unit", card);

            });
            playerCloseCombat.add(stackPane, i, 0);
        }
        for (int i = 0; i < GameMenuController.currentPlayer.getRangedCombat().getCards().size(); i++) {
            Card card = GameMenuController.currentPlayer.getRangedCombat().getCards().get(i);
            StackPane stackPane = getStackPaneOfCard(card);
            if (!card.getName().equals("Decoy"))
                ((Label) stackPane.getChildren().get(1)).setText(String.valueOf(card.getCurrentPower()));
            stackPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if (clickedCard != null && clickedCard.getName().equals("Decoy"))
                    GameMenuController.placeCard(clickedCard, "Ranged Unit", card);

            });
            playerRanged.add(stackPane, i, 0);
        }
        for (int i = 0; i < GameMenuController.currentPlayer.getSiege().getCards().size(); i++) {
            Card card = GameMenuController.currentPlayer.getSiege().getCards().get(i);
            StackPane stackPane = getStackPaneOfCard(card);
            if (!card.getName().equals("Decoy"))
                ((Label) stackPane.getChildren().get(1)).setText(String.valueOf(card.getCurrentPower()));
            stackPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if (clickedCard != null && clickedCard.getName().equals("Decoy"))
                    GameMenuController.placeCard(clickedCard, "Siege Unit", card);
            });
            playerSiege.add(stackPane, i, 0);
        }
        for (int i = 0; i < GameMenuController.opponentPlayer.getCloseCombat().getCards().size(); i++) {
            Card card = GameMenuController.opponentPlayer.getCloseCombat().getCards().get(i);
            StackPane stackPane = getStackPaneOfCard(card);
            if (!card.getName().equals("Decoy"))
                ((Label) stackPane.getChildren().get(1)).setText(String.valueOf(card.getCurrentPower()));
            opponentCloseCombat.add(stackPane, i, 0);
        }
        for (int i = 0; i < GameMenuController.opponentPlayer.getRangedCombat().getCards().size(); i++) {
            Card card = GameMenuController.opponentPlayer.getRangedCombat().getCards().get(i);
            StackPane stackPane = getStackPaneOfCard(card);
            if (!card.getName().equals("Decoy"))
                ((Label) stackPane.getChildren().get(1)).setText(String.valueOf(card.getCurrentPower()));
            opponentRanged.add(stackPane, i, 0);
        }
        for (int i = 0; i < GameMenuController.opponentPlayer.getSiege().getCards().size(); i++) {
            Card card = GameMenuController.opponentPlayer.getSiege().getCards().get(i);
            StackPane stackPane = getStackPaneOfCard(card);
            if (!card.getName().equals("Decoy"))
                ((Label) stackPane.getChildren().get(1)).setText(String.valueOf(card.getCurrentPower()));
            opponentSiege.add(stackPane, i, 0);
        }

        for (GridPane gridPane : specials) {
            gridPane.getChildren().clear();
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(10);
        }
        playerCloseCombatSpecial.add(
                getStackPaneOfCard(GameMenuController.currentPlayer.getCloseCombat().getSpecial()), 0, 0);
        playerRangedSpecial.add(
                getStackPaneOfCard(GameMenuController.currentPlayer.getRangedCombat().getSpecial()), 0, 0);
        playerSiegeSpecial.add(
                getStackPaneOfCard(GameMenuController.currentPlayer.getSiege().getSpecial()), 0, 0);
        opponentCloseCombatSpecial.add(
                getStackPaneOfCard(GameMenuController.opponentPlayer.getCloseCombat().getSpecial()), 0, 0);
        opponentRangedSpecial.add(
                getStackPaneOfCard(GameMenuController.opponentPlayer.getRangedCombat().getSpecial()), 0, 0);
        opponentSiegeSpecial.add(
                getStackPaneOfCard(GameMenuController.opponentPlayer.getSiege().getSpecial()), 0, 0);


    }

    private void loadPlayerHand() {
        playerHand.getChildren().clear();
        for (int i = 0; i < GameMenuController.currentPlayer.getHand().size(); i++) {
            Card card = GameMenuController.currentPlayer.getHand().get(i);
            StackPane stackPane = getStackPaneOfCard(card);
            stackPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                clickedCard = card;
                resetGridPanes();
                showAvailableRows(clickedCard);
            });
            playerHand.add(stackPane, i, 0);
        }
        playerHand.setAlignment(Pos.CENTER);
        playerHand.setHgap(10);
    }

    private void updateDiscardPilesAndImages() {
        currentDiscardPile.getChildren().clear();
        opponentDiscardPile.getChildren().clear();
        if (GameMenuController.currentPlayer.getDiscardPile().size() != 0)
            currentDiscardPile.getChildren().add(getStackPaneOfCard(GameMenuController.currentPlayer.getDiscardPile().get(0)));
        if (GameMenuController.opponentPlayer.getDiscardPile().size() != 0)
            opponentDiscardPile.getChildren().add(getStackPaneOfCard(GameMenuController.opponentPlayer.getDiscardPile().get(0)));

        currentLeaderImage.setImage(new Image(String.valueOf(getClass().getResource("/Images/" + GameMenuController.currentPlayer.getCommander().getName() + ".jpg"))));

        currentLeaderImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (GameMenuController.currentPlayer.hasUsedCommanderAction()) {
                currentLeaderImage.setOpacity(0.3);
                return;
            }
            System.out.println(GameMenuController.currentPlayer.getUsername());
            //ButtonType buttonType = showConfirmMessage("You want to use commander action?");
            //if (buttonType == ButtonType.OK) {
                GameMenuController.playCommanderPower();
            System.out.println(11111);

            updateTable();
            currentLeaderImage.addEventHandler(MouseEvent.MOUSE_CLICKED,event1 -> {});
            //}
        });
        opponentLeaderImage.setImage(new Image(String.valueOf(getClass().getResource("/Images/" + GameMenuController.opponentPlayer.getCommander().getName() + ".jpg"))));
        if (GameMenuController.opponentPlayer.hasUsedCommanderAction())
            opponentLeaderImage.setOpacity(0.3);
        currentDeck.setImage(new Image(String.valueOf(getClass().getResource("/Images/deck.jpg"))));
        opponentDeck.setImage(new Image(String.valueOf(getClass().getResource("/Images/deck.jpg"))));
        opponentName.setText(GameMenuController.opponentPlayer.getUsername());
        currentName.setText(GameMenuController.currentPlayer.getUsername());
    }

    private void updateTableLabels() {
        playerCloseCombatScore.setText(String.valueOf(GameMenuController.currentPlayer.getCloseCombat().getTotalScore()));
        playerRangedScore.setText(String.valueOf(GameMenuController.currentPlayer.getRangedCombat().getTotalScore()));
        playerSiegeScore.setText(String.valueOf(GameMenuController.currentPlayer.getSiege().getTotalScore()));
        opponentCloseCombatScore.setText(String.valueOf(GameMenuController.opponentPlayer.getCloseCombat().getTotalScore()));
        opponentRangedScore.setText(String.valueOf(GameMenuController.opponentPlayer.getRangedCombat().getTotalScore()));
        opponentSiegeScore.setText(String.valueOf(GameMenuController.opponentPlayer.getSiege().getTotalScore()));

        currentName.setText(GameMenuController.currentPlayer.getUsername());
        opponentName.setText(GameMenuController.opponentPlayer.getUsername());
        currentScore.setText(String.valueOf(GameMenuController.currentPlayer.calculateTotalScore()));
        opponentScore.setText(String.valueOf(GameMenuController.opponentPlayer.calculateTotalScore()));
        currentCardCount.setText(String.valueOf(GameMenuController.currentPlayer.getHand().size()));
        opponentCardCount.setText(String.valueOf(GameMenuController.opponentPlayer.getHand().size()));
        currentCrystal.setImage(new Image(String.valueOf(getClass().getResource("/Images/Live" + GameMenuController.currentPlayer.getLives() + ".png"))));
        opponentCrystal.setImage(new Image(String.valueOf(getClass().getResource("/Images/Live" + GameMenuController.opponentPlayer.getLives() + ".png"))));
        currentDeckSize.setText(String.valueOf(GameMenuController.currentPlayer.getDeck().size()));
        opponentDeckSize.setText(String.valueOf(GameMenuController.opponentPlayer.getDeck().size()));
    }

    private void updateSpell() {
        spell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (clickedCard != null) {
                GameMenuController.placeCard(clickedCard, "Weather", null);
                clickedCard = null;
                updateTable();
            }

        });

        spell.getChildren().clear();
        spell.setStyle("-fx-border-color: #a57a1c; -fx-border-width: 2px; -fx-background-color: #1c1c1c;");
        for (int i = 0; i < GameMenuController.currentGameTable.getWeather().size(); i++) {
            Card card = GameMenuController.currentGameTable.getWeather().get(i);
            ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("/Images/" + card.getName() + ".jpg"))));
            imageView.setFitWidth(cardWidth);
            imageView.setFitHeight(cardHeight);
            spell.add(imageView, i, 0);
        }
        spell.setAlignment(Pos.CENTER);
        spell.setHgap(10);

    }

    private void resetGridPanes() {
        playerHand.setStyle("-fx-border-color: #a57a1c; -fx-border-width: 2px; -fx-background-color: #1c1c1c;");
        for (GridPane gridPane : rows) {
            gridPane.setStyle("-fx-border-color: #a57a1c; -fx-border-width: 2px; -fx-background-color: #1c1c1c;");
        }
        for (GridPane gridPane : specials) {
            gridPane.setStyle("-fx-border-color: #a57a1c; -fx-border-width: 2px; -fx-background-color: #1c1c1c;");
        }
        updateSpell();

    }

    private Card getCardFromUrl(String Url) {
        if (Url != null && !Url.isEmpty()) {
            int lastSlashIndex = Url.lastIndexOf('/');
            if (lastSlashIndex == -1) {
                lastSlashIndex = Url.lastIndexOf('\\');
            }
            if (lastSlashIndex != -1 && lastSlashIndex < Url.length() - 1) {
                String name = Url.substring(lastSlashIndex + 1, Url.lastIndexOf('.'));
                return GameMenuController.currentPlayer.getFaction().getCardByName(name);
            }
        }
        return null;
    }

    private void showAvailableRows(Card card) {
        ArrayList<GridPane> rows = getRows(card);
        for (GridPane gridPane : rows) {
            if (gridPane.getId().contains("Special"))
                gridPane.setStyle("-fx-background-color: #1c1c1c; -fx-border-color: #c3c701; -fx-border-width: 5px;");
            else if (gridPane.getId().contains("spell"))
                gridPane.setStyle("-fx-background-color: #1c1c1c; -fx-border-color: #c3c701; -fx-border-width: 5px;");
            else
                gridPane.setStyle("-fx-background-color: #1c1c1c; -fx-border-color: #c3c701; -fx-border-width: 5px;");

        }
    }

    public ArrayList<GridPane> getRows(Card card) {
        boolean isSpy = false;
        ArrayList<GridPane> gridPanes = new ArrayList<>();
        if (card.getAbility().equals("Spy"))
            isSpy = true;

        switch (card.getType()) {
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
                if (isSpy) {
                    gridPanes.add(opponentCloseCombat);
                    gridPanes.add(playerRanged);
                } else {
                    gridPanes.add(playerCloseCombat);
                    gridPanes.add(playerRanged);
                }
                break;
            case "Special":
                if (card.getName().equals("Decoy"))
                    gridPanes.addAll(Arrays.asList(playerCloseCombat, playerRanged, playerSiege));
                else
                    gridPanes.addAll(Arrays.asList(playerCloseCombatSpecial, playerRangedSpecial, playerSiegeSpecial));
                break;
            case "Spell":
            case "Weather":
                gridPanes.add(spell);
                break;
        }
        return gridPanes;
    }

    public StackPane getStackPaneOfCard(Card card) {
        try {
            ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("/Images/" + card.getName() + ".jpg"))));
            Label label = new Label(String.valueOf(card.getCurrentPower()));
            label.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-alignment: center;" +
                    "-fx-min-width: 18px; -fx-min-height: 18px; -fx-max-width: 18px; -fx-max-height: 18px;" +
                    " -fx-background-radius: 50%; -fx-font-size: 14;");
            label.setTranslateX(5);
            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(imageView, label);
            StackPane.setAlignment(label, Pos.TOP_LEFT);
            stackPane.setId(imageView.getImage().getUrl());
            imageView.setFitWidth(cardWidth);
            imageView.setFitHeight(cardHeight);
            if (card.getType().equals("Weather") || card.getType().equals("Special")) {
                stackPane.getChildren().remove(label);
            }
            return stackPane;
        } catch (Exception e) {
            return new StackPane();
        }
    }

    public void veto() {
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Veto: " + GameMenuController.currentPlayer.getUsername());
        dialogStage.initModality(Modality.WINDOW_MODAL);

        GridPane gridPane = new GridPane();
        for (int i = 0; i < GameMenuController.currentPlayer.getHand().size(); i++) {
            Card card = GameMenuController.currentPlayer.getHand().get(i);
            ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("/Images/" + card.getName() + ".jpg"))));
            imageView.setFitWidth(cardWidth);
            imageView.setFitHeight(cardHeight);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                GameMenuController.vetoCard(card);
                if (GameMenuController.currentPlayer.getNumberOfVetoUse() == 2)
                    dialogStage.close();
                gridPane.getChildren().remove(imageView);

            });
            gridPane.add(imageView, i, 0);
        }
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    public static void showDiscardPile() {
        if (GameMenuController.currentPlayer.getDiscardPile().size() == 0)
            return;

        Stage dialogStage = new Stage();
        dialogStage.setTitle("DiscardPile");
        dialogStage.initModality(Modality.WINDOW_MODAL);

        GridPane gridPane = new GridPane();
        for (int i = 0; i < GameMenuController.currentPlayer.getDiscardPile().size(); i++) {
            Card card = GameMenuController.currentPlayer.getDiscardPile().get(i);
            ImageView imageView = new ImageView(new Image(String.valueOf(GameMenuView.class.getResource("/Images/" + card.getName() + ".jpg"))));
            imageView.setFitWidth(cardWidth);
            imageView.setFitHeight(cardHeight);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                GameMenuController.reviveCard(card);
                dialogStage.close();
            });
            gridPane.add(imageView, i, 0);
        }
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane);
        dialogStage.setScene(scene);
        dialogStage.setWidth(600);
        dialogStage.setHeight(400);
        dialogStage.showAndWait();
    }

    private void doVeto() {
        for (int i = 0; i < 2; i++) {
            veto();
            GameMenuController.changeTurn();
        }
    }

    public void passTurn() {
        if (GameMenuController.opponentPlayer.isPassed()) {
            GameMenuController.endTurn();
            showSuccessfulMessage("Winner of this round: " + GameMenuController.currentGameTable.getRoundWinner(
                    GameMenuController.currentGameTable.getRoundNumber() - 1).getUsername());
            updateTable();
            return;
        }

        GameMenuController.currentPlayer.setPassed(true);
        GameMenuController.changeTurn();
        updateTable();
    }

    public void endGame() {
        showSuccessfulMessage("Winner: " + GameMenuController.currentGameTable.getGameWinner().getUsername());
        goToMainMenu(stage);
    }

}
