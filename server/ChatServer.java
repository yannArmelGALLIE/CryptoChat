import java.io.*;
import java.net.*;
import java.util.*;

class ClientHandler implements Runnable {
    private Socket clientSocket;

    private static List<PrintWriter> clientOutputs = new ArrayList<>();

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );
            PrintWriter writer = new PrintWriter( clientSocket.getOutputStream(), true );
            synchronized (clientOutputs) {
                clientOutputs.add(writer);
            }

            String clientID = "client " + reader.readLine();
            String message;
            System.out.println("Le " + clientID + " est connecté !");

            while ((message = reader.readLine()) != null) {
                System.out.println(clientID + " : " + message);
                synchronized (clientOutputs) {
                    for (PrintWriter writer2 : clientOutputs) {
                        writer2.println(clientID + " : " + message);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class ChatServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Le serveur est en attente sur le port 1234...");

            while (true) {
                Socket clienSocket = serverSocket.accept();
                new Thread( new ClientHandler(clienSocket) ).start();
            }
        }
    }
}