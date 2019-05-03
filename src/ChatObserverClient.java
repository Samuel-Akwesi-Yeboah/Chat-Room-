
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class ChatObserverClient extends Thread implements Observer{
    private BufferedReader reader = null;
    private PrintWriter writer = null;
    private TextArea screen = null;
    private OutputStream observerPatternSock = null;
    private String username;
    private String password;

    public ChatObserverClient(String user, String pass, OutputStream sock){
        username = user;
        password = pass;
        observerPatternSock = sock;
    }
    public boolean validate(){
        boolean validated = false;
        return !validated;
    }

    public void setUpNetworking() throws Exception {
        try {
            // Create a socket to connect to the server
            @SuppressWarnings("resource")
            Socket socket = new Socket("localhost",5000);
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("Connection established");
            Thread thread = new Thread(new IncomingReader());
            thread.start();
        }
        catch (IOException ex) {
        }
    }

    public void setScreen(TextArea textDisplay){
        screen = textDisplay;
    }

    public void sendMessage(String message){
        writer.println(message);
        writer.flush();
    }

    class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    screen.appendText(message + "\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        PrintWriter sendMessage = new PrintWriter(observerPatternSock);
        sendMessage.println(arg);
        sendMessage.flush();
    }
}
