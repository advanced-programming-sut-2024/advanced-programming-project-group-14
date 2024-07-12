package view;

import client.Client;
import com.google.gson.JsonObject;
import controller.LoginMenuController;
import controller.RegisterMenuController;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Question;
import model.Result;
import model.User;
import model.UsersManager;

//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;


public class LoginMenuView extends MenuView {
    public static Stage stage;
    private static final String USERNAME = "gwentgame14@gmail.com";
    private static final String PASSWORD = "perognblyuxxvicq";
    private String generatedCode;

    @FXML
    public TextField loginUsernameField;
    public CheckBox stayLoginCheckBox;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private TextField loginPasswordTextField;
    @FXML
    private Button loginToggleButton;
    private PasswordField registerPasswordField;
    public TextField registerPasswordTextField;
    public Button registerToggleButton;
    private PasswordField registerCPasswordField;
    public TextField registerCPasswordTextField;
    public Button registerToggleCButton;

    public static void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenuView.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMenu.fxml"));
        StackPane stackPane = new StackPane();
        String videoPath = getClass().getResource("/Media/GwentGame.mp4").toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setPreserveRatio(false);
        stackPane.getChildren().addAll(mediaView, root);
        Scene scene = new Scene(stackPane);
        mediaView.fitWidthProperty().bind(scene.widthProperty());
        mediaView.fitHeightProperty().bind(scene.heightProperty());
        scene.getStylesheets().add(getClass().getResource("/CSS/gwent-theme.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Login Menu");
        stage.setHeight(600);
        stage.setWidth(800);
        stage.show();

        UsersManager usersManager = new UsersManager();
        usersManager.loadStayLoggedInUser();
        if (User.getLoggedInUser() != null) {
            goToMainMenu(stage);
        }
        else {
            mediaPlayer.play();
            player = mediaPlayer;
        }
    }

    @FXML
    public void initialize() {
        try {
            loginToggleButton.setOnAction(event -> togglePasswordVisibility(loginPasswordField, loginToggleButton, loginPasswordTextField));
            loginPasswordField.textProperty().bindBidirectional(loginPasswordTextField.textProperty());
            registerToggleButton.setOnAction(event -> togglePasswordVisibility(registerPasswordField, registerToggleButton, registerPasswordTextField));
            registerPasswordField.textProperty().bindBidirectional(registerPasswordTextField.textProperty());
            registerToggleCButton.setOnAction(event -> togglePasswordVisibility(registerCPasswordField, registerToggleCButton, registerCPasswordTextField));
            registerCPasswordField.textProperty().bindBidirectional(registerCPasswordTextField.textProperty());
        } catch (NullPointerException e) {
        }

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

  /*  private void showQuestionDialog() {
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("action", "getUser");
        jsonRequest.addProperty("username", loginUsernameField.getText());

        User user = Client.getUser(jsonRequest);
        if (user == null) {
            showError("Enter a correct Username");
            return;
        }

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Questions");
        dialog.setHeaderText("Please َAnswer the question:");

        ButtonType OkType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OkType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label questionLabel = new Label("Question:");
        Label questionTextLabel = new Label(user.getQuestion().getQuestionText());


        Label answerLabel = new Label("Answer:");
        TextField answerField = new TextField();

        grid.add(questionLabel, 0, 0);
        grid.add(questionTextLabel, 1, 0);
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
                return answerField.getText();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            JsonObject jsonRequest1 = new JsonObject();
            jsonRequest1.addProperty("action", "checkAnswer");
            jsonRequest1.addProperty("username", loginUsernameField.getText());
            jsonRequest1.addProperty("answer", result);

            Result checkResult = Client.getResult(jsonRequest1);

            if (!checkResult.isSuccessful())
                showError(checkResult.getMessage());
            else
                showNewPasswordDialog(user);
        });
    }
*/
    private void showNewPasswordDialog(User user) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("New Password");
        dialog.setHeaderText("Please enter your new password:");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ButtonType OkButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OkButton, ButtonType.CANCEL);
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();
        Label confirmPasswordLabel = new Label("Confirm password:");
        TextField confirmPasswordField = new TextField();

