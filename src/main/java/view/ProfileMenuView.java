package view;

import controller.ProfileMenuController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.GameTable;
import model.Player;
import model.Result;
import model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ProfileMenuView extends MenuView {
    @FXML
    private TextField gameHistoryCount;
    @FXML
    private GridPane infoGrid;
    @FXML
    private TableView<GameTable> gameHistoryTable;
    @FXML
    private TableColumn<GameTable, String> opponentColumn;
    @FXML
    private TableColumn<GameTable, Date> dateColumn;
    @FXML
    private TableColumn<GameTable, String> roundsColumn;
    @FXML
    private TableColumn<GameTable, String> totalColumn;
    @FXML
    private TableColumn<GameTable, String> winnerColumn;

    @Override
    public void start(Stage stage) throws Exception {
        RegisterMenuView.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ProfileMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/CSS/gwent-theme.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Profile Menu");
        stage.setHeight(600);
        stage.setWidth(800);
        stage.show();
    }


    @FXML
    public void handleUserInfoButtonClick() {
        User user = User.getLoggedInUser();
        gameHistoryTable.setVisible(false);
        infoGrid.getChildren().clear();

        String[] labels = {"Name", "Nickname", "Max Score", "Rank", "Games Played", "Wins", "Draws", "Losses"};
        String[] values = {user.getUsername(), user.getNickname(), String.valueOf(user.getMaxScore()), String.valueOf(user.getRank()), String.valueOf(user.getGamePlayed().size()), String.valueOf(user.getNumOfWin()), String.valueOf(user.getNumOfDraw()), String.valueOf(user.getNumOfLose())};
        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i] + ": ");
            label.setStyle("-fx-text-fill: #d4af37; -fx-font-size: 14px;");
            Label value = new Label(values[i]);
            value.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px;");
            infoGrid.add(label, 0, i);
            infoGrid.add(value, 1, i);
        }

        TextField nameField = new TextField();
        Button nameButton = new Button("Change username");
        nameButton.setOnAction(event -> {handleMessage(ProfileMenuController.changeUsername(nameField.getText()));});

        TextField nickNameField = new TextField();
        Button nickNameButton = new Button("Change nickname");
        nickNameButton.setOnAction(event -> {handleMessage(ProfileMenuController.changeNickname(nickNameField.getText()));});

        TextField emailField = new TextField();
        Button emailButton = new Button("Change email");
        emailButton.setOnAction(event -> {handleMessage(ProfileMenuController.changeEmail(emailField.getText()));});

        Label oldPasswordLabel = new Label("old password:");
        TextField oldPasswordField = new TextField();
        TextField passwordField = new TextField();
        Button passwordButton = new Button("Change password");
        passwordButton.setOnAction(event -> {handleMessage(ProfileMenuController.changePassword(passwordField.getText(),oldPasswordField.getText()));});

        ArrayList<Button> buttons = new ArrayList<Button>(Arrays.asList(nameButton,nickNameButton,emailButton,passwordButton));
        ArrayList<TextField> fields = new ArrayList<TextField>(Arrays.asList(nameField,nickNameField,emailField,passwordField,oldPasswordField));

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setPrefWidth(180);
            fields.get(i).setPrefWidth(180);
            infoGrid.add(fields.get(i), 1, labels.length + i);
            infoGrid.add(buttons.get(i), 2, labels.length + i);
        }
        infoGrid.add(oldPasswordLabel,0,labels.length+buttons.size());
        infoGrid.add(oldPasswordField,1,labels.length+buttons.size());

    }

    @FXML
    public void handleGameHistoryButtonClick() {
        infoGrid.getChildren().clear();
        Result result = ProfileMenuController.numberOfGameToShow(gameHistoryCount.getText());
        if (!result.isSuccessful()) {
            showError(result.getMessage());
            return;
        }

        User user = User.getLoggedInUser();
        ArrayList<GameTable> gameTables = user.getGamePlayed();

        // Set cell value factories for the columns
        opponentColumn.setCellValueFactory(cellData -> {
            Player opponent = cellData.getValue().getPlayer1();
            if (!cellData.getValue().getPlayer2().getUsername().equals(user.getUsername()))
                opponent = cellData.getValue().getPlayer2();
            return new SimpleStringProperty(opponent.getUsername());
        });
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        roundsColumn.setCellValueFactory(cellData -> {
            String roundScores = "";
            Player player1 = cellData.getValue().getPlayer1();
            Player player2 = cellData.getValue().getPlayer2();
            roundScores += player1.getScoreOfRounds().get(1) + " | " + player2.getScoreOfRounds().get(1) + "\n";
            roundScores += player1.getScoreOfRounds().get(2) + " | " + player2.getScoreOfRounds().get(2) + "\n";
            roundScores += player1.getScoreOfRounds().get(3) + " | " + player2.getScoreOfRounds().get(3);
            return new SimpleStringProperty(roundScores);
        });
        totalColumn.setCellValueFactory(cellData -> {
            String totalScores = cellData.getValue().getPlayer1().getTotalScoreOfRounds() + " | " + cellData.getValue().getPlayer2().getTotalScoreOfRounds();
            return new SimpleStringProperty(totalScores);
        });
        winnerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWinner().getUsername()));

        //Set style for columns
        ArrayList<TableColumn> tableColumns = new ArrayList<>(Arrays.asList(opponentColumn,dateColumn,roundsColumn,totalColumn,winnerColumn));
        for (TableColumn tableColumn: tableColumns) {
            setCustomCellFactory(tableColumn);
        }
        // Convert ArrayList to ObservableList
        ObservableList<GameTable> gameData = FXCollections.observableArrayList(gameTables);

        gameHistoryTable.setFixedCellSize(80);
        gameHistoryTable.setItems(gameData);
        gameHistoryTable.setVisible(true);
    }

    private <T> void setCustomCellFactory(TableColumn<GameTable, T> column) {
        column.setCellFactory(new Callback<>() {
            @Override
            public TableCell<GameTable, T> call(TableColumn<GameTable, T> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setStyle("");
                        } else {
                            Text text = new Text(item.toString());
                            text.setStyle("-fx-fill:white; -fx-font-size: 16px;"); // Set font size
                            text.wrappingWidthProperty().bind(param.widthProperty()); // Ensure text wraps within cell width
                            text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER); // Center text horizontally
                            setGraphic(text);
                            setAlignment(Pos.CENTER);
                        }
                    }
                };
            }
        });
    }

    public void handleMessage(Result result){
        if (!result.isSuccessful())
            showError(result.getMessage());
        else{
            showSuccessfulMessage(result.getMessage());
            handleUserInfoButtonClick();
        }
    }

}
