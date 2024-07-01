package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameMenuView extends MenuView{
    public static Stage stage;
    public ImageView opponentSpellImage =  new ImageView();

    public static void run(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        GameMenuView.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/GameMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/CSS/gwent-theme.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Game Menu");
        stage.setHeight(600);
        stage.setWidth(800);
        stage.show();
    }


}
