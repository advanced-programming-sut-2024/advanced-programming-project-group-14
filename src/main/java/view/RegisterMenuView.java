package view;

import controller.RegisterMenuController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Question;
import model.Result;

import java.io.IOException;
import java.util.ArrayList;


public class RegisterMenuView extends MenuView {
    public static Stage stage;
    @FXML
    public TextField nameField;
    public TextField nicknameField;
    public TextField emailField;
    @FXML
    private PasswordField passwordField;
    public TextField passwordTextField;
    public Button passwordToggleButton;
    @FXML
    private PasswordField CPasswordField;
    public TextField CPasswordTextField;
    public Button CPasswordToggleButton;

    @Override
    public void start(Stage stage) throws Exception {
        Question.loadQuestions();
        RegisterMenuView.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/RegisterMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/CSS/gwent-theme.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Register Menu");
        stage.setHeight(600);
        stage.setWidth(800);
        stage.show();
    }

    @FXML
    public void initialize() {
        try {
            passwordToggleButton.setOnAction(event -> togglePasswordVisibility(passwordField, passwordToggleButton, passwordTextField));
            passwordField.textProperty().bindBidirectional(passwordTextField.textProperty());
            CPasswordToggleButton.setOnAction(event -> togglePasswordVisibility(CPasswordField, CPasswordToggleButton, CPasswordTextField));
            CPasswordField.textProperty().bindBidirectional(CPasswordTextField.textProperty());
        }catch (NullPointerException e){}

    }

    private void togglePasswordVisibility(PasswordField field, Button button, TextField textField) {
        if (field.isVisible()) {
            field.setVisible(false);
            field.setManaged(false);
            textField.setVisible(true);
            textField.setManaged(true);
            button.setText("🙈");
        } else {
            field.setVisible(true);
            field.setManaged(true);
            textField.setVisible(false);
            textField.setManaged(false);
            button.setText("👁");
        }
    }

    private void showQuestionDialog(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Questions");
        dialog.setHeaderText("Please choose a question and answer it:");

        ButtonType OkType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OkType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label questionLabel = new Label("Question:");
        ComboBox<String> questionComboBox = new ComboBox<>();
        ArrayList<String> questions = new ArrayList<>();
        for (Question question: Question.getQuestions()) {
            questions.add(question.getQuestionText());
        }
        questionComboBox.getItems().addAll(questions);
        questionComboBox.setStyle("-fx-text-fill: #d4af37; -fx-border-radius: 3px;");

        Label answerLabel = new Label("Answer:");
        TextField answerField = new TextField();

        grid.add(questionLabel, 0, 0);
        grid.add(questionComboBox, 1, 0);
        grid.add(answerLabel, 0, 1);
        grid.add(answerField, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("/CSS/gwent-theme.css").toExternalForm());
        dialog.getDialogPane().getStyleClass().add("dialog-pane");
        dialog.getDialogPane().getContent().getStyleClass().add("dialog-content");
        dialog.getDialogPane().lookup(".header-panel").getStyleClass().add("dialog-header");
        dialog.setTitle("dialog-title");


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == OkType) {
                return new Pair<>(questionComboBox.getValue(), answerField.getText());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            Question question = Question.getQuestionByText(result.getKey());
            RegisterMenuController.pickQuestion(question.getNumber(),result.getValue());
        });
    }

    public void register() {
        Result result = RegisterMenuController.register(nameField.getText(),passwordTextField.getText(),CPasswordTextField.getText(),nicknameField.getText(),emailField.getText());
        if (!result.isSuccessful())
            showError(result.getMessage());
        else{
            showQuestionDialog();
            showSuccessfulMessage(result.getMessage());
            goToMainMenu(stage);
        }

    }

    public void randomPassword() {
        passwordTextField.setText(RegisterMenuController.generateRandomPassword());
    }
}