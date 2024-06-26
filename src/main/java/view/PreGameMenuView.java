package view;

import controller.PreGameMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Card;
import model.Commander;
import model.Faction;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class PreGameMenuView {
    @FXML
    private GridPane infoGrid;

    public static void run(Scanner scanner) {

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
            Label label = new Label(factions.get(i).getName());
            label.setStyle("-fx-text-fill: #d4af37; -fx-font-size: 14px;");

            // Assuming images are stored in the resources folder
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Images/" + factions.get(i).getPhotoName())));
            imageView.setFitWidth(100);
            imageView.setFitHeight(150);

            infoGrid.add(label, 0, i);
            infoGrid.add(imageView, 1, i);
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

        if (PreGameMenuController.getCurrentPlayerDeck() == null){
            PreGameMenuView.showError("Choose a faction first!");
            return;
        }
        ArrayList<Commander> leaders = PreGameMenuController.getCurrentPlayerFaction().getCommanders();
        for (int i = 0; i < leaders.size(); i++) {
            Label label = new Label(leaders.get(i).getName());
            label.setStyle("-fx-text-fill: #d4af37; -fx-font-size: 14px;");

            // Assuming images are stored in the resources folder
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("resources/Images/" + leaders.get(i).getPhotoName())));
            imageView.setFitWidth(100);
            imageView.setFitHeight(150);

            infoGrid.add(label, 0, i);
            infoGrid.add(imageView, 1, i);
        }
    }

    public void changeTurn() {

    }

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
