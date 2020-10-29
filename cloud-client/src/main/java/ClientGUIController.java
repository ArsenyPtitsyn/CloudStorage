import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ClientGUIController {

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

    public void connect(ActionEvent actionEvent) {
    }

    public void regisration(ActionEvent actionEvent) {
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
}
