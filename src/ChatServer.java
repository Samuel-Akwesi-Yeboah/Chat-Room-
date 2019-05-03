import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

public class ChatServer extends Observable{
    private int numUsers;
    private ArrayList<UserProfile> userAccounts;

    public ChatServer(){
        userAccounts = new ArrayList<>();
        numUsers = 0;
    }

    public void setUpNetwork(TextArea screen) throws Exception {
        new Thread( () -> {
            try {  // Create a server socket
                @SuppressWarnings("resource")
                ServerSocket serverSocket = new ServerSocket(5000);
                screen.appendText("MultiThreadServer started" + '\n');

                while (true) {
                    // Listen for a new connection request
                    Socket clientSocket = serverSocket.accept();
                    ChatObserverClient writer = new ChatObserverClient(null,null,clientSocket.getOutputStream());
                    // Increment clientNo
                    numUsers+=1;
                    Platform.runLater( () -> {
                        screen.appendText("Client " + numUsers + " has joined the server.\n");
                    });

                    //Create account whenever a user joins the server
                    UserProfile profile = new UserProfile(Integer.toString(numUsers),writer);
                    userAccounts.add(profile);

                    // Create and start a new thread for the connection
                    new Thread(new ClientHandler(clientSocket)).start();
                    this.addObserver(writer);
                }
            }
            catch(IOException ex) {
            }
        }).start();
    }

    class ClientHandler extends ChatServer implements Runnable {
        private BufferedReader reader;

        public ClientHandler(Socket clientSocket) {
             Socket sock = clientSocket;
             try {
                 reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
             }catch (Exception e){}
        }

        /** Run a thread */
        public void run() {
            //Router System
            // Must route messages to the correct client based on a code system we create.
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                   System.out.println("Message Received:" + message);
                   UserProfile receiver;
                   receiver = userAccounts.get(0);
                   ChatObserverClient writer = receiver.getConnection();
                   writer.update(this,message);
                   //setChanged();
                   //notifyObservers(message);
                }
            } catch(IOException e) {
            }
        }
    }
}