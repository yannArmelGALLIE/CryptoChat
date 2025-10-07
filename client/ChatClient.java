import java.io.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            

            System.out.println("Choisissez un nom d'utilisateur : ");
            String username = console.readLine();
            
            ChatConnection connection = new ChatConnection();
            connection.connect("localhost", 1234, username);

            String message;
            while ((message = console.readLine()) != null) {
                connection.sendMessage(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}