
import java.io.*;
import java.net.*;

public class ChatConnection {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public void connect(String host, int port, String username) throws IOException {
        socket = new Socket(host, port);
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        writer.println(username);

        new Thread(() -> {
            try {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
               e.printStackTrace();
            }
        }).start();
    }

    public void sendMessage(String message) {
        if (writer != null) {
            writer.println(message);
        }
    }

    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }
}
