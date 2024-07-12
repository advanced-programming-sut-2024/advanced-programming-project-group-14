package view;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TVMenuView extends MenuView {
    public static Stage stage;
    @FXML
    private ListView<String> videoListView;
    @FXML
    private MediaView mediaView;
    @FXML
    private Label loadingLabel;
    private MediaPlayer mediaPlayer;
    public static void run(){
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        TVMenuView.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/TVMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/CSS/gwent-theme.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("TV Menu");
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(event -> {
            stopVideo();
        });
    }
    @FXML
    public void initialize() {
        File videoDir = new File("videos");
        if (videoDir.exists() && videoDir.isDirectory()) {
            List<String> videoFiles = Arrays.stream(videoDir.listFiles())
                    .filter(file -> file.isFile() && file.getName().endsWith(".mp4"))
                    .map(file -> file.getName().replace(".mp4",""))
                    .collect(Collectors.toList());
            videoListView.getItems().addAll(videoFiles);
        }
        videoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> playVideo(newValue));
    }
    private void playVideo(String fileName) {
        if (fileName != null) {
            loadingLabel.setText("Loading...");
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            File videoFile = new File("videos", fileName + ".mp4");
            Media media = new Media(videoFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.setOnReady(() -> {
                loadingLabel.setText("");
                mediaPlayer.play();
            });
            mediaPlayer.setOnError(() -> loadingLabel.setText("Failed to load video."));
        }
    }
    private void stopVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
