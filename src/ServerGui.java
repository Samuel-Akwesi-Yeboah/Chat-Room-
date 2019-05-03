import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

public class ServerGui {
    boolean serverEngaged = false;
    ChatServer server;
    @FXML
    private TextArea serverLog;

    @FXML
    public void initialize() {
        server = new ChatServer();
    }

    @FXML
    void engageServer(ActionEvent event) {
        if(!serverEngaged) {
            serverEngaged = true;
            try {
               server.setUpNetwork(serverLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            serverLog.appendText("The server has already been started.\n");
        }
    }
}
