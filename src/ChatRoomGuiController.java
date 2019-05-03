import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChatRoomGuiController {
    ChatObserverClient client;
    String username;
    String password;

    private Scene loginWindow;

    @FXML
    private TextField inputTextField;

    @FXML
    private TextArea clientTextArea;

    @FXML
    private Button sendButton;

    @FXML
    public void initialize () {
        client = new ChatObserverClient(null,null,null);
        client.setScreen(clientTextArea);
    }

    public void setLoginWindow(Scene scene){
        loginWindow = scene;
    }

    void openLoginWindow(ActionEvent event){
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Login");
        primaryStage.setScene(loginWindow);
    }

    @FXML
    void sendMessage(ActionEvent event) {
        String message = inputTextField.getText() + "/n";
        clientTextArea.appendText(message);
        client.sendMessage(inputTextField.getText());
    }

    @FXML
    void logout(ActionEvent event){
        openLoginWindow(event);
    }
}
