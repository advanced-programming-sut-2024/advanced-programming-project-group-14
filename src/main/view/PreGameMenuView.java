package main.view;

import javafx.scene.control.Alert;

import java.util.Scanner;

public class PreGameMenuView {

    public static void run(Scanner scanner) {

    }

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
