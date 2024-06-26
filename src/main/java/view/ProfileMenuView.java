package view;

import controller.ProfileMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Result;
import model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ProfileMenuView extends MenuView {
    @FXML
    private TextField gameHistoryCount;
    @FXML
    private GridPane infoGrid;
    @FXML
    private TableView<?> gameHistoryTable;

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

        //ToDo
        // Logic to fetch and display the last 'n' games in the table

        gameHistoryTable.setVisible(true);
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
