package view;

import controller.PreGameMenuController;
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
import model.Card;
import model.Commander;
import model.Faction;
import model.Result;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class PreGameMenuView extends MenuView{
    public static Stage stage;

    @FXML
    private GridPane infoGrid;

    public static void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        PreGameMenuView.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/PreGameMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/CSS/gwent-theme.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.setHeight(600);
        stage.setWidth(800);
        stage.show();
    }

    @FXML
    public void handleSaveDeckButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Deck");
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            // Add logic to save deck to the file
            System.out.println("Deck saved to: " + file.getAbsolutePath());
        }
    }

    @FXML
    public void handleLoadDeckButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Deck");
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            // Add logic to load deck from the file
            System.out.println("Deck loaded from: " + file.getAbsolutePath());
        }
    }

    public void showFaction() {
        infoGrid.getChildren().clear();

        ArrayList<Faction> factions = Faction.getFactions();
        for (int i = 0; i < factions.size(); i++) {
            ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("/Images/" + factions.get(i).getPhotoName()))));
            imageView.setFitWidth(150);
            imageView.setFitHeight(100);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                String photoName = getPhotoNameFromUrl(imageView.getImage().getUrl());
                Faction faction = Faction.getFactionByPhotoName(photoName);
                ButtonType buttonType = showConfirmMessage("You want to change faction to "+ faction.getName() + "?");
                if (buttonType == ButtonType.OK){
                    PreGameMenuController.selectFaction(faction);
                    showSuccessfulMessage(faction.getName() + " chose as your faction");
                    infoGrid.getChildren().clear();
                }
            });
            int columnIndex = i % 4;
            infoGrid.add(imageView, columnIndex, (int) (i/4));
        }
    }

    public void showCards() {
        infoGrid.getChildren().clear();

        ArrayList<Card> cards = Card.getCards();
        for (int i = 0; i < cards.size(); i++) {
            Label label = new Label(cards.get(i).getName());
            label.setStyle("-fx-text-fill: #d4af37; -fx-font-size: 14px;");

            // Assuming images are stored in the resources folder
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Images/" + cards.get(i).getPhotoName())));
            imageView.setFitWidth(100);
            imageView.setFitHeight(150);

            infoGrid.add(label, 0, i);
            infoGrid.add(imageView, 1, i);
        }
    }

    public void showDeck() {
        infoGrid.getChildren().clear();
        ArrayList<Card> deck = PreGameMenuController.getCurrentPlayerDeck();
        for (int i = 0; i < deck.size(); i++) {
            Label label = new Label(deck.get(i).getName());
            label.setStyle("-fx-text-fill: #d4af37; -fx-font-size: 14px;");

            // Assuming images are stored in the resources folder
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Images/" + deck.get(i).getPhotoName())));
            imageView.setFitWidth(100);
            imageView.setFitHeight(150);

            infoGrid.add(label, 0, i);
            infoGrid.add(imageView, 1, i);
        }
    }

    public void showCurrentUserInfo() {
        infoGrid.getChildren().clear();

        String[] labels = {"Player Name", "Faction Name", "Cards in Hand", "Number of Soldiers", "Special Cards", "Hero Cards", "Total Power"};
        String name = PreGameMenuController.getCurrentPlayerName();
        String factionName = PreGameMenuController.getCurrentPlayerFactionName();
        String handSize = String.valueOf(PreGameMenuController.getCurrentPlayerHandSize());
        String numberOfSoldiers = String.valueOf(PreGameMenuController.getCurrentPlayerNumberOfSoldiers());
        String numberOfSpecialCards = String.valueOf(PreGameMenuController.getCurrentPlayerDeck().size()-PreGameMenuController.getCurrentPlayerNumberOfSoldiers());
        String numberOfHeroCards = String.valueOf(PreGameMenuController.getCurrentPlayerNumberOfHeroes());
        String totalPower = String.valueOf(PreGameMenuController.getCurrentPlayerTotalDeckPower());
        String[] values = {name, factionName, handSize, numberOfSoldiers, String.valueOf(numberOfSpecialCards), String.valueOf(numberOfHeroCards), String.valueOf(totalPower)};

        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i] + ":");
            label.setStyle("-fx-text-fill: #d4af37; -fx-font-size: 14px;");
            Label value = new Label(values[i]);
            value.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px;");

            infoGrid.add(label, 0, i);
            infoGrid.add(value, 2, i);
        }
    }

    public void showLeaders() {
        infoGrid.getChildren().clear();
        if (PreGameMenuController.getCurrentPlayerFaction() == null){
            showError("Choose a faction first!");
            return;
        }

        ArrayList<Commander> leaders = PreGameMenuController.getCurrentPlayerFaction().getCommanders();
        for (int i = 0; i < leaders.size(); i++) {
            Label label = new Label(leaders.get(i).getName());
            label.setStyle("-fx-text-fill: #d4af37; -fx-font-size: 14px;");

            // Assuming images are stored in the resources folder
            ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("/Images/" + leaders.get(i).getPhotoName()))));
            imageView.setFitWidth(100);
            imageView.setFitHeight(150);

            infoGrid.add(label, 0, i);
            infoGrid.add(imageView, 1, i);
        }
    }

    public void changeTurn() {
        Result result = PreGameMenuController.changeTurn();
        if (!result.isSuccessful())
            showError(result.getMessage());
        else
            showSuccessfulMessage(result.getMessage());
    }

    private String getPhotoNameFromUrl(String Url){
        if (Url != null && !Url.isEmpty()) {
            int lastSlashIndex = Url.lastIndexOf('/');
            if (lastSlashIndex == -1) {
                lastSlashIndex = Url.lastIndexOf('\\');
            }
            if (lastSlashIndex != -1 && lastSlashIndex < Url.length() - 1) {
                return Url.substring(lastSlashIndex + 1);
            }
        }
        return "Unknown Image";
    }

}
