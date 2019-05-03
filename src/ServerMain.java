import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Observable;

public class ServerMain extends Application {
    private ArrayList<UserProfile> users;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ServerGui.fxml"));
        primaryStage.setTitle("Server Log");
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
        System.out.println("Hello World");
    }
}
