package view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;


public class ScoreBoardMenuView extends MenuView {
    public static Stage stage;
    @FXML
    private VBox rootVBox;
    @FXML
    private TableView<User> scoreBoardTable;
    @FXML
    private TableColumn<User, String> rankColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, Integer> winsColumn;
    private ObservableList<User> scoreList;

    public static void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        RegisterMenuView.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ScoreBoardMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/CSS/gwent-theme.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("ScoreBoard Menu");
        stage.setHeight(600);
        stage.setWidth(800);
        stage.show();
    }

    @FXML
    public void initialize() {
        rankColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(scoreBoardTable.getItems().indexOf(cellData.getValue()) + 1).asString());
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        scoreList = FXCollections.observableArrayList();
        scoreBoardTable.setItems(scoreList);
        rootVBox.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double tableWidth = newWidth.doubleValue();
            rankColumn.setPrefWidth(tableWidth * 0.1);
            usernameColumn.setPrefWidth(tableWidth * 0.5);
            winsColumn.setPrefWidth(tableWidth * 0.4);
        });
        loadScoreBoard();
    }

    private void loadScoreBoard() {
        scoreList.clear();
        scoreList.addAll(User.getAllUsers());
        scoreList.sort((o1, o2) -> o2.getNumOfWin() - o1.getNumOfWin());
    }

    @FXML
    private void handleRefresh() {
        loadScoreBoard();
    }
}
