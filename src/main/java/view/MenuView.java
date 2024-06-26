package view;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class MenuView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public static void changeMenu(Stage stage,MenuView menuView){
        try {
            menuView.start(stage);
        }catch (Exception e){}

    }

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
