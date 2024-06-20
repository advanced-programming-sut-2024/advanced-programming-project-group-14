package main.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoginMenuView extends Application {
    public static void run(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/FXML/RegisterMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/resources/CSS/gwent-theme.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Register Menu");
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        primaryStage.show();
        showDialog();
    }

    private void showDialog(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Questions");
        dialog.setHeaderText("Please choose a question and answer it:");

        // Set the button types
        ButtonType OkType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OkType, ButtonType.CANCEL);

        // Create the username and password labels and fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label questionLabel = new Label("Question:");
        ComboBox<String> questionComboBox = new ComboBox<>();
        ArrayList<String> questions = new ArrayList<>();
        //ToDo:
        // add questions to Arraylist
        questionComboBox.getItems().addAll(questions);
        questionComboBox.setStyle("-fx-text-fill: #d4af37; -fx-border-radius: 3px;");

        Label answerLabel = new Label("Answer:");
        TextField answerField = new TextField();

        grid.add(questionLabel, 0, 0);
        grid.add(questionComboBox, 1, 0);
        grid.add(answerLabel, 0, 1);
        grid.add(answerField, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("/resources/CSS/gwent-theme.css").toExternalForm());
        dialog.getDialogPane().getStyleClass().add("dialog-pane");
        dialog.getDialogPane().getContent().getStyleClass().add("dialog-content");
        dialog.getDialogPane().lookup(".header-panel").getStyleClass().add("dialog-header");
        dialog.setTitle("dialog-title");


        // Convert the result to a pair of question and answer when the OK button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == OkType) {
                return new Pair<>(questionComboBox.getValue(), answerField.getText());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            //ToDo:
            // save questions with the answers(result.getKey and result.getValue)
        });
    }

}