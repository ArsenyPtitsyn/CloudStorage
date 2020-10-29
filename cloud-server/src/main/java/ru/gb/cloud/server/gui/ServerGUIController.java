package ru.gb.cloud.server.gui;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import ru.gb.cloud.server.core.CloudServerListener;
import ru.gb.cloud.server.core.CloudServer;

public class ServerGUIController implements CloudServerListener, Thread.UncaughtExceptionHandler{

    public CloudServer cloudServer = new CloudServer(this);
    public Button btnStart;
    public Button btnStop;
    public TextArea log;
    public AnchorPane apMain;

    public void start() {
        cloudServer.start(8189);
    }

    public void stop() {
        cloudServer.stop();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        msg = String.format("Exception in thread \"%s\" %s: %s\n\t at %s",
                t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception");
        alert.setContentText(msg);
        alert.showAndWait();
        System.exit(1);
    }

    @Override
    public void onCloudServerMessage(String msg) {
        Platform.runLater(() -> {
            log.appendText(msg + "\n");
            log.positionCaret(log.getLength());
        });
    }
}
