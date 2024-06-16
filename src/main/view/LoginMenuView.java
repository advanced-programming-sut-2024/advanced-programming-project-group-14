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

import java.util.Optional;

public class LoginMenuView extends Application {
    public static void run(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/FXML/LoginMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/resources/CSS/gwent-theme.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Register Menu");
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        primaryStage.show();
    }
}