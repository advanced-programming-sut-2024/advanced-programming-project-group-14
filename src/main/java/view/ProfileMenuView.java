package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Scanner;

public class ProfileMenuView extends MenuView{
    @FXML
    private TextField gameHistoryCount;
    @FXML
    private GridPane infoGrid;
    @FXML
    private TableView<?> gameHistoryTable;

    @FXML
    public void handleUserInfoButtonClick() {
        gameHistoryTable.setVisible(false);
        infoGrid.getChildren().clear();

        String[] labels = {"Name", "Nickname", "Top Score", "Rank", "Games Played", "Wins", "Draws", "Losses"};
        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i] + ":");
            label.setStyle("-fx-text-fill: #d4af37; -fx-font-size: 14px;");
            Label value = new Label("Sample Data " + (i + 1));
            value.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px;");
            infoGrid.add(label, 0, i);
            infoGrid.add(value, 1, i);
        }


        String[] buttonTexts = {"Change username","Change nickname","Change email","Change password"};
        for (int i = 0; i < buttonTexts.length; i++) {
            Button button = new Button(buttonTexts[i]);
            button.setPrefWidth(180);
            TextField textField = new TextField();
            textField.setPrefWidth(180);
            infoGrid.add(textField,0,labels.length+i);
            infoGrid.add(button,1,labels.length+i);
        }
    }

    @FXML
    public void handleGameHistoryButtonClick() {
        infoGrid.getChildren().clear();
        String gameCount = gameHistoryCount.getText();
        int n;
        if (gameCount.length() == 0)
            n = 5;
        else{
            try {
                n = Integer.parseInt(gameCount);
            } catch (NumberFormatException e) {
                //ToDo
                // Show error
                return;
            }
        }
        //ToDo
        // Logic to fetch and display the last 'n' games in the table

        gameHistoryTable.setVisible(true);
    }
}
