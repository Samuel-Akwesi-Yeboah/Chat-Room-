import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginWindowController {
    private Scene chatWindow;

    public ChatObserverClient loginUser;
    @FXML
    private MenuItem exitProgram;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    public void setChatWindow(Scene scene){
        chatWindow = scene;
    }

    private void openChatWindow(ActionEvent event, String username, String password){
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //Pass the username and password chatRoomGuiControllerSomeHow
        primaryStage.setTitle("Chat Room");
        primaryStage.setScene(chatWindow);
    }

    @FXML
    void closeProgram(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void login(ActionEvent event) {
        loginUser = new ChatObserverClient(userNameTextField.getText(),passwordTextField.getText(),null);
        boolean loginSucces = loginUser.validate();
        if(loginSucces){
            openChatWindow(event,userNameTextField.getText(),passwordTextField.getText());
        }
    }

}