        grid.add(passwordLabel, 0, 0);
        grid.add(passwordField, 1, 0);
        grid.add(confirmPasswordLabel, 0, 1);
        grid.add(confirmPasswordField, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("/CSS/gwent-theme.css").toExternalForm());
        dialog.getDialogPane().getStyleClass().add("dialog-pane");
        dialog.getDialogPane().getContent().getStyleClass().add("dialog-content");
        dialog.getDialogPane().lookup(".header-panel").getStyleClass().add("dialog-header");
        dialog.setTitle("dialog-title");

        // Enable/Disable Ok button depending on whether password and confirm password are the same
        Node okButton = dialog.getDialogPane().lookupButton(OkButton);
        okButton.setDisable(true);

        // Validate input
        passwordField.textProperty().addListener((observable, oldValue, newValue) ->
                okButton.setDisable(newValue.trim().isEmpty() || !newValue.equals(confirmPasswordField.getText())));
        confirmPasswordField.textProperty().addListener((observable, oldValue, newValue) ->
                okButton.setDisable(newValue.trim().isEmpty() || !newValue.equals(passwordField.getText())));

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == OkButton) {
                if (passwordField.getText().equals(confirmPasswordField.getText()))
                    return passwordField.getText();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            Result checkPassword = RegisterMenuController.checkPassword(passwordField.getText(), passwordField.getText());
            if (!checkPassword.isSuccessful())
                showError(checkPassword.getMessage());
            else {
                LoginMenuController.changePassword(user, passwordField.getText());
                showSuccessfulMessage("Password changed successfully");
            }
        });
    }

    public void login() {
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("action", "login");
        jsonRequest.addProperty("username", loginUsernameField.getText());
        jsonRequest.addProperty("password", loginPasswordField.getText());
        jsonRequest.addProperty("stayLoggedIn", stayLoginCheckBox.getText());
        Result result = Client.getResult(jsonRequest);

        if (!result.isSuccessful())
            showError(result.getMessage());
        else {
            verification();
        }
    }

    public void forgetPassword() {
        //showQuestionDialog();
    }

    public void verifyCode() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Email Verification");
        alert.setHeaderText("Enter the verification code sent to your email.");
        TextField codeField = new TextField();
        alert.getDialogPane().setContent(codeField);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String enteredCode = codeField.getText();
            if (enteredCode.equals(generatedCode)) {
                showSuccessfulMessage("Email verified successfully.");
                player.stop();
                goToMainMenu(stage);
            } else {
                showError("Invalid verification code.");
            }
        }
    }

    public void openRegisterMenu() {
        goToRegisterMenu(stage);
    }

    private void sendEmail(String to, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }

    private String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int code = random.nextInt(999999);
        return String.format("%06d", code);
    }

    public void sendEmailInBackground(String email, String subject, String content) {
        Alert waitingAlert = new Alert(Alert.AlertType.INFORMATION);
        waitingAlert.setTitle("Please Wait");
        waitingAlert.setHeaderText(null);
        waitingAlert.setContentText("Please wait while the verification code is being sent for 2FA...");
        waitingAlert.show();
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                sendEmail(email, subject, content);
                return null;
            }

            @Override
            protected void succeeded() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful");
                alert.setHeaderText(null);
                alert.setContentText("Verification code sent to your email.");
                alert.showAndWait();

                Optional<ButtonType> result = Optional.ofNullable(alert.getResult());
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    verifyCode();
                }

            }

            @Override
            protected void failed() {
                Throwable throwable = getException();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to send email: " + throwable.getMessage());
                alert.showAndWait();
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    public void verification() {
        generatedCode = generateVerificationCode();
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("action", "getEmail");
        jsonRequest.addProperty("username", loginUsernameField.getText());

        Result email = Client.getResult(jsonRequest);
        String subject = "Gwent Game";
        String content = "Your verification code is: " + generatedCode;

        sendEmailInBackground(email.getMessage(), subject, content);
    }

}