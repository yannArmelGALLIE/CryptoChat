import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        try (Socket clientSocket = new Socket("localhost", 1234)) {
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Quel est votre ID (ex: 1) : ");
            String clientID = console.readLine();
            writer.println(clientID);

            new Thread(() -> {
                try {
                    String messageServer;
                    while ((messageServer = reader.readLine()) != null) {
                        System.out.println(messageServer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();;

            String message;
            while ((message = console.readLine()) != null) {
                writer.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}