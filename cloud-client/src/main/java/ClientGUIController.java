import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import ru.gb.j5.network.SocketThread;
import ru.gb.j5.network.SocketThreadListener;

import java.io.IOException;
import java.net.Socket;

public class ClientGUIController implements SocketThreadListener, Thread.UncaughtExceptionHandler {

    public BorderPane bpMain;

    public Pane paneTop;
    public TextField tfIPAddress;
    public TextField tfPort;
    public TextField tfLogin;
    public PasswordField tfPassword;
    public Button btnLogin;
    public Button btnRegistration;

    public Pane paneBottom;
    public Button btnRename;
    public Button btnCreateDir;
    public Button btnDelete;
    public CheckBox cbAlwaysOnTop;

    public Pane paneCenter;
    public ListView<String> lvClientFiles;
    public ListView<String> lvServerFiles;
    public Button btnUpload;
    public Button btnDownload;
    public TextArea log;

    SocketThread socketThread;

    private void putLog(String msg) {
        if ("".equals(msg)) return;
        Platform.runLater(() -> {
            log.appendText(msg + "\n");
            log.positionCaret(log.getLength());
        });
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        showException(t, e);
        System.exit(1);
    }

    private void showException(Thread t, Throwable e) {
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        if (ste.length == 0)
            msg = "Empty Stacktrace";
        else {
            msg = String.format("Exception in \"%s\" %s: %s\n\tat %s",
                    t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
        }
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception");
            alert.setContentText(msg);
            alert.showAndWait();
        });
    }

    public void connect() {
        try {
            Socket socket = new Socket(tfIPAddress.getText(), Integer.parseInt(tfPort.getText()));
            socketThread = new SocketThread(this, "Client", socket);
        } catch (IOException e) {
            showException(Thread.currentThread(), e);
        }
    }

    public void registration(ActionEvent actionEvent) {
    }

    public void rename(ActionEvent actionEvent) {
    }

    public void createDirectory(ActionEvent actionEvent) {
    }

    public void delete(ActionEvent actionEvent) {
    }

    public void alwaysOnTopMethod(ActionEvent actionEvent) {
    }

    public void upload(ActionEvent actionEvent) {
    }

    public void download(ActionEvent actionEvent) {
    }

    /**
     * Socket Thread Listener Methods.
     * */

    @Override
    public void onSocketStart(SocketThread thread, Socket socket) {
        putLog("Start");
    }

    @Override
    public void onSocketReady(SocketThread thread, Socket socket) {
        putLog("Ready");
    }

    @Override
    public void onReceiveBytes(SocketThread thread, Socket socket, int i) {
        // Something informative for client.
    }

    @Override
    public void onSocketException(SocketThread thread, Throwable throwable) {
        showException(thread, throwable);
    }

    @Override
    public void onSocketStop(SocketThread thread) {
        putLog("Stop");
    }
}
