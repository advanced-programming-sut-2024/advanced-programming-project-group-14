//package model;
//
//import org.bytedeco.javacv.FFmpegFrameRecorder;
//import org.bytedeco.javacv.Frame;
//import org.bytedeco.javacv.Java2DFrameConverter;
//import javafx.application.Platform;
//import javafx.embed.swing.SwingFXUtils;
//import javafx.scene.Scene;
//import javafx.scene.image.WritableImage;
//
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public class ScreenRecorder {
//    private FFmpegFrameRecorder recorder;
//    private Java2DFrameConverter converter;
//    private WritableImage fxImage;
//    private ScheduledExecutorService executor;
//    private boolean isRecording = false;
//
//    public ScreenRecorder(String filePath, int width, int height) throws IOException {
//        recorder = new FFmpegFrameRecorder(filePath, width, height);
//        recorder.setVideoCodec(org.bytedeco.ffmpeg.global.avcodec.AV_CODEC_ID_H264);
//        recorder.setFormat("mp4");
//        recorder.setFrameRate(30);
//        recorder.setVideoBitrate(2000000); // Set bitrate to 2 Mbps
//        recorder.setPixelFormat(org.bytedeco.ffmpeg.global.avutil.AV_PIX_FMT_YUV420P); // Set pixel format
//
//        recorder.start();
//
//        converter = new Java2DFrameConverter();
//        fxImage = new WritableImage(width, height);
//    }
//
//    public void startRecording(Scene scene) {
//        if (isRecording) {
//            return; // Already recording
//        }
//        isRecording = true;
//
//        executor = Executors.newSingleThreadScheduledExecutor();
//        executor.scheduleAtFixedRate(() -> {
//            Platform.runLater(() -> {
//                scene.snapshot(fxImage);
//                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(fxImage, null);
//                Frame frame = converter.convert(bufferedImage);
//                if (isRecording) {
//                    try {
//                        recorder.record(frame);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }, 0, 33, TimeUnit.MILLISECONDS); // 30 fps
//
//        // Use 33 milliseconds for 30 fps (1000ms / 30fps ≈ 33ms)
//    }
//
//    public void stopRecording() {
//        if (!isRecording) {
//            return; // Not currently recording
//        }
//        isRecording = false;
//
//        executor.shutdown();
//        try {
//            executor.awaitTermination(3, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            recorder.stop();
//            recorder.release();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
