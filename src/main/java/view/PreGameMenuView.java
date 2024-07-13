package view;

import client.Client;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import controller.LoadController;
import controller.PreGameMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PreGameMenuView extends MenuView{
    public static Stage stage;
    public int cardWidth = 150;
    public int cardHeight = 283;
    public int cardCountInRow = 9;

    @FXML
    private GridPane infoGrid;
    public Label remainCardsLabel;
    public Label deckSizeLabel;


    public static void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainMenuView.checkInGame.stop();
        PreGameMenuView.stage = stage;
        LoadController.loadAll();
        System.out.println("sads");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/PreGameMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/CSS/gwent-theme.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("PreGame Menu");
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    public void handleSaveDeckButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Deck");
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            PreGameMenuController.saveDeck("-f",file.getAbsolutePath());
            System.out.println("Deck saved to: " + file.getAbsolutePath());
        }
    }

    @FXML
    public void handleLoadDeckButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Deck");
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            PreGameMenuController.loadDeck(file);
            updateLabels();
            System.out.println("Deck loaded from: " + file.getAbsolutePath());
        }
    }

    public void showFaction() {
        infoGrid.getChildren().clear();

        JsonObject jsonRequest = new JsonObject();
        ArrayList<Faction> factions = Faction.getFactions();
        for (int i = 0; i < factions.size(); i++) {
            ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("/Images/" + factions.get(i).getName() + ".jpg"))));
            imageView.setFitWidth(cardWidth);
            imageView.setFitHeight(cardHeight);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                String name = getNameFromUrl(imageView.getImage().getUrl());
                ButtonType buttonType = showConfirmMessage("You want to change faction to "+ name + "?");
                if (buttonType == ButtonType.OK){
                    jsonRequest.addProperty("action","selectFaction");
                    jsonRequest.addProperty("factionName",name);
                    Client.getResult(jsonRequest);
                    showSuccessfulMessage(name + " chose as your faction");
                    infoGrid.getChildren().clear();
                    updateLabels();
                }
            });
            int columnIndex = i % cardCountInRow;
            infoGrid.add(imageView, columnIndex, (int) (i/cardCountInRow));
        }
    }

    public void showLeaders() {
        infoGrid.getChildren().clear();

        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("action", "getFactionName");
        Faction faction = Faction.getFactionByName(Client.getFactionName(jsonRequest));
        ArrayList<Commander> leaders = faction.getCommanders();
        jsonRequest.remove("action");
        for (int i = 0; i < leaders.size(); i++) {
            ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("/Images/" + leaders.get(i).getName() + ".jpg"))));
            imageView.setFitWidth(cardWidth);
            imageView.setFitHeight(cardHeight);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                String name = getNameFromUrl(imageView.getImage().getUrl());
                ButtonType buttonType = showConfirmMessage("You want to choose "+ name + " as leader?");
                if (buttonType == ButtonType.OK){
                    jsonRequest.addProperty("action","selectLeader");
                    jsonRequest.addProperty("leaderName",name);
                    Client.getResult(jsonRequest);
                    showSuccessfulMessage(name + " chose as your leader");
                    infoGrid.getChildren().clear();
                }
            });
            int columnIndex = i % cardCountInRow;
            infoGrid.add(imageView, columnIndex, (int) (i/cardCountInRow));
        }
    }

    public void showCards() {
        infoGrid.getChildren().clear();

        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("action", "getFactionName");
        Faction faction = Faction.getFactionByName(Client.getFactionName(jsonRequest));
        ArrayList<Card> cards = faction.getCards();
        jsonRequest.remove("action");
        for (int i = 0; i < cards.size(); i++) {
            ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("/Images/" + cards.get(i).getName() + ".jpg"))));
            imageView.setFitWidth(cardWidth);
            imageView.setFitHeight(cardHeight);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                String name = getNameFromUrl(imageView.getImage().getUrl());
                jsonRequest.addProperty("action","addToDeck");
                jsonRequest.addProperty("cardName",name);
                Result result = Client.getResult(jsonRequest);
                if (!result.isSuccessful()) {
                    showError(result.getMessage());
                    return;
                }
                infoGrid.getChildren().remove(imageView);
                updateLabels();
                showCards();
            });
            int columnIndex = i % cardCountInRow;
            infoGrid.add(imageView, columnIndex, (int) (i/cardCountInRow));
        }
    }

    public void showDeck() {
        infoGrid.getChildren().clear();

        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("action", "getPlayer");
        Player player = Client.getPlayer(jsonRequest);
        ArrayList<Card> deck = player.getDeck();
        jsonRequest.remove("action");
        for (int i = 0; i < deck.size(); i++) {
            ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("/Images/" + deck.get(i).getName() + ".jpg"))));
            imageView.setFitWidth(cardWidth);
            imageView.setFitHeight(cardHeight);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                String name = getNameFromUrl(imageView.getImage().getUrl());
                jsonRequest.addProperty("action","deleteFromDeck");
                jsonRequest.addProperty("cardName",name);
                Client.getResult(jsonRequest);
                infoGrid.getChildren().remove(imageView);
                updateLabels();
                showDeck();
            });
            int columnIndex = i % cardCountInRow;
            infoGrid.add(imageView, columnIndex, (int) (i/cardCountInRow));
        }
    }

    public void showCurrentUserInfo() {
        infoGrid.getChildren().clear();

        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("action", "getCurrentPlayer");
        Player currentPlayer = Client.getPlayer(jsonRequest);
        String[] labels = {"Player Name", "Faction Name", "Cards in Hand", "Number of Soldiers", "Special Cards", "Hero Cards", "Total Power"};
        String name = PreGameMenuController.getCurrentPlayer().getUsername();
        String factionName = PreGameMenuController.getCurrentPlayerFactionName();
        String handSize = String.valueOf(PreGameMenuController.getCurrentPlayerHandSize());
        String numberOfSoldiers = String.valueOf(PreGameMenuController.getCurrentPlayerNumberOfSoldiers());
        String numberOfSpecialCards = String.valueOf(PreGameMenuController.getCurrentPlayerHandSize()-PreGameMenuController.getCurrentPlayerNumberOfSoldiers());
        String numberOfHeroCards = String.valueOf(PreGameMenuController.getCurrentPlayerNumberOfHeroes());
        String totalPower = String.valueOf(PreGameMenuController.getCurrentPlayerTotalDeckPower());
        String[] values = {name, factionName, handSize, numberOfSoldiers, numberOfSpecialCards, numberOfHeroCards,totalPower};

        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i] + ":");
            label.setStyle("-fx-text-fill: #d4af37; -fx-font-size: 14px;");
            Label value = new Label(values[i]);
            value.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px;");

            infoGrid.add(label, 0, i);
            infoGrid.add(value, 2, i);
        }
    }

    public void changeTurn() {
        Result result = PreGameMenuController.changeTurn();
        if (!result.isSuccessful())
            showError(result.getMessage());
        else{
            showSuccessfulMessage(result.getMessage());
            infoGrid.getChildren().clear();
            updateLabels();
        }

    }

    private String getNameFromUrl(String Url){
        if (Url != null && !Url.isEmpty()) {
            int lastSlashIndex = Url.lastIndexOf('/');
            if (lastSlashIndex == -1) {
                lastSlashIndex = Url.lastIndexOf('\\');
            }
            if (lastSlashIndex != -1 && lastSlashIndex < Url.length() - 1) {
                return Url.substring(lastSlashIndex + 1,Url.lastIndexOf('.'));
            }
        }
        return "Unknown Image";
    }

    private void updateLabels(){
        try {
            remainCardsLabel.setText("Cards remains: " + PreGameMenuController.currentPlayer.getFaction().getCards().size());
            deckSizeLabel.setText("Cards in deck: " + PreGameMenuController.currentPlayer.getDeck().size());
        }catch (Exception e){
            remainCardsLabel.setText("Cards remains: 0");
            deckSizeLabel.setText("Cards in deck: 0");
        }

    }

    public void startGame() {
        Result result = PreGameMenuController.startGame();
        if (!result.isSuccessful())
            showError(result.getMessage());

        showSuccessfulMessage(result.getMessage());
        goToGameMenu(stage);
    }
}
