import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMain extends Application {
    public static void main(String[] args) {
        launch(args);
        System.out.println("Hello World");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
        Parent loginPane = loginLoader.load();
        Scene loginScene = new Scene(loginPane, 600, 400);

        FXMLLoader chatLoader = new FXMLLoader(getClass().getResource("ChatRoomGUI.fxml"));
        Parent chatPane = chatLoader.load();
        Scene chatScene = new Scene(chatPane, 600, 400);

        LoginWindowController loginWindowController = (LoginWindowController) loginLoader.getController();
        loginWindowController.setChatWindow(chatScene);

        ChatRoomGuiController chatRoomGuiController = (ChatRoomGuiController) chatLoader.getController();
        chatRoomGuiController.setLoginWindow(loginScene);

        primaryStage.setTitle("Login");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}
